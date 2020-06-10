package las3007.assignment.tasks;

import las3007.assignment.utilis.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private URL gridUrl;
    protected WebDriver driver;

    protected String email, password, username;
    protected String dayOfBirth, monthOfBirth, yearOfBirth, country, gender;

    protected void getDriver(String browser) throws MalformedURLException {
        gridUrl = new URL("http://localhost:4444/wd/hub");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName(browser);
        driver = new RemoteWebDriver(gridUrl, dc);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    protected void getAccountProperties() {
        try {
            email = PropertyReader.getValue("email");
            password = PropertyReader.getValue("password");
            username = PropertyReader.getValue("username");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void getAccountDetailsProperties() {
        try {
            dayOfBirth = PropertyReader.getValue("day");
            monthOfBirth = PropertyReader.getValue("month");
            yearOfBirth = PropertyReader.getValue("year");
            country = PropertyReader.getValue("country");
            gender = PropertyReader.getValue("gender");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void tearDown() {
        driver.quit();
    }
}
