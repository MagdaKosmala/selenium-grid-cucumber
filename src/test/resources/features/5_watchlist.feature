Feature: Watchlist
  Background: User is logged in
    Given the loggedin user visits home page

  @watchlist_chrome
  Scenario: User adds movie to watchlist
    When the user searched for movie "Bohemian Rhapsody"
    And the user clicks title link to redirect to movie details page
    And the user clicks on add to watchlist button
    Then the movie should be added to watchlist

  @watchlist_chrome
  Scenario: User navigates to watchlist
    When the user goes to watchlist page
    Then the user should be redirect to watchlist with header "Your Watchlist"
    And the watchlist should contain movie "Bohemian Rhapsody"

  @watchlist_chrome
  Scenario: User removes movie from watchlist
    When the user goes to watchlist page
    And the user changes list order
    And the user removes the movie from watchlist
    And the user clicks on watchlist link
    Then the movie "Bohemian Rhapsody" should be removed from watchlist

  @watchlist_firefox
  Scenario: User changes view from list to grid view
    When the user goes to watchlist page
    And the user switches to grid view
    Then movie plot should not be visible

  @watchlist_firefox
  Scenario: User refines the list
    When the user goes to watchlist page
    And the user refines the list
    Then the user should see number of movies 2 and 2 series
