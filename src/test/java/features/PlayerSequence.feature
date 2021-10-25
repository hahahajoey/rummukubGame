@tag
Feature: Player Sequence feature
  Test player's game sequence

  @game_sequence
  Scenario: game logic test
    Given Game is on
    And Game start
    When Player end the turn
    Then Current Player is Player 1
    When Player draw
    And Player end the turn
    Then Current Player is Player 2
    Given Player 2 has "JH QH KH" on hand
    When Player 2 place "JH QH KH"
    Then Player 2 's melds has "JH QH KH"
    When Player end the turn
    Given Player 3 has "KH KS KC 2C 2H 2D" on hand
    When Player 3 place "KH KS KC"
    And Player 3 place "2C 2H 2D"
    Then Player 3 's melds has "KH KS KC"
    And Player 3 's melds has "2C 2H 2D"
    When Player end the turn
    Then Current Player is Player 1

