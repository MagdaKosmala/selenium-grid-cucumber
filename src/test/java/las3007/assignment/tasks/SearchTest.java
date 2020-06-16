package las3007.assignment.tasks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.common.HomePageFactory;
import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.search.AdvanceResultPageFactory;
import las3007.assignment.factory.search.AdvancedSearchPageFactory;
import las3007.assignment.factory.search.AdvancedTitleSearchPageFactory;
import las3007.assignment.factory.search.SearchPageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest extends BaseTest {

    private HomePageFactory homePage;
    private SearchPageFactory searchPage;
    private AdvancedSearchPageFactory advancedSearchPage;
    private AdvancedTitleSearchPageFactory advancedTitleSearchPage;
    private AdvanceResultPageFactory advanceResultPage;
    private MenuPageFactory menuPage;

    @Given("the user visits home page")
    public void the_user_visits_home_page() {
        homePage = new HomePageFactory(driver);
        homePage.loadHomePage();
        menuPage = new MenuPageFactory(driver);
    }

    @When("the user search for {string}")
    public void the_user_search_for(String searchText) {
        menuPage.enterSearchText(searchText);
    }

    @When("the user leaves empty search field")
    public void the_leaves_empty_search_field() {
        menuPage.enterSearchText("");
    }

    @When("the user clicks on search icon")
    public void the_user_clicks_on_search_icon() {
        searchPage = menuPage.submitSearch();
    }

    @When("the user selects advance search")
    public void the_user_selects_advance_search() {
        menuPage.openSearchCategoryMenu();
        advancedSearchPage = menuPage.selectAdvanceSearch();
    }

    @When("the user clicks advanced title search link")
    public void the_user_clicks_advanced_title_search_link() {
        advancedTitleSearchPage = advancedSearchPage.loadAdvancedTitleSearchPage();
    }

    @When("the user selects oscar winning titles in titles group section")
    public void the_user_selects_oscar_winning_titles_in_titles_group_section() {
        advancedTitleSearchPage.selectGroupsItem();
    }

    @When("the user clicks search button")
    public void the_user_clicks_search_button() {
        advanceResultPage = advancedTitleSearchPage.loadAdvanceResultPage();
    }

    @When("the user selects sort by A-Z descending in display option section")
    public void the_user_selects_sort_by_A_Z_descending_in_display_option_section() {
        advancedTitleSearchPage.selectSortByAZ();
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String pageTitle) {
        assertTrue(driver.getTitle().contains(pageTitle));
    }

    @Then("the user should see in titles section first title {string}")
    public void the_user_should_see_in_titles_section_first_title(String title) {
        assertEquals(title, searchPage.getFirstSearchTitle());
    }

    @Then("the user should see message {string}")
    public void the_user_should_see_message(String message) {
        assertTrue(searchPage.getNoResultMessage().contains(message));
    }

    @Then("the user on advance search page should see header {string}")
    public void the_user_on_advance_search_page_should_see_header(String title) {
        assertEquals(title, advancedSearchPage.getHeaderTitle(title));
    }

    @Then("the user should see page title {string}")
    public void the_user_should_see_page_title(String pageTitle) {
        assertTrue(driver.getTitle().contains(pageTitle));
    }

    @Then("the user should see page header {string}")
    public void the_user_should_see_page_header(String title) {
        assertEquals(title, advancedSearchPage.getHeaderTitle(title));
    }

    @Then("the search result should not be empty")
    public void the_search_result_should_not_be_empty() {
        assertFalse(advanceResultPage.isEmptySearchResult());
    }

    @Then("the first search title should start on letter {string}")
    public void the_first_search_title_should_start_on_letter(String firstLetter) {
        assertEquals(firstLetter, advanceResultPage.getFirstResultTitle().substring(0,1));
    }
}
