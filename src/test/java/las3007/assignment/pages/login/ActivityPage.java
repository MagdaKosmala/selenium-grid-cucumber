package las3007.assignment.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActivityPage {
    protected WebDriver driver;

    private By editProfileLinkBy = By.linkText("Edit profile");

    public ActivityPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEditProfileLink() {
        return driver.findElement(editProfileLinkBy);
    }
}
