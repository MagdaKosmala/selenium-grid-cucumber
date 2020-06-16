package las3007.assignment.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigninPage {
    private static final String URL = "https://www.imdb.com/registration/signin";

    protected WebDriver driver;

    private By signInHeaderBy = By.tagName("hi");
    private By signInBtnBy = By.cssSelector("span.auth-provider-text");

    public SigninPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadSignInPage() {
        driver.get(URL);
    }

    public String getSignInHeader() {
        return driver.findElement(signInHeaderBy).getText();
    }

    public LoginPage signInForExistingAccount() {
        driver.findElement(signInBtnBy).click();
        return new LoginPage(driver);
    }
}
