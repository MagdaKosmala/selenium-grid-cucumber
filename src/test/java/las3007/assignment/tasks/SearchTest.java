package las3007.assignment.tasks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.common.HomePageFactory;
import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.search.AdvanceResultPageFactory;
import las3007.assignment.factory.search.AdvancedSearchPageFactory;
import las3007.assignment.factory.search.AdvancedTitleSearchPageFactory;
import las3007.assignment.factory.search.SearchPageFactory;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest extends BaseTest {

    private HomePageFactory homePageFactory;
    private SearchPageFactory searchPageFactory;
    private AdvancedSearchPageFactory advancedSearchPageFactory;
    private AdvancedTitleSearchPageFactory advancedTitleSearchPageFactory;
    private AdvanceResultPageFactory advanceResultPageFactory;
    private MenuPageFactory menuPageFactory;

    @Before(value = "@search_firefox")
    public void setup_account_firefox() throws MalformedURLException {
        getDriver("firefox");
    }

    @Before(value = "@search_chrome")
    public void setup_account_chrome() throws MalformedURLException {
        getDriver("chrome");
    }

    @After
    private void searchTearDownDriver() {
        tearDown();
    }

    @Given("the user visits home page")
    public void the_user_visits_home_page() {
        homePageFactory = new HomePageFactory(driver);
        homePageFactory.loadHomePage();
        menuPageFactory = new MenuPageFactory(driver);
    }

    //2nd option: run with multiple browsers with scenario outline
    @Given("start browser {string} to search a movie")
    public void start_browser_to_search_a_movie(String browser) throws MalformedURLException {
        getDriver(browser);
    }
    @When("the user search for {string}")
    public void the_user_search_for(String searchText) {
        menuPageFactory.enterSearchText(searchText);
    }

    @When("the user leaves empty search field")
    public void the_leaves_empty_search_field() {
        menuPageFactory.enterSearchText("");
    }

    @When("the user clicks on search icon")
    public void the_user_clicks_on_search_icon() {
        searchPageFactory = menuPageFactory.submitSearch();
    }

    @When("the user selects advance search")
    public void the_user_selects_advance_search() {
        menuPageFactory.openSearchCategoryMenu();
        advancedSearchPageFactory = menuPageFactory.selectAdvanceSearch();
    }

    @When("the user clicks advanced title search link")
    public void the_user_clicks_advanced_title_search_link() {
        advancedTitleSearchPageFactory = advancedSearchPageFactory.loadAdvancedTitleSearchPage();
    }

    @When("the user selects oscar winning titles in titles group section")
    public void the_user_selects_oscar_winning_titles_in_titles_group_section() {
        advancedTitleSearchPageFactory.selectGroupsItem();
    }

    @When("the user clicks search button")
    public void the_user_clicks_search_button() {
        advanceResultPageFactory = advancedTitleSearchPageFactory.loadAdvanceResultPage();
    }

    @When("the user selects sort by A-Z descending in display option section")
    public void the_user_selects_sort_by_A_Z_descending_in_display_option_section() {
        advancedTitleSearchPageFactory.selectSortByAZ();
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String pageTitle) {
        assertTrue(driver.getTitle().contains(pageTitle));
    }

    @Then("the user should see in titles section first title {string}")
    public void the_user_should_see_in_titles_section_first_title(String title) {
        assertEquals(title, searchPageFactory.getFirstSearchTitle());
    }

    @Then("the user should see message {string}")
    public void the_user_should_see_message(String message) {
        assertTrue(searchPageFactory.getNoResultMessage().contains(message));
    }

    @Then("the user on advance search page should see header {string}")
    public void the_user_on_advance_search_page_should_see_header(String title) {
        assertEquals(title, advancedSearchPageFactory.getHeaderTitle(title));
    }

    @Then("the user should see page title {string}")
    public void the_user_should_see_page_title(String pageTitle) {
        assertTrue(driver.getTitle().contains(pageTitle));
    }

    @Then("the user should see page header {string}")
    public void the_user_should_see_page_header(String title) {
        assertEquals(title, advancedSearchPageFactory.getHeaderTitle(title));
    }

    @Then("the search result should not be empty")
    public void the_search_result_should_not_be_empty() {
        assertFalse(advanceResultPageFactory.isEmptySearchResult());
    }

    @Then("the first search title should start on letter {string}")
    public void the_first_search_title_should_start_on_letter(String firstLetter) {
        assertEquals(firstLetter, advanceResultPageFactory.getFirstResultTitle().substring(0,1));
    }
}
