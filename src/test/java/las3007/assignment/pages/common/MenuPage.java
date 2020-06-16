package las3007.assignment.pages.common;

import las3007.assignment.pages.account.AccountPage;
import las3007.assignment.pages.login.ActivityPage;
import las3007.assignment.pages.rating.RatingPage;
import las3007.assignment.pages.search.AdvancedSearchPage;
import las3007.assignment.pages.search.SearchPage;
import las3007.assignment.pages.watchlist.WatchlistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage {
    protected WebDriver driver;

    private By loggedinAccoutHeaderBy = By.cssSelector("span.imdb-header__account-toggle--logged-in");
    private By activityMenuItemBy = By.linkText("Your activity");
    private By menuItemSignOutBy = By.linkText("Sign out");
    private By signInBtnBy = By.linkText("Sign In");
    private By accountSettingsMenuItemBy = By.linkText("Account settings");
    private By ratingMenuItemBy = By.linkText("Your ratings");
    private By watchlistMenuItemBy = By.xpath("//*[@id=\"imdbHeader\"]/div[2]/div[4]/a");
    private By searchFldBy = By.id("suggestion-search");
    private By searchBtnBy = By.id("suggestion-search-button");
    private By categorySelectorBtnBy = By.cssSelector("div.search-category-selector");
    private By advanceMenuItemBy = By.linkText("Advanced Search");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoggedinUsername() {
        return driver.findElement(loggedinAccoutHeaderBy).getText();
    }

    public void openLoggedinAccountMenu() {
        driver.findElement(loggedinAccoutHeaderBy).click();
    }

    public ActivityPage selectActivityMenuItem() {
        driver.findElement(activityMenuItemBy).click();
        return new ActivityPage(driver);
    }

    public void signOut() {
        driver.findElement(menuItemSignOutBy).click();

    }

    public WebElement getSignInButton() {
        return driver.findElement(signInBtnBy);
    }

    public AccountPage selectAccountSettingsMenuItem() {
        driver.findElement(accountSettingsMenuItemBy).click();
        return new AccountPage(driver);
    }

    public RatingPage selectRatingMenuItem() {
        driver.findElement(ratingMenuItemBy).click();
        return new RatingPage(driver);
    }

    public WatchlistPage loadWatchlistPage() {
        driver.findElement(watchlistMenuItemBy).click();
        return new WatchlistPage(driver);
    }

    public void enterSearchText(String text) {
        driver.findElement(searchFldBy).clear();
        driver.findElement(searchFldBy).sendKeys(text);
    }

    public SearchPage submitSearch() {
        driver.findElement(searchBtnBy).click();
        return new SearchPage(driver);
    }

    public void openSearchCategoryMenu() {
        driver.findElement(categorySelectorBtnBy).click();
    }

    public AdvancedSearchPage selectAdvanceSearch() {
        driver.findElement(advanceMenuItemBy).click();
        return new AdvancedSearchPage(driver);
    }
}
