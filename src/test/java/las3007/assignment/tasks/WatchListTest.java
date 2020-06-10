package las3007.assignment.tasks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.login.LoginPageFactory;
import las3007.assignment.factory.login.SigninPageFactory;
import las3007.assignment.factory.search.SearchPageFactory;
import las3007.assignment.factory.watchlist.MovieDetailsPageFactory;
import las3007.assignment.factory.watchlist.WatchlistPageFactory;
import las3007.assignment.utilis.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class WatchListTest {

    private URL gridUrl;
    private WebDriver driver;

    private SigninPageFactory signinPageFactory;
    private LoginPageFactory loginPageFactory;
    private MenuPageFactory menuPageFactory;
    private WatchlistPageFactory watchlistPageFactory;
    private SearchPageFactory searchPageFactory;
    private MovieDetailsPageFactory movieDetailsPageFactory;

    private String email, password;

    @Before(value = "@watchlist_firefox")
    public void setup_watchlist_firefox() throws MalformedURLException {
        getDriver("firefox");
    }

    @Before(value = "@watchlist_chrome")
    public void setup_watchlist_chrome() throws MalformedURLException {
        getDriver("chrome");
    }

    @Before
    public void setup_watchlist_props() throws IOException {
        email = PropertyReader.getValue("email");
        password = PropertyReader.getValue("password");
    }

    @After
    private void watchlistTearDownDriver() {
//        if (Optional.ofNullable(driver).isPresent()) {
//            driver.quit();
//        }
        if (driver != null)  driver.quit();
        driver = null;
    }

    @Given("the loggedin user visits home page")
    public void the_loggedin_user_visits_home_page() {
        signinPageFactory = new SigninPageFactory(driver);
        signinPageFactory.loadSignInPage();
        loginPageFactory = signinPageFactory.signInForExistingAccount();
        loginPageFactory.enterCredentials( email,password);
        loginPageFactory.submitLoginForm();

        menuPageFactory = new MenuPageFactory(driver);
    }

    //2nd option: run with multiple browsers with scenario outline
    @Given("start browser {string} for watchlist")
    public void start_browser_for_watchlist(String browser) throws MalformedURLException {
        getDriver(browser);
    }

    @When("the user searched for movie {string}")
    public void the_user_searched_for_movie(String movieTitle){
        menuPageFactory.enterSearchText(movieTitle);
        searchPageFactory = menuPageFactory.submitSearch();
    }

    @When("the user clicks title link to redirect to movie details page")
    public void the_user_clicks_title_link_to_redirect_to_movie_details_page(){
        movieDetailsPageFactory = searchPageFactory.loadMovieDetailsPage();
    }

    @When("the user clicks on add to watchlist button")
    public void the_user_clicks_on_add_to_watchlist_button(){
        movieDetailsPageFactory.addToWatchlistToggle();
    }

    @When("the user goes to watchlist page")
    public void the_user_goes_to_watchlist_page(){
        watchlistPageFactory = menuPageFactory.loadWatchlistPage();
    }

    @When("the user clicks on watchlist link")
    public void the_user_clicks_on_watchlist_link(){
        watchlistPageFactory.openWatchlist();
    }

    @When("the user removes the movie from watchlist")
    public void the_user_clicks_to_remove_from_watchlist() {
        watchlistPageFactory.deleteToggle();
    }

    @When("the user changes list order")
    public void the_user_changes_list_order() {
        watchlistPageFactory.changeListOrder();
    }

    @When("the user switches to grid view")
    public void the_user_switches_to_grid_view() {
        watchlistPageFactory.changeViewMode();
    }

    @When("the user refines the list")
    public void the_user_refines_the_list() {
        watchlistPageFactory.refineWatchlist();
    }


    @Then("the movie should be added to watchlist")
    public void the_movie_should_be_added_to_watchlist(){
        assertTrue(movieDetailsPageFactory.isAddedIconPresent());
    }

    @Then("the user should be redirect to watchlist with header {string}")
    public void the_user_should_be_redirect_to_watchlist(String header){
        assertEquals(header, watchlistPageFactory.getHeaderText());
    }
    @Then("the watchlist should contain movie {string}")
    public void the_watchlist_should_contain_movie(String title){
        assertTrue(watchlistPageFactory.isMoveInWatchlist(title));
    }

    @Then("the movie {string} should be removed from watchlist")
    public void the_movie_should_be_removed_from_watchlist(String title){
        assertFalse(watchlistPageFactory.isMoveInWatchlist(title));
    }

    @Then("movie plot should not be visible")
    public void movie_plot_should_not_be_visible(){
        assertTrue(watchlistPageFactory.isGridView());
    }

    @Then("the user should see number of movies {int} and {int} series")
    public void the_user_should_see_number_of_movies_and_series(Integer moviesNum, Integer seriesNum) {
        assertEquals(moviesNum, watchlistPageFactory.getWatchlistMoviesCount());
        assertEquals(seriesNum, watchlistPageFactory.getWatchlistSeriesCount());
    }

    public WebDriver getDriver(String browser) throws MalformedURLException {
        if (driver == null) {
            driver = createDriver(browser);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }

        return driver;
    }

    private WebDriver createDriver(String browser) throws MalformedURLException {
        gridUrl = new URL("http://localhost:4444/wd/hub");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName(browser);
        driver = new RemoteWebDriver(gridUrl, dc);

        return driver;
    }
}
