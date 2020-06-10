package las3007.assignment.factory.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountDetailsPageFactory {
    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(id= "genderSel")
    private WebElement genderSelect;

    @FindBy(id= "dayOfBirth")
    private WebElement dayOfBirthrSelect;

    @FindBy(id= "monthOfBirth")
    private WebElement monthOfBirthSelect;

    @FindBy(id= "yearOfBirth")
    private WebElement yearOfBirthEl;

    @FindBy(id= "countryOfResidence")
    private WebElement countryOfResidenceSelect;

    @FindBy(css= "input.pretty_btn")
    private WebElement submitBtn;

    @FindBy(css= "div.success")
    private WebElement successMsg;

    public AccountDetailsPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle() {
        return header.getText();
    }

    public void enterAccountDetails(String gender, String dayOfBirth, String monthOfBirth, String yearOfBirth, String country) {
        Select genderSe = new Select(genderSelect);
        genderSe.selectByIndex(0);
        Select daySe = new Select(dayOfBirthrSelect);
        daySe.selectByIndex(0);
        Select monthSe = new Select(monthOfBirthSelect);
        monthSe.selectByIndex(0);
        yearOfBirthEl.clear();
        Select countrySe = new Select(countryOfResidenceSelect);
        countrySe.selectByIndex(0);

        genderSe.selectByValue(gender);
        daySe.selectByVisibleText(dayOfBirth);
        monthSe.selectByVisibleText(monthOfBirth);
        yearOfBirthEl.sendKeys(yearOfBirth);
        countrySe.selectByVisibleText(country);
    }

    public void submitAccountDetails() {
        submitBtn.click();
    }

    public String getUpdateDetailsSuccessMessage() {
        return successMsg.getText();
    }
}
