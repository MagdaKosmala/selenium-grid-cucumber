package las3007.assignment.factory.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SigninPageFactory {
    private static final String URL = "https://www.imdb.com/registration/signin";
    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement signInHeader;

    @FindBy(css="span.auth-provider-text")
    private WebElement signInBtn;

    public SigninPageFactory (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loadSignInPage() {
        driver.get(URL);
    }

    public String getSignInHeader() {
        return signInHeader.getText();
    }

    public LoginPageFactory signInForExistingAccount() {
        signInBtn.click();
        return new LoginPageFactory(driver);
    }
}
