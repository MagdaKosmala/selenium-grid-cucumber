package las3007.assignment.factory.watchlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//*[@id=\"star-rating-widget\"]/div")
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
        return addedIcon.isDisplayed();
    }

    public void openRatingWidget() {
        try {
            Thread.sleep(1000);
            startRatingBtn.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRatingNumber(Integer rateNumber) {
        try {
            List<WebElement> stars = starsContainer.findElements(By.tagName("a"));
            WebElement option = stars.get(rateNumber);
            Thread.sleep(1000);
            option.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        try {
            Thread.sleep(2000);
            addToWatchlistBtn.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
