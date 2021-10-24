@tag
Feature: Joker's functionality
Test every functionality for joker

  @draw_joker
  Scenario: Player could draw joker from deck
    Given Game is on
    When Player 1 draw "*"
    Then Player 1 's hand has "*"
    And Deck has remain 105

  @place_joker
  Scenario: Player could place joker from hand
    Given Game is on
    And Player 1 has "* 1G 2G" on hand
    When Player 1 place "1G 2G *"
    Then Player 1 's melds has "1G 2G *"

  @reuse_joker_from_melds
  Scenario: Player could reuse joker from melds
    Given Game is on
    And Player 1 has "7G 7O" on hand
    And Player 1 has "1G, 2G, 3G, *" melds
    When Player 1 reuse "*" from Player 1 meld 1
    And Player 1 place "7G 7O *"
    Then Player 1 's melds has "1G, 2G, 3G", "7G, 7O, *"

  @place_joker_into_melds
  Scenario: Player could reuse joker from melds
    Given Game is on
    And Player 1 has "*" on hand
    And Player 1 has "1G, 2G, 3G" melds
    When Player 1 place "*" into Player 1 meld 1
    Then Player 1 's melds has "1G 2G 3G *"