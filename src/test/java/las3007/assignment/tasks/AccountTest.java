package las3007.assignment.tasks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.account.AccountDetailsPageFactory;
import las3007.assignment.factory.account.AccountPageFactory;
import las3007.assignment.utilis.PropertyReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest extends BaseTest {
    private String dayOfBirth, monthOfBirth, yearOfBirth, country, gender; //gender value: f or m

    private AccountPageFactory accountPageFactory;
    private AccountDetailsPageFactory accountDetailsPageFactory;

    @Given("the user is loggedin to an account")
    public void the_user_is_loggedin_to_an_account() {
        loginUser();
    }

    @Given("the user clicks on account menu list")
    public void the_user_clicks_on_account_menu_list() {
        menuPageFactory.openLoggedinAccountMenu();
    }

    @Given("the user clicks on account setting link to be redirected to account page")
    public void the_user_clicks_on_account_setting_link_to_be_redirected_to_account_page() {
        accountPageFactory = menuPageFactory.selectAccountSettingsMenuItem();
    }

    @When("the user clicks on personal details link")
    public void the_user_clicks_on_personal_details_link() {
        accountDetailsPageFactory = accountPageFactory.loadPersonalDetailsPage();
    }

    @When("the user enters personal details")
    public void the_user_enters_personal_details() {
        getAccountDetailsProperties();
        accountDetailsPageFactory.enterAccountDetails(gender,dayOfBirth, monthOfBirth, yearOfBirth, country);
    }

    @When("the user clicks on submit personal details button")
    public void the_user_clicks_on_submit_personal_details_button() {
        accountDetailsPageFactory.submitAccountDetails();
    }

    @Then("the user should see link to personal details page")
    public void the_user_should_see_on_link_to_personal_details_page() {
        assertNotNull(accountPageFactory.getPersonalDetailsLink());
    }

    @Then("the account details page title should be {string}")
    public void the_account_details_page_title_should_be(String pageTitle) {
        assertTrue(driver.getTitle().contains(pageTitle));
    }

    @Then("the user should see account details page header {string}")
    public void the_user_should_see_account_details_page_header(String headerTitle){
        assertEquals(headerTitle, accountDetailsPageFactory.getHeaderTitle());
    }

    @Then("the user should see success message {string}")
    public void the_user_should_see_success_message(String successMessage) {
        assertEquals(successMessage, accountDetailsPageFactory.getUpdateDetailsSuccessMessage());
    }

    private void getAccountDetailsProperties() {
        try {
            dayOfBirth = PropertyReader.getValue("day");
            monthOfBirth = PropertyReader.getValue("month");
            yearOfBirth = PropertyReader.getValue("year");
            country = PropertyReader.getValue("country");
            gender = PropertyReader.getValue("gender");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
