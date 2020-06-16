package las3007.assignment.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdvancedSearchPage {
    protected WebDriver driver;

    private By advanceSearchHeaderBy = By.tagName("h1");
    private By advancedTitleSearchLinkBy = By.linkText("Advanced Title Search");

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderTitle(String title) {
        return driver.findElement(advanceSearchHeaderBy).getText();
    }

    public AdvancedTitleSearchPage loadAdvancedTitleSearchPage() {
        driver.findElement(advancedTitleSearchLinkBy).click();
        return new AdvancedTitleSearchPage(driver);
    }
}
