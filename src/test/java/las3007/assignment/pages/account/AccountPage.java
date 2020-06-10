package las3007.assignment.pages.account;

import las3007.assignment.factory.account.AccountDetailsPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    protected WebDriver driver;

    private By personalDetailsLinkBy = By.tagName("Personal details");

    public AccountPage(WebDriver driver){
        this.driver = driver;
    }

    public String getPersonalDetailsLink() {
        return driver.findElement(personalDetailsLinkBy).getText();
    }

    public AccountDetailsPageFactory loadPersonalDetailsPage() {
        driver.findElement(personalDetailsLinkBy).click();
        return new AccountDetailsPageFactory(driver);
    }
}
