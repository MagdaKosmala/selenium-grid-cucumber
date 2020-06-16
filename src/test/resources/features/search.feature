Feature: Search
  Background:
    Given the user visits home page

  Scenario: User searches for the movie
    Given the user visits home page
    When the user search for "Bridget Jones"
    And the user clicks on search icon
    Then page title should be "Find - IMDb"
    And the user should see in titles section first title "Bridget Jones's Diary (2001)"

  Scenario: User searches the movie with advance search option
    When the user selects advance search
    Then the user on advance search page should see header "Advanced Search"

  Scenario: Uses selects advance title search
    When the user selects advance search
    And the user clicks advanced title search link
    Then the user should see page title "IMDb: Advanced Title Search - IMDb"
    And the user should see page header "Advanced Title Search"

  Scenario: Result of advance search is not empty
    When the user selects advance search
    And the user clicks advanced title search link
    And the user selects oscar winning titles in titles group section
    And the user clicks search button
    Then the search result should not be empty

  Scenario: Change search order
    When the user selects advance search
    And the user clicks advanced title search link
    And the user selects oscar winning titles in titles group section
    And the user selects sort by A-Z descending in display option section
    And the user clicks search button
    Then the first search title should start on letter "Z"

  Scenario: Empty search
    When the user leaves empty search field
    And the user clicks on search icon
    Then the user should see message "Enter a word or phrase to search on in the form at the top of the page."
