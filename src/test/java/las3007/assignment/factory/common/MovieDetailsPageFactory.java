package las3007.assignment.factory.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MovieDetailsPageFactory {
    protected WebDriver driver;

    @FindBy(xpath = "//*[@id=\"title-overview-widget\"]/div[2]/div[2]/button[2]/div")
    private WebElement addToWatchlistTextBtn;

    @FindBy(css = "button.ipc-button.uc-add-wl-button-icon--add.watchlist--title-main-desktop-standalone.ipc-button--core-base.ipc-button--single-padding.ipc-button--default-height")
    private WebElement addToWatchlistBtn;

    @FindBy(xpath ="//*[@id=\"title-overview-widget\"]/div[2]/div[2]/button[1]/div")
    private WebElement addedToWatchlistTextBtn;

    @FindBy(css = "svg.ipc-icon.ipc-icon--done.ipc-button__icon.ipc-button__icon--pre")
    private WebElement addedIcon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div[5]/div[1]/div/div/div[1]/div[2]/div/div[1]/div[2]/div")
    private WebElement startRatingBtn;

    @FindBy(css = "span.star-rating-value")
    private WebElement rateValue;

    @FindBy(xpath = "//*[@id=\"star-rating-widget\"]/div/div/span[1]/a")
    private WebElement removeRatingBtn;

    @FindBy(css = "span.star-rating-star.rating")
    private WebElement starFilled;

    @FindBy(css = "span.star-rating-star.no-rating")
    private WebElement starEmpty;

    @FindBy(xpath = "//*[@id=\"star-rating-widget\"]/div/div/span[1]/span")
    private WebElement starsContainer;

    public MovieDetailsPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isAddedIconPresent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addedIcon.isDisplayed();
    }

    public void openRatingWidget() {
        // element not clickable until all web page elements are loaded
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExpectedCondition<WebElement> btnClickable =
                ExpectedConditions.elementToBeClickable(startRatingBtn);
        waitUntil(btnClickable).click();
    }

    public void setRatingNumber(Integer rateNumber) {
        List<WebElement> stars = starsContainer.findElements(By.tagName("a"));
        WebElement option = stars.get(rateNumber);
        option.click();
    }

    public String ratingValue() {
        return rateValue.getText();
    }

    public void removeRating() {
        removeRatingBtn.click();
    }

    public boolean isRatingSelected() {
        return (starFilled != null && starFilled.isDisplayed()? true : false);
    }

    public boolean isEmptyStar() {
        return (starEmpty != null && starEmpty.isDisplayed()? true : false);
    }

    public void addToWatchlistToggle() {
        addToWatchlistBtn.click();
    }

    public <T> T waitUntil(ExpectedCondition<T> expectedCondition){
        return new WebDriverWait(driver, 10).until(expectedCondition);
    }
}
