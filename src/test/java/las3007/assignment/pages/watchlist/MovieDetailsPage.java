package las3007.assignment.pages.watchlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MovieDetailsPage {

    protected WebDriver driver;

    private By addToWatchlistBtnBy = By.cssSelector("button.ipc-button.uc-add-wl-button-icon--add.watchlist--title-main-desktop-standalone.ipc-button--core-base.ipc-button--single-padding.ipc-button--default-height");
    private By addedIconBy = By.cssSelector("svg.ipc-icon.ipc-icon--done.ipc-button__icon.ipc-button__icon--pre");
    private By startRatingBtnBy = By.xpath("//*[@id=\"star-rating-widget\"]/div");
    private By rateValueBy = By.cssSelector("span.star-rating-value");
    private By removeRatingBtnBy = By.xpath("//*[@id=\"star-rating-widget\"]/div/div/span[1]/a");
    private By starFilledBy = By.cssSelector("span.star-rating-star.rating");
    private By starEmptyBy = By.cssSelector("span.star-rating-star.no-rating");
    private By starsContainerBy = By.xpath("//*[@id=\"star-rating-widget\"]/div/div/span[1]/span");

    public MovieDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAddedIconPresent() {
        return driver.findElement(addedIconBy).isDisplayed();
    }

    public void openRatingWidget() {
        driver.findElement(startRatingBtnBy).click();
    }

    public void setRatingNumber(Integer rateNumber) {
        WebElement starsContainer = driver.findElement(starsContainerBy);
        List<WebElement> stars = starsContainer.findElements(By.tagName("a"));
        WebElement option = stars.get(rateNumber);
        option.click();
    }

    public String ratingValue() {
        return driver.findElement(rateValueBy).getText();
    }

    public void removeRating() {
        driver.findElement(removeRatingBtnBy).click();
    }

    public boolean isRatingSelected() {
        WebElement starFilled = driver.findElement(starFilledBy);
        return (starFilled != null && starFilled.isDisplayed()? true : false);
    }

    public boolean isEmptyStar() {
        WebElement starEmpty = driver.findElement(starFilledBy);
        return (starEmpty != null && starEmpty.isDisplayed()? true : false);
    }

    public void addToWatchlistToggle() {
        driver.findElement(addToWatchlistBtnBy).click();
    }
}
