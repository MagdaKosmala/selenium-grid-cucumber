package las3007.assignment.pages.search;

import las3007.assignment.factory.search.AdvanceResultPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AdvancedTitleSearchPage {

    protected WebDriver driver;

    private By groupCheckboxBy = By.id("groups-4");
    private By searchBtnBy = By.cssSelector("button.primary");
    private By sortBy = By.name("sort");

    public AdvancedTitleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectGroupsItem() {
        driver.findElement(groupCheckboxBy).click();
    }

    public AdvanceResultPageFactory loadAdvanceResultPage() {
        driver.findElement(searchBtnBy).click();
        return new AdvanceResultPageFactory(driver);
    }

    public void selectSortByAZ() {
        Select sortSe = new Select(driver.findElement(sortBy));
        sortSe.selectByValue("alpha,desc");
    }
}
