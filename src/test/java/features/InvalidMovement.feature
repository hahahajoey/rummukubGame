@tag
Feature: Invalid movement blocker
  Test the function to block every invalid movement

  @less_than_3_tiles
  Scenario: Player end up with less than 3 tiles in a meld
    Given Game is on
    And Player 1 has "AG 2G" on hand
    When Player 1 place "AG 2G"
    And Player end the turn
    Then Player 1 's melds do not has "AG 2G"

  @run_became_not_valid
  Scenario: Player massed up a valid run
    Given Game is on
    And Player 1 has "AG 2G 3G" melds
    When Player 1 place "4S" into Player 1 meld 1
    And Player end the turn
    Then Player 1 's melds do not has "AG 2G 3G 4S"

  @set_became_not_valid
  Scenario: Player massed up a valid set
    Given Game is on
    And Player 1 has "KH KC KS" melds
    When Player 1 place "4D" into Player 1 meld 1
    And Player end the turn
    Then Player 1 's melds do not has "KH KC KS 4D"

  @meld_has_too_less_tiles
  Scenario: Player made a meld has too less tiles
    Given Game is on
    And Player 1 has "JH QH" on hand
    And Player 1 has "KH KC KS" melds
    When Player 1 reuse "KH" from Player 1 meld 1
    And Player 1 place "JH QH KH"
    And Player end the turn
    Then Player 1 's melds do not has "KC KS"
    And Player 1 's melds do not has "JH QH KH"

  @player_place_not_exist_tile
  Scenario: Player placed tiles that are not in his hand
    Given Game is on
    And Player 1 has "AG 2G 3G" on hand
    When Player 1 place "3G 4G 5G"
    And Player end the turn
    Then Player 1 's melds do not has "3G 4G 5G"

  @player_reuse_not_exist_tile
  Scenario: Player reuse tiles that are not in his hand
    Given Game is on
    And Player 1 has "AG 2G 3G 4G" melds
    And Player 1 has "2R 3R" on hand
    When Player 1 reuse "4R" from Player 1 meld 1
    And Player 1 place "2R 3R 4R"
    And Player end the turn
    Then Player 1 's melds do not has "2R 3R 4R"

  @player_has_less_than_30_points_reuse
  Scenario: Player have less than 30 points when reuse
    Given Game is on
    And Player 2 has "AQ 2Q 3Q 4Q" melds
    And Player 1 has "AK AR" on hand
    When Player 1 reuse "AQ" from Player 2 meld 1
    And Player 1 place "AQ AK AR"
    And Player end the turn
    Then Player 1 's melds do not has "AQ AK AR"
    And Player 2 's melds has "AQ 2Q 3Q 4Q"

  @player_has_less_than_30_points_place_in_meld
  Scenario: Player have less than 30 points when place in meld
    Given Game is on
    And Player 1 has "1Q, 2Q, 3Q, 4Q" melds
    And Player 1 has "*" on hand
    When Player 1 place "*" into Player 1 meld 1
    And Player end the turn
    Then Player 1 's melds do not has "1Q, 2Q, 3Q, 4Q, *"
