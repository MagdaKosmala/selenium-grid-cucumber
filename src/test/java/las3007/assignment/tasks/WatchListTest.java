package las3007.assignment.tasks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.search.SearchPageFactory;
import las3007.assignment.factory.common.MovieDetailsPageFactory;
import las3007.assignment.factory.watchlist.WatchlistPageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class WatchListTest extends BaseTest {

    private WatchlistPageFactory watchlistPageFactory;
    private SearchPageFactory searchPageFactory;
    private MovieDetailsPageFactory movieDetailsPageFactory;

    @Given("the user is loggedin to visit watchlist")
    public void the_user_is_logged_in_to_visit_watchlist() {
        loginUser();
    }

    @When("the user searched for movie {string}")
    public void the_user_searched_for_movie(String movieTitle) {
        menuPageFactory.enterSearchText(movieTitle);
        searchPageFactory = menuPageFactory.submitSearch();
    }

    @When("the user clicks title link to redirect to movie details page")
    public void the_user_clicks_title_link_to_redirect_to_movie_details_page() {
        movieDetailsPageFactory = searchPageFactory.loadMovieDetailsPage();
    }

    @When("the user clicks on add to watchlist button")
    public void the_user_clicks_on_add_to_watchlist_button() {
        movieDetailsPageFactory.addToWatchlistToggle();
    }

    @When("the user goes to watchlist page")
    public void the_user_goes_to_watchlist_page() {
        watchlistPageFactory = menuPageFactory.loadWatchlistPage();
    }

    @When("the user clicks on watchlist link")
    public void the_user_clicks_on_watchlist_link() {
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
    public void the_movie_should_be_added_to_watchlist() {
        assertTrue(movieDetailsPageFactory.isAddedIconPresent());
    }

    @Then("the user should be redirect to watchlist with header {string}")
    public void the_user_should_be_redirect_to_watchlist(String header) {
        assertEquals(header, watchlistPageFactory.getHeaderText());
    }

    @Then("the watchlist should contain movie {string}")
    public void the_watchlist_should_contain_movie(String title) {
        assertTrue(watchlistPageFactory.isMoveInWatchlist(title));
    }

    @Then("the movie {string} should be removed from watchlist")
    public void the_movie_should_be_removed_from_watchlist(String title) {
        assertFalse(watchlistPageFactory.isMoveInWatchlist(title));
    }

    @Then("movie plot should not be visible")
    public void movie_plot_should_not_be_visible() {
        assertTrue(watchlistPageFactory.isGridView());
    }

    @Then("the user should see number of movies {int} and {int} series")
    public void the_user_should_see_number_of_movies_and_series(Integer moviesNum, Integer seriesNum) {
        assertEquals(moviesNum, watchlistPageFactory.getWatchlistMoviesCount());
        assertEquals(seriesNum, watchlistPageFactory.getWatchlistSeriesCount());
    }
}