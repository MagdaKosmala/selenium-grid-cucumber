package las3007.assignment.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountDetailsPage {
    protected WebDriver driver;

    private By headerBy = By.tagName("h1");
    private By genderSelectBy = By.id("genderSel");
    private By dayOfBirthrSelectBy = By.id("dayOfBirth");
    private By monthOfBirthSelectBy = By.id("monthOfBirth");
    private By yearOfBirthElBy = By.id("yearOfBirth");
    private By countryOfResidenceBy = By.id("countryOfResidence");
    private By submitBtnBy = By.cssSelector("input.pretty_btn");
    private By successMsgBy = By.cssSelector("div.success");

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getHeaderTitle() {
        return driver.findElement(headerBy).getText();
    }

    public void enterAccountDetails(String gender, String dayOfBirth, String monthOfBirth, String yearOfBirth, String country) {
        Select genderSe = new Select(driver.findElement(genderSelectBy));
        genderSe.selectByIndex(0);
        Select daySe = new Select(driver.findElement(dayOfBirthrSelectBy));
        daySe.selectByIndex(0);
        Select monthSe = new Select(driver.findElement(monthOfBirthSelectBy));
        monthSe.selectByIndex(0);
        driver.findElement(yearOfBirthElBy).clear();
        Select countrySe = new Select(driver.findElement(countryOfResidenceBy));
        countrySe.selectByIndex(0);

        genderSe.selectByValue(gender);
        daySe.selectByVisibleText(dayOfBirth);
        monthSe.selectByVisibleText(monthOfBirth);
        driver.findElement(yearOfBirthElBy).sendKeys(yearOfBirth);
        countrySe.selectByVisibleText(country);
    }

    public void submitAccountDetails() {
        driver.findElement(submitBtnBy).click();
    }

    public String getUpdateDetailsSuccessMessage() {
        return driver.findElement(successMsgBy).getText();
    }
}
