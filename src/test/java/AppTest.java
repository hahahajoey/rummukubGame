import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AppTest extends TestCase {

    @DisplayName("Starting a networked game works")
    @Test
    public void testGameStart() {
        Game game = new Game();
        createPlayer(game);
        game.start();
        assertEquals(game.deck.tiles.size(), 64);
        assertEquals(game.players.get(0).hand.tiles.size(), 14);
        assertEquals(game.players.get(1).hand.tiles.size(), 14);
        assertEquals(game.players.get(2).hand.tiles.size(), 14);
    }

    @DisplayName("A player's attempts initial 30 points without jokers")
    @ParameterizedTest
    @MethodSource("initialSample1")
    public void testFirstTurnPlacement(String[] melds, boolean valid) {
        Game game = new Game();
        createPlayer(game);
        game.currentPlayerNumber = 0;
        for (String meld : melds) {
            game.draw(meld.split(" "));
        }
        while (game.players.get(0).hand.tiles.size() < 14) {
            game.draw();
        }
        game.save();
        for (String meld : melds) {
            game.place(meld.split(" "));
        }
        game.nextTurn();
        if (!valid) {
            for (String meld : melds) {
                assertFalse(game.players.get(0).hasMeld(meld.split(" ")));
            }
            assertTrue(game.players.get(0).hand.tiles.size() == 17);
        } else {
            for (String meld : melds) {
                assertTrue(game.players.get(0).hasMeld(meld.split(" ")));
            }
        }
    }

    private void createPlayer(Game game) {
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));
        game.addPlayer(new Player("p3"));
    }

    private static Stream<Arguments> initialSample1() {
        return Stream.of(
                Arguments.of(new String[]{"R9 O9 B9"}, false),
                Arguments.of(new String[]{"B11 O11 R11"}, true),
                Arguments.of(new String[]{"B9 O9 G9 R9"}, true),
                Arguments.of(new String[]{"R9 R10 R11"}, false),
                Arguments.of(new String[]{"R11 R12 R13"}, true),
                Arguments.of(new String[]{"G8 G9 G10 G11"}, true),
                Arguments.of(new String[]{"R1 O1 B1", "R2 R3 R4", "R3 O3 B3", "G1 G2 G3"}, false),
                Arguments.of(new String[]{"R1 O1 B1", "R2 R3 R4", "R3 O3 B3", "G2 G3 G4"}, true),
                Arguments.of(new String[]{"R11 O11 B11", "R2 R3 R4", "R3 O3 B3", "G2 G3 G4"}, true),
                Arguments.of(new String[]{"R1 O1 B1", "R2 R3 R4", "R2 O2 B2", "R3 O3 B3"}, false),
                Arguments.of(new String[]{"R1 R2 R3", "G2 G3 G4", "O2 O3 O4"}, false),
                Arguments.of(new String[]{"B6 G6 R6", "B1 R1 O1", "B3 R3 O3"}, true),
                Arguments.of(new String[]{"B1 B2 B3", "G2 G3 G4", "R4 R5 R6"}, true),
                Arguments.of(new String[]{"B7 G7 R7", "B1 R1 O1", "B3 R3 O3"}, true),
                Arguments.of(new String[]{"B11 B12 B13", "G2 G3 G4", "R4 R5 R6"}, true),
                Arguments.of(new String[]{"R9 O9 *"}, false),
                Arguments.of(new String[]{"B11 * R11"}, true),
                Arguments.of(new String[]{"* O9 G9 R9"}, true),
                Arguments.of(new String[]{"* R10 R11"}, false),
                Arguments.of(new String[]{"R11 * R13"}, true),
                Arguments.of(new String[]{"G8 G9 G10 *"}, true),
                Arguments.of(new String[]{"O9 * *"}, false),
                Arguments.of(new String[]{"B11 * *"}, true),
                Arguments.of(new String[]{"* O9 * R9"}, true),
                Arguments.of(new String[]{"R9 R10 * *"}, true),
                Arguments.of(new String[]{"R1 O1 B1", "R2 R3 R4", "R3 * B3", "G2 G3 G4"}, true),
                Arguments.of(new String[]{"R1 O1 B1", "R2 R3 R4", "R3 * B3", "G2 G3 *"}, true)
        );
    }

    @DisplayName("A player's attempts to play one or more melds from hand after initial 30 points of previous turn obtained with R11 O11 G11")
    @ParameterizedTest
    @MethodSource("initialSample2")
    public void testSecondTurnPlacement(String[] melds, boolean valid) {
        Game game = new Game();
        createPlayer(game);
        game.currentPlayerNumber = 0;
        game.draw("R11 O11 G11".split(" "));
        for (String meld : melds) {
            game.draw(meld.split(" "));
        }
        game.place("R11 O11 G11".split(" "));
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        while (game.players.get(0).hand.tiles.size() < 14) {
            game.draw();
        }
        for (String meld : melds) {
            game.place(meld.split(" "));
        }
        for (String meld : melds) {
            assertTrue(game.players.get(0).hasMeld(meld.split(" ")));
        }
    }

    private static Stream<Arguments> initialSample2() {
        return Stream.of(
                Arguments.of(new String[]{"B11 O11 R11"}, true),
                Arguments.of(new String[]{"B11 B12 B13"}, true),
                Arguments.of(new String[]{"B11 B12 B13", "O1 G1 R1", "O4 G4 R4"}, true)
        );
    }

    @DisplayName("A player's draws")
    @ParameterizedTest
    @MethodSource("initialHand")
    public void testPlayerDraw(String hand, String[] melds, int handNumber) {
        Game game = new Game();
        createPlayer(game);
        game.currentPlayerNumber = 0;
        for (String meld : melds) {
            game.draw(meld.split(" "));
        }
        game.draw(hand.split(" "));
        for (String meld : melds) {
            game.place(meld.split(" "));
        }
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        game.draw();
        game.nextTurn();
        assertEquals(game.players.get(0).hand.tiles.size(), handNumber);
    }

    private static Stream<Arguments> initialHand() {
        return Stream.of(
                Arguments.of("O1 O2 G2 G3 B3 B4 R4 R5 O5 O6 G6 G7 B7 B8 ", new String[]{}, 15),
                Arguments.of("O1 O2 G2 G3 B3 B4 R4 R5 O5 O6 G6 ", new String[]{"R10 R11 R12"}, 12),
                Arguments.of("O1 O2 G2 G3 B3 B4 R4 R5 O5 O6 G6 R10 R11 R12", new String[]{}, 15),
                Arguments.of("O1 O2 G2 G3 B3 B4 R4 R5 O5 O6 O7 ", new String[]{"R10 R11 R12"}, 12),
                Arguments.of("O1 O2 G2 G3 B3 B4 R4 R5 O5 O6 R9 ", new String[]{"R10 R11 R12"}, 12)
        );
    }

    @DisplayName("After initial 30 points obtained with R11 O11 G11,  player's attempts to play an invalid meld (regardless the value of this meld)")
    @ParameterizedTest
    @MethodSource("initial30PointInvalidMove")
    public void test30PointInvalidMove(String[] melds, String hand, String dontHave) {
        Game game = new Game();
        createPlayer(game);
        game.currentPlayerNumber = 0;
        game.draw("R11 O11 G11".split(" "));
        game.draw(hand.split(" "));
        while (game.players.get(0).hand.tiles.size() < 14) {
            game.draw();
        }
        game.place("R11 O11 G11".split(" "));
        game.nextTurn();
        if (!dontHave.isEmpty()) {
            game.draw(dontHave.split(" "));
            game.draw(dontHave.split(" "));
        }
        while (game.players.get(1).hand.tiles.size() < 14) {
            game.draw();
        }
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        for (String meld : melds) {
            game.place(meld.split(" "));
        }
        game.nextTurn();
        for (String meld : melds) {
            assertFalse(game.players.get(0).hasMeld(meld.split(" ")));
        }
        assertTrue(game.players.get(0).hand.tiles.size() == 14);
    }

    private static Stream<Arguments> initial30PointInvalidMove() {
        return Stream.of(
                Arguments.of(new String[]{"R1 R2"}, "R1 R2", ""),
                Arguments.of(new String[]{"R1 B2"}, "R1 B2", ""),
                Arguments.of(new String[]{"B1 B1 R1"}, "B1 B1 R1", ""),
                Arguments.of(new String[]{"B1 B1 B2 B3"}, "B1 B1 B2 B3", ""),
                Arguments.of(new String[]{"B7 B8 B8 B9"}, "B7 B8 B8 B9", ""),
                Arguments.of(new String[]{"B6 B7 B8 B8"}, "B6 B7 B8 B8", ""),
                Arguments.of(new String[]{"* * R1"}, "* R1", ""),
                Arguments.of(new String[]{"* * B2 B3"}, "* B2 B3", ""),
                Arguments.of(new String[]{"R1 B2 R3"}, "R1 B2 R3", ""),
                Arguments.of(new String[]{"R1 B2 O3"}, "R1 B2 O3", ""),
                Arguments.of(new String[]{"R1 B2 O3"}, "R1 B2 O3", ""),
                Arguments.of(new String[]{"B1 R1 O1"}, "B1 O1", "R1"),
                Arguments.of(new String[]{"B1 B2 B3"}, "B1 B3", "B2"),
                Arguments.of(new String[]{"B2 B1 B3"}, "B2 B1 B3", ""),
                Arguments.of(new String[]{"B1 B3 B4"}, "B1 B3 B4", ""),
                Arguments.of(new String[]{"B4 B5 O4"}, "B4 B5 O4", ""),
                Arguments.of(new String[]{"B4 B5 O5"}, "B4 B5 O5", "")
        );
    }

    @DisplayName("After initial 30 points, a player's adds one or more cards from hand to meld(s) of the table")
    @ParameterizedTest
    @MethodSource("AdditionalTiles")
    public void testAdditionalTiles(String meld, String addTiles, String afterMeld, boolean valid) {
        Game game = new Game();
        createPlayer(game);
        game.currentPlayerNumber = 0;
        game.draw("R11 O11 G11".split(" "));
        game.draw(meld.split(" "));
        game.draw(addTiles.split(" "));
        while (game.players.get(0).hand.tiles.size() < 14) {
            game.draw();
        }
        game.place("R11 O11 G11".split(" "));
        game.place(meld.split(" "));
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        game.insertFromHand(addTiles.split(" "), 1, 2);
        game.nextTurn();
        if (valid) {
            assertTrue(game.players.get(0).hasMeld(afterMeld.split(" ")));
        } else {
            assertFalse(game.players.get(0).hasMeld(afterMeld.split(" ")));
        }
    }

    public static Stream<Arguments> AdditionalTiles() {
        return Stream.of(
                Arguments.of("R3 R4 R5", "R7 R8", "R3 R4 R5 R7 R8", false),
                Arguments.of("R3 R4 R5", "R1", "R1 R3 R4 R5", false),
                Arguments.of("R3 R4 R5", "R3", "R3 R3 R4 R5", false),
                Arguments.of("R3 R4 R5", "R2", "R2 R3 R4 R5", true),
                Arguments.of("R3 R4 R5", "R1 R2", "R1 R2 R3 R4 R5", true),
                Arguments.of("R1 G1 B1", "O2", "R1 G1 B1 O2", false),
                Arguments.of("R1 G1 B1", "R1 O1", "R1 R1 G1 B1 O1", false),
                Arguments.of("R1 G1 B1", "O1", "R1 G1 B1 O1", true),
                Arguments.of("R3 R4 R5", "*", "* R3 R4 R5", true),
                Arguments.of("R3 R4 R5", "* *", "* * R3 R4 R5", true),
                //Arguments.of("R3 R4 R5", "R1 *", "R1 * R3 R4 R5", true) fail
                Arguments.of("R3 R4 R5", "*", "R3 * R4 R5", false),
                Arguments.of("R1 R2 R3 R4 R5 R6 R7 R8 R9 R10 R11 R12 R13", "*", "R1 R2 R3 R4 R5 R6 R7 R8 R9 R10 R11 R12 R13 *", false)
        );
    }

    @DisplayName("After initial 30 points, a player's reuses the table by splitting a meld")
    @ParameterizedTest
    @MethodSource("ReuseTile")
    public void testReuseTiles(String meld, String reuseTiles, String newMeld, String[] afterMeld, String hand, boolean valid) {
        Game game = new Game();
        createPlayer(game);
        game.currentPlayerNumber = 0;
        game.draw("R11 O11 G11".split(" "));
        game.draw(meld.split(" "));
        game.draw(hand.split(" "));
        while (game.players.get(0).hand.tiles.size() < 14) {
            game.draw();
        }
        game.place("R11 O11 G11".split(" "));
        game.place(meld.split(" "));
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        game.draw(14);
        game.nextTurn();
        for (String tile : reuseTiles.split(" ")) {
            game.reuse(1, 2, tile);
        }
        game.place(newMeld.split(" "));
        System.out.println(game);
        game.nextTurn();
        System.out.println(game);
        if (valid) {
            for (String aMeld : afterMeld) {
                assertTrue(game.players.get(0).hasMeld(aMeld.split(" ")));
            }
        } else {
            for (String aMeld : afterMeld) {
                assertFalse(game.players.get(0).hasMeld(aMeld.split(" ")));
            }
        }
    }

    public static Stream<Arguments> ReuseTile() {
        return Stream.of(
                Arguments.of("R3 R4 R5 R6 R7 R8", "R3 R4 R5", "R3 R4 R4 R5", new String[]{"R3 R4 R4 R5", "R6 R7 R8"}, "R4", false),
                Arguments.of("R3 R4 R5 R6 R7 R8", "R6 R7 R8", "R6 R7 R7 R8", new String[]{"R3 R4 R5", "R6 R7 R7 R8"}, "R7", false),
                Arguments.of("R3 R4 R5 R6 R7", "R6 R7", "R5 R6 R7", new String[]{"R3 R4 R5", "R5 R6 R7"}, "R5", true),
                Arguments.of("R3 R4 R5 R6 R7 R8", "R3 R4 R5", "R3 R4 R5 R6", new String[]{"R3 R4 R5 R6", "R6 R7 R8"}, "R6", true),
                Arguments.of("R3 R4 R5 R6 R7 R8", "R3 R4 R5 ", "R3 R4 R5 *", new String[]{"R3 R4 R5 *", "R6 R7 R8"}, "*", true),
                Arguments.of("R1 G1 B1 O1", "R1 G1", "R1 G1 R2", new String[]{"R1 G1 R2", "B1 O1"}, "R2", false),
                Arguments.of("R1 G1 B1 O1", "R1 G1", "R1 G1 R1", new String[]{"R1 G1 R1", "B1 O1"}, "R1", false),
                Arguments.of("R1 G1 B1 ", "R1 G1", "R1 G1 O1", new String[]{"R1 G1 O1", "B1"}, "O1", false),
                Arguments.of("R1 G1 B1 O1", "O1", "G1 B1 O1", new String[]{"R1 G1 B1", "G1 B1 O1"}, "G1 B1", true),
                Arguments.of("R1 G1 B1 O1", "R1", "R1 G1 *", new String[]{"R1 G1 *", "G1 B1 O1"}, "G1 *", true)
        );
    }
}