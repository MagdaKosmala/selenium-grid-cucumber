package las3007.assignment.factory.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvanceResultPageFactory {

    protected WebDriver driver;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[3]/div/div[1]/div[2]/a/img")
    private WebElement advanceSearchFirstResultImg;

    @FindBy(linkText = "A-Z")
    private WebElement sortByAZ;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[3]/div/div[1]/div[3]/h3/a")
    private WebElement firstResultTitle;

    public AdvanceResultPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isEmptySearchResult() {
        return (advanceSearchFirstResultImg == null ? true : false);
    }

    public String getFirstResultTitle() {
        return firstResultTitle.getText();
    }
}
