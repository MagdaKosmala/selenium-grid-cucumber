package las3007.assignment.factory.rating;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RatingPageFactory {
    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(name = "filter")
    private WebElement filterRatings;

    @FindBy(xpath = "//*[@id=\"lister-header-current-size\"]")
    private WebElement filterListSize;

    public RatingPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public void filterRatingsByNumber(Integer starsNumber) {
        try {
            Thread.sleep(2000);
            filterRatings.click();
            Thread.sleep(2000);
            Select filterRatingsSe = new Select(filterRatings);
            filterRatingsSe.selectByIndex(0);
            Thread.sleep(2000);
            filterRatingsSe.selectByValue(String.valueOf(starsNumber));
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getRatingListSize() {
        return filterListSize.getText().trim();
    }
}
