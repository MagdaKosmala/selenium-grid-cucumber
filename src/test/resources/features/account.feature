Feature: Account test
  Background: User is logged in to change personal details
    Given the user is loggedin to an account
    And the user clicks on account menu list
    And the user clicks on account setting link to be redirected to account page

  Scenario: User visits account page
    Then the user should see link to personal details page

  Scenario: User submits personal details
    When the user clicks on personal details link
    And the user enters personal details
    And the user clicks on submit personal details button
    Then the user should see success message "You have successfully updated your account details!"
    And the account details page title should be "Change your Details - IMDb"
    And the user should see account details page header "Change your Details"
