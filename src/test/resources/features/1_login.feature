Feature: Login test
  Background: User visits sign in page
    Given  the user visits the signIn page
    And the user clicks the Sign in with IMDb button

  @login_chrome
  Scenario: User submits empty credentials to login
    When  the user leaves empty form fields
    And the user clicks on sign in button
    Then the the user should get list of error messages
      | Enter your email     |
      | Enter your password  |

  @login_chrome
  Scenario: User submits incorrect credentials to login
    When the user enters incorrect password
    And the user clicks on sign in button
    Then the error message should be "Your password is incorrect"

  @login_firefox
  Scenario: User login successfully
    When the user enters correct credentials
    And the user clicks on sign in button
    And the loggedin user clicks on account menu list
    And the loggedin user selects first link to be redirected to profile page
    Then the user should see username in menu
    Then the loggedin user should see on profile page link to edit profile

  @login_firefox
  Scenario: User logout
    When the user enters correct credentials
    And the user clicks on sign in button
    And the loggedin user clicks on account menu list
    And the loggedin user selects last link to sign out
    Then the user should see on the page sign in button