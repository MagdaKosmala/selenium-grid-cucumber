package las3007.assignment.factory.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPageFactory {
    protected WebDriver driver;

    @FindBy(linkText = "Personal details")
    private WebElement personalDetailsLink;

    public AccountPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPersonalDetailsLink() {
        return personalDetailsLink.getText();
    }

    public AccountDetailsPageFactory loadPersonalDetailsPage() {
        try {
            Thread.sleep(3000);
            personalDetailsLink.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AccountDetailsPageFactory(driver);
    }
}
