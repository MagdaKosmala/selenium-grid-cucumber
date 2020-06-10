package las3007.assignment.pages.rating;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RatingPage {
    protected WebDriver driver;

    private By headerBy = By.tagName("h1");
    private By filterRatingsBy = By.name("filter");
    private By filterListSizeBy = By.xpath("//*[@id=\"lister-header-current-size\"]");

    public RatingPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(headerBy).getText();
    }

    public void filterRatingsByNumber(Integer starsNumber) {
        driver.findElement(filterRatingsBy).click();
        Select filterRatingsSe = new Select(driver.findElement(filterRatingsBy));
        filterRatingsSe.selectByIndex(0);
        filterRatingsSe.selectByValue(String.valueOf(starsNumber));
    }

    public String getRatingListSize() {
        return driver.findElement(filterListSizeBy).getText().trim();
    }
}
