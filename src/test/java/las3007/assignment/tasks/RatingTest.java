package las3007.assignment.tasks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.rating.RatingPageFactory;
import las3007.assignment.factory.search.SearchPageFactory;
import las3007.assignment.factory.common.MovieDetailsPageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RatingTest extends BaseTest {

    private MovieDetailsPageFactory movieDetailsPage;
    private RatingPageFactory ratingPage;
    private SearchPageFactory searchPage;

    @Given("the user is logged in to rate the movie")
    public void the_user_is_logged_in_to_rate_the_movie() {
        loginUser();
    }

    @When("the user searches the movie {string}")
    public void the_user_searches_the_movie(String title) {
        menuPageFactory.enterSearchText(title);
        searchPage = menuPageFactory.submitSearch();
    }

    @When("the user clicks on the title to be redirect to movie detail page")
    public void the_user_clicks_on_the_title_to_be_redirect_to_movie_detail_page() {
        movieDetailsPage = searchPage.loadMovieDetailsPage();
    }

    @When("the user clicks on star rating button")
    public void the_user_clicks_on_star_rating_button() {
        movieDetailsPage.openRatingWidget();
    }

    @When("the user rates the movie with number {int}")
    public void the_user_rates_the_movie_with_number(Integer rateNumber) {
        movieDetailsPage.setRatingNumber(rateNumber-1);
    }

    @When("the user clicks on delete star button")
    public void the_user_clicks_on_delete_star_button() {
        movieDetailsPage.removeRating();
    }

    @When("the user clicks on account header menu")
    public void the_user_clicks_on_account_header_menu() {
        menuPageFactory.openLoggedinAccountMenu();
    }

    @When("the user selects your rating link from menu list")
    public void the_user_selects_your_rating_link_from_menu_list() {
        ratingPage = menuPageFactory.selectRatingMenuItem();
    }

    @When("the user filters ratings by {int} stars")
    public void the_user_filters_ratings_by_stars(Integer starsNumber) {
        ratingPage.filterRatingsByNumber(starsNumber);
    }

    @Then("the rating was added")
    public void the_rating_was_added() {
        assertTrue(movieDetailsPage.isRatingSelected());
    }

    @Then("the user should see the rate number {int}")
    public void the_user_should_see_the_rate_number(Integer rateNumber) {
        assertEquals(String.valueOf(rateNumber), movieDetailsPage.ratingValue());
    }

    @Then("rating should be removed")
    public void rating_should_be_removed() {
        assertTrue(movieDetailsPage.isEmptyStar());
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        assertTrue(driver.getTitle().contains(title));
    }

    @Then("the user should see header {string}")
    public void the_user_should_see_header(String title) {
        assertEquals(title, ratingPage.getHeaderText());
    }

    @Then("the user should see list size equals to {int}")
    public void the_user_should_see_list_size_equals_to(Integer listSize) {
        assertTrue(ratingPage.getRatingListSize().contains(String.valueOf(listSize)));
    }
}
