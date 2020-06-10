package las3007.assignment.factory.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedTitleSearchPageFactory {

    protected WebDriver driver;

    @FindBy(id = "groups-4")
    private WebElement groupCheckbox;

    @FindBy(css = "button.primary")
    private WebElement searchBtn;

    @FindBy(name = "sort")
    private WebElement sort;

    public AdvancedTitleSearchPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectGroupsItem() {
        groupCheckbox.click();
    }

    public AdvanceResultPageFactory loadAdvanceResultPage() {
        searchBtn.click();
        return new AdvanceResultPageFactory(driver);
    }

    public void selectSortByAZ() {
        Select sortSe = new Select(sort);
        sortSe.selectByValue("alpha,desc");
    }
}
