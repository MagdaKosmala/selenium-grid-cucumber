package las3007.assignment.tasks;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import las3007.assignment.factory.common.MenuPageFactory;
import las3007.assignment.factory.login.ActivityPageFactory;
import las3007.assignment.factory.login.LoginPageFactory;
import las3007.assignment.factory.login.SigninPageFactory;
import las3007.assignment.utilis.PropertyReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LoginTest extends BaseTest{

    private ActivityPageFactory activityPage;
    private SigninPageFactory signinPage;
    private LoginPageFactory loginPage;
    private MenuPageFactory menuPage;

    @Before("@login")
    public void login_setup() {
        getAccountProperties();
    }

    @Given("the user visits the signIn page")
    public void the_user_visits_the_signIn_page() {
        signinPage = new SigninPageFactory(driver);
        signinPage.loadSignInPage();
    }

    @When("the user clicks the Sign in with IMDb button")
    public void the_user_clicks_the_Sign_in_with_IMDb_button() {
        loginPage = signinPage.signInForExistingAccount();
    }

    @When("the user leaves empty form fields")
    public void the_user_leaves_empty_form_fields() {
        loginPage.enterCredentials("", "");
    }

    @When("the user enters incorrect password")
    public void the_user_enters_incorrect_password() {
        loginPage.enterCredentials(email, "s313n1um_incorrect");
    }

    @When("the user enters correct credentials")
    public void the_user_enters_correct_password() {
        loginPage.enterCredentials(email, password);
    }

    @When("the user clicks on sign in button")
    public void the_user_clicks_on_sign_in_button() {
        loginPage.submitLoginForm();
    }

    @When("the loggedin user clicks on account menu list")
    public void the_loggedin_user_clicks_on_account_menu_list() {
        menuPage = new MenuPageFactory(driver);
        menuPage.openLoggedinAccountMenu();
    }

    @When("the loggedin user selects first link to be redirected to profile page")
    public void the_loggedin_user_selects_first_link_to_be_redirected_to_profile_page() {
        activityPage = menuPage.selectActivityMenuItem();
    }

    @When("the loggedin user selects last link to sign out")
    public void the_loggedin_user_selects_last_link_to_sign_out() {
        menuPage.signOut();
    }

    @Then("the error message should be {string}")
    public void the_error_message_should_be(String errorMessage) {
        assertEquals(errorMessage, loginPage.loginFailedMessage());
    }

    @Then("the the user should get list of error messages")
    public void the_user_should_get_list_of_error_messages(List<String> errorMessages) {
        assertTrue(loginPage.matchMessages(errorMessages));
    }

    @Then("the user should see username in menu")
    public void the_user_should_see_username_in_menu() throws IOException {
        String username = PropertyReader.getValue("username");
        menuPage = new MenuPageFactory(driver);

        assertTrue(menuPage.getLoggedinUsername().contains(username));
    }

    @Then("the loggedin user should see on profile page link to edit profile")
    public void the_loggedin_user_should_see_on_profile_page_link_to_edit_profile() {
        assertNotNull(activityPage.getEditProfileLink());
    }

    @Then("the user should see on the page sign in button")
    public void the_user_should_see_on_the_page_sign_in_button() {
        assertNotNull(menuPage.getSignInButton());
    }
}