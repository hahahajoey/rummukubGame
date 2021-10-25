@tag
Feature: Main application test
  Test game's core features

  @single_placement
  Scenario Outline: single placement test
    Given Game is on
    And Game start
    And Player 1 has <tiles> on hand
    When Player 1 place <tiles>
    Then Player 1 's melds has <tiles>
    Examples:
      | tiles             |
      | "JH QH KH"        |
      | "JH QH KH"        |
      | "QH QC QS"        |
      | "9H 10H JH QH KH" |
      | "KH KC KS KD"     |

  @double_placement
  Scenario Outline: double placement test
    Given Game is on
    And Game start
    And Player 1 has <tiles1> on hand
    And Player 1 has <tiles2> on hand
    When Player end the turn
    And Player 1 place <tiles1>
    And Player 1 place <tiles2>
    Then Player 1 's melds has <tiles1>
    And Player 1 's melds has <tiles2>
    Examples:
      | tiles1     | tiles2     |
      | "2H 3H 4H" | "7S 8S 9S" |
      | "8H 8C 8D" | "2H 3H 4H" |

  @triple_placement
  Scenario Outline: triple placement test
    Given Game is on
    And Game start
    And Player 1 has <tiles1> on hand
    And Player 1 has <tiles2> on hand
    And Player 1 has <tiles3> on hand
    When Player end the turn
    And Player 1 place <tiles1>
    And Player 1 place <tiles2>
    And Player 1 place <tiles3>
    Then Player 1 's melds has <tiles1>
    And Player 1 's melds has <tiles2>
    And Player 1 's melds has <tiles3>
    Examples:
      | tiles1     | tiles2        | tiles3     |
      | "2H 2S 2D" | "4C 4D 4S 4H" | "5D 5S 5H" |

  @fourth_placement
  Scenario Outline: fourth placement test
    Given Game is on
    And Game start
    And Player 1 has <tiles1> on hand
    And Player 1 has <tiles2> on hand
    And Player 1 has <tiles3> on hand
    And Player 1 has <tiles4> on hand
    When Player end the turn
    And Player 1 place <tiles1>
    And Player 1 place <tiles2>
    And Player 1 place <tiles3>
    And Player 1 place <tiles4>
    Then Player 1 's melds has <tiles1>
    And Player 1 's melds has <tiles2>
    And Player 1 's melds has <tiles3>
    And Player 1 's melds has <tiles4>
    Examples:
      | tiles1     | tiles2     | tiles3     | tiles4     |
      | "2H 2D 2S" | "2C 3C 4C" | "3H 3S 3D" | "5S 6S 7S" |

  @single_placement_round_2
  Scenario Outline: single placement for player 2
    Given Game is on
    And Game start
    And Player 1 has "JH QH KH" melds
    And Player 2 has "JS QS KS" melds
    And Player 3 has "JD QD KD" melds
    When Player end the turn
    And Player end the turn
    And Player end the turn
    And Player end the turn
    And Player 1 place <tiles>
    Then Player 1 's melds has <tiles>
    Examples:
      | tiles      |
      | "2C 3C 4C" |
      | "2C 2H 2D" |

  @double_placement_round_2
  Scenario Outline: double placement for player 2
    Given Game is on
    And Game start
    And Player 1 has "JH QH KH" melds
    And Player 2 has "JS QS KS" melds
    And Player 3 has "JD QD KD" melds
    When Player end the turn
    And Player end the turn
    And Player end the turn
    And Player end the turn
    And Player 1 place <tiles1>
    And Player 1 place <tiles2>
    Then Player 1 's melds has <tiles1>
    And Player 1 's melds has <tiles2>
    Examples:
      | tiles1     | tiles2        |
      | "2C 3C 4C" | "8D 9D 10D"   |
      | "2C 2H 2D" | "8D 8H 8S 8C" |
      | "2C 2H 2D" | "8D 9D 10D"   |

  @fourth_placement_round_2
  Scenario Outline: fourth placement for player 2
    Given Game is on
    And Game start
    And Player 1 has "JH QH KH" melds
    And Player 2 has "JS QS KS" melds
    And Player 3 has "JD QD KD" melds
    When Player end the turn
    And Player end the turn
    And Player end the turn
    And Player end the turn
    And Player 1 place <tiles1>
    And Player 1 place <tiles2>
    And Player 1 place <tiles3>
    And Player 1 place <tiles4>
    Then Player 1 's melds has <tiles1>
    And Player 1 's melds has <tiles2>
    And Player 1 's melds has <tiles3>
    And Player 1 's melds has <tiles4>
    Examples:
      | tiles1     | tiles2     | tiles3      | tiles4      |
      | "2C 2H 2D" | "3C 3H 3D" | "8D 9D 10D" | "8H 9H 10H" |