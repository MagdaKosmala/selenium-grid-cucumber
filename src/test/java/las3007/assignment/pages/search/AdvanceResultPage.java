package las3007.assignment.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdvanceResultPage {
    protected WebDriver driver;

    private By advanceSearchFirstResultImgBy = By.xpath("//*[@id=\"main\"]/div/div[3]/div/div[1]/div[2]/a/img");
    private By firstResultTitleBy = By.xpath("//*[@id=\"main\"]/div/div[3]/div/div[1]/div[3]/h3/a");

    public AdvanceResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEmptySearchResult() {
        return (driver.findElement(advanceSearchFirstResultImgBy) == null ? true : false );
    }

    public String getFirstsResultTitle() {
        return driver.findElement(firstResultTitleBy).getText();
    }

}
