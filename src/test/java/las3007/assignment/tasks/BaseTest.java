package las3007.assignment.tasks;

import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.login.LoginPageFactory;
import las3007.assignment.factory.login.SigninPageFactory;
import las3007.assignment.utilis.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected String email, password;
    protected String browserName;

    protected SigninPageFactory signinPageFactory;
    protected LoginPageFactory loginPageFactory;
    protected MenuPageFactory menuPageFactory;

    protected void getDriver(String browserName) throws MalformedURLException {
        if(driver == null) {

            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName(browserName);

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        }
    }

    protected void tearDown() {
        driver.quit();
        driver = null;
    }

    protected void getAccountProperties() {
        try {
            email = PropertyReader.getValue("email");
            password = PropertyReader.getValue("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void loginUser() {
        getAccountProperties();

        signinPageFactory = new SigninPageFactory(driver);
        signinPageFactory.loadSignInPage();
        loginPageFactory = signinPageFactory.signInForExistingAccount();
        loginPageFactory.enterCredentials(email, password);
        loginPageFactory.submitLoginForm();

        menuPageFactory = new MenuPageFactory(driver);
    }
}
