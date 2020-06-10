package las3007.assignment.tasks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.account.AccountDetailsPageFactory;
import las3007.assignment.factory.account.AccountPageFactory;
import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.login.LoginPageFactory;
import las3007.assignment.factory.login.SigninPageFactory;
import las3007.assignment.pages.account.AccountDetailsPage;
import las3007.assignment.pages.account.AccountPage;
import las3007.assignment.pages.common.MenuPage;
import las3007.assignment.pages.login.LoginPage;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest extends BaseTest {

    private AccountPageFactory accountPageFactory;
    private AccountPage accountPage;
    private AccountDetailsPageFactory accountDetailsPageFactory;
    private AccountDetailsPage accountDetailsPage;
    private SigninPageFactory signinPageFactory;
    private LoginPageFactory loginPageFactory;
    private LoginPage loginPage;
    private MenuPageFactory menuPageFactory;
    private MenuPage menuPage;

    @Before(value = "@account_firefox")
    public void setup_account_firefox() throws MalformedURLException {
        getDriver("firefox");
    }

    @Before(value = "@account_chrome")
    public void setup_account_chrome() throws MalformedURLException {
        getDriver("chrome");
    }

    @Before
    public void setup_account_props() {
        getAccountProperties();
        getAccountDetailsProperties();
    }

    @After
    private void accountTearDownDriver() {
        tearDown();
    }

    @Given("the user is loggedin to an account")
    public void the_user_is_loggedin_to_an_account() {
        signinPageFactory = new SigninPageFactory(driver);
        signinPageFactory.loadSignInPage();
        loginPageFactory = signinPageFactory.signInForExistingAccount();
        loginPageFactory.enterCredentials( email,password);
        loginPageFactory.submitLoginForm();

        menuPageFactory = new MenuPageFactory(driver);
    }

    //2nd option: run with multiple browsers with scenario outline
    @Given("start browser {string} to change account personal details")
    public void start_browser_to_change_account_personal_details(String browser) throws MalformedURLException {
        getDriver(browser);
    }

    @When("the user clicks on account menu list")
    public void the_user_clicks_on_account_menu_list() {
        menuPageFactory.openLoggedinAccountMenu();
    }

    @When("the user clicks on account setting link to be redirected to account page")
    public void the_user_clicks_on_account_setting_link_to_be_redirected_to_account_page() {
        accountPageFactory = menuPageFactory.selectAccountSettingsMenuItem();
    }

    @When("the user clicks on personal details link")
    public void the_user_clicks_on_personal_details_link() {
        accountDetailsPageFactory = accountPageFactory.loadPersonalDetailsPage();
    }

    @When("the user enters personal details")
    public void the_user_enters_personal_details() {
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
}
