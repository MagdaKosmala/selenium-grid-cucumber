package las3007.assignment.factory.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivityPageFactory {
    protected WebDriver driver;

    @FindBy(linkText = "Edit profile")
    private WebElement editProfileLink;

    public ActivityPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getEditProfileLink() {
        return editProfileLink;
    }
}
