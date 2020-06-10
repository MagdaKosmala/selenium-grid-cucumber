package las3007.assignment.tasks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.utilis.PropertyReader;
import las3007.assignment.factory.common.HomePageFactory;
import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.login.LoginPageFactory;
import las3007.assignment.factory.login.SigninPageFactory;
import las3007.assignment.factory.rating.RatingPageFactory;
import las3007.assignment.factory.search.SearchPageFactory;
import las3007.assignment.factory.watchlist.MovieDetailsPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingTest {

    private WebDriver driver;
    private URL gridUrl;

    private MovieDetailsPageFactory movieDetailsPageFactory;
    private RatingPageFactory ratingPageFactory;
    private MenuPageFactory menuPageFactory;
    private HomePageFactory homePageFactory;
    private SearchPageFactory searchPageFactory;

    private String email, password;
    private SigninPageFactory signinPageFactory;
    private LoginPageFactory loginPageFactory;

    @Before(value = "@rating_firefox")
    public void setup_rating_firefox() throws MalformedURLException {
        getDriver("firefox");
    }

    @Before(value = "@rating_chrome")
    public void setup_rating_chrome() throws MalformedURLException {
        getDriver("chrome");
    }

    @Before
    public void setup_rating_props() throws IOException {
        email = PropertyReader.getValue("email");
        password = PropertyReader.getValue("password");
    }

    @After
    void ratingTearDownDriver() {
        if (driver != null)  driver.quit();

        driver = null;
    }

    @Given("the user is logged in to be able to rate the movie")
    public void the_user_is_logged_in_to_be_able_to_rate_the_movie() {
        signinPageFactory = new SigninPageFactory(driver);
        signinPageFactory.loadSignInPage();
        loginPageFactory = signinPageFactory.signInForExistingAccount();
        loginPageFactory.enterCredentials( email,password);
        loginPageFactory.submitLoginForm();

        menuPageFactory = new MenuPageFactory(driver);
    }

    //2nd option: run with multiple browsers with scenario outline
    @Given("start browser {string} to rate movie")
    public void start_browser_to_rate_movie(String browser) throws MalformedURLException {
        getDriver(browser);
    }

    @When("the user searches the movie {string}")
    public void the_user_searches_the_movie(String title) {
        menuPageFactory.enterSearchText(title);
        searchPageFactory = menuPageFactory.submitSearch();
    }

    @When("the user clicks on the title to be redirect to movie detail page")
    public void the_user_clicks_on_the_title_to_be_redirect_to_movie_detail_page() {
        movieDetailsPageFactory = searchPageFactory.loadMovieDetailsPage();
    }

    @When("the user clicks on star rating button")
    public void the_user_clicks_on_star_rating_button() {
        movieDetailsPageFactory.openRatingWidget();
    }

    @When("the user rates the movie with number {int}")
    public void the_user_rates_the_movie_with_number(Integer rateNumber) {
        movieDetailsPageFactory.setRatingNumber(rateNumber-1);
    }

    @When("the user clicks on delete star button")
    public void the_user_clicks_on_delete_star_button() {
        movieDetailsPageFactory.removeRating();
    }

    @When("the user clicks on account header menu")
    public void the_user_clicks_on_account_header_menu() {
        menuPageFactory.openLoggedinAccountMenu();
    }

    @When("the user selects your rating link from menu list")
    public void the_user_selects_your_rating_link_from_menu_list() {
        ratingPageFactory = menuPageFactory.selectRatingMenuItem();
    }

    @When("the user filters ratings by {int} stars")
    public void the_user_filters_ratings_by_stars(Integer starsNumber) {
        ratingPageFactory.filterRatingsByNumber(starsNumber);
    }

    @Then("the rating was added")
    public void the_rating_was_added() {
        assertTrue(movieDetailsPageFactory.isRatingSelected());
    }

    @Then("the user should see the rate number {int}")
    public void the_user_should_see_the_rate_number(Integer rateNumber) {
        assertEquals(String.valueOf(rateNumber), movieDetailsPageFactory.ratingValue());
    }

    @Then("rating should be removed")
    public void rating_should_be_removed() {
        assertTrue(movieDetailsPageFactory.isEmptyStar());
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        assertTrue(driver.getTitle().contains(title));
    }

    @Then("the user should see header {string}")
    public void the_user_should_see_header(String title) {
        assertEquals(title, ratingPageFactory.getHeaderText());
    }

    @Then("the user should see list size equals to {int}")
    public void the_user_should_see_list_size_equals_to(Integer listSize) {
        assertTrue(ratingPageFactory.getRatingListSize().contains(String.valueOf(listSize)));
    }

    public void getDriver(String browser) throws MalformedURLException {
        if (driver == null) {
            createDriver(browser);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
    }

    private void createDriver(String browser) throws MalformedURLException {
        gridUrl = new URL("http://localhost:4444/wd/hub");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName(browser);
        driver = new RemoteWebDriver(gridUrl, dc);
    }
}
