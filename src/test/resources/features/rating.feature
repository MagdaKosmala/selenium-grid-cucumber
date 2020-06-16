Feature: Rating
  Background: User is Logged In to rate movies
    Given the user is logged in to rate the movie

  Scenario Outline: The user rates the movie and changes rating number
    When the user searches the movie "Bohemian Rhapsody"
    And the user clicks on the title to be redirect to movie detail page
    And the user clicks on star rating button
    And the user rates the movie with number <rating_number>
    Then the rating was added
    And the user should see the rate number <rating_number>

      Examples:
      | rating_number |
      | 7             |
      | 8             |

  Scenario: The user removes rating
    When the user searches the movie "Bohemian Rhapsody"
    And the user clicks on the title to be redirect to movie detail page
    And the user clicks on star rating button
    And the user clicks on delete star button
    Then rating should be removed

  Scenario Outline: the user filters ratings by specific star rating
    When the user clicks on account header menu
    And the user selects your rating link from menu list
    And the user filters ratings by <rating_stars> stars
    Then the user should see list size equals to <list_size>

    Examples:
      | rating_stars | list_size |
      | 9            | 2         |
      | 7            | 1         |
