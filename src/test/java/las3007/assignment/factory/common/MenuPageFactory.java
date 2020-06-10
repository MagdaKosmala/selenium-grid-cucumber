package las3007.assignment.factory.common;

import las3007.assignment.factory.account.AccountPageFactory;
import las3007.assignment.factory.login.ActivityPageFactory;
import las3007.assignment.factory.rating.RatingPageFactory;
import las3007.assignment.factory.search.AdvancedSearchPageFactory;
import las3007.assignment.factory.search.SearchPageFactory;
import las3007.assignment.factory.watchlist.WatchlistPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPageFactory {
    protected WebDriver driver;

    @FindBy(css="span.imdb-header__account-toggle--logged-in")
    private WebElement loggedinAccoutHeader;

    @FindBy(linkText = "Your activity")
    private WebElement activityMenuItem;

    @FindBy(linkText = "Sign out")
    private WebElement menuItemSignOut;

    @FindBy(linkText="Sign In")
    private WebElement signInBtn;

    @FindBy(linkText = "Account settings")
    private WebElement accountSettingsMenuItem;

    @FindBy(linkText = "Your ratings")
    private WebElement ratingMenuItem;

    @FindBy(xpath = "//*[@id=\"imdbHeader\"]/div[2]/div[4]/a")
    private WebElement watchlistMenuItem;

    @FindBy(id="suggestion-search")
    private WebElement searchFld;

    @FindBy(id="suggestion-search-button")
    private WebElement searchBtn;

    @FindBy(css="div.search-category-selector")
    private WebElement categorySelectorBtn;

    @FindBy(linkText = "Advanced Search")
    private WebElement advanceMenuItem;

    public MenuPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLoggedinUsername() {
        return loggedinAccoutHeader.getText();
    }

    public void openLoggedinAccountMenu() {
        loggedinAccoutHeader.click();
    }

    public ActivityPageFactory selectActivityMenuItem() {
        activityMenuItem.click();
        return new ActivityPageFactory(driver);
    }

    public void signOut() {
        menuItemSignOut.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement getSignInButton() {
        return signInBtn;
    }

    public AccountPageFactory selectAccountSettingsMenuItem() {
        accountSettingsMenuItem.click();
        return new AccountPageFactory(driver);
    }

    public RatingPageFactory selectRatingMenuItem() {
        ratingMenuItem.click();
        return new RatingPageFactory(driver);
    }

    public WatchlistPageFactory loadWatchlistPage() {
        watchlistMenuItem.click();
        return new WatchlistPageFactory(driver);
    }

    public void enterSearchText(String text) {
        searchFld.clear();
        searchFld.sendKeys(text);
    }

    public SearchPageFactory submitSearch() {
        searchBtn.click();
        return new SearchPageFactory(driver);
    }

    public void openSearchCategoryMenu() {
        categorySelectorBtn.click();
    }

    public AdvancedSearchPageFactory selectAdvanceSearch() {
        advanceMenuItem.click();
        return new AdvancedSearchPageFactory(driver);
    }
}
