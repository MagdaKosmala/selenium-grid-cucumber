package las3007.assignment.factory.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchPageFactory {

    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement advanceSearchHeader;

    @FindBy(linkText = "Advanced Title Search")
    private WebElement advancedTitleSearchLink;

    public AdvancedSearchPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderTitle(String title) {
        return advanceSearchHeader.getText();
    }

    public AdvancedTitleSearchPageFactory  loadAdvancedTitleSearchPage() {
        advancedTitleSearchLink.click();
        return new AdvancedTitleSearchPageFactory(driver);
    }
}
