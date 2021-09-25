import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    //initial 30 points: (each test assumes we are at the start of a new game)
    @DisplayName("P1 plays{JH QH KH}")
    @Test
    void testPlayPlaceRun() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH}");
    }

    @DisplayName("P1 play {QH QC QS}")
    @Test
    void testPlayPlaceSet() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"QH", "QC", "QS"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {QH QC QS}");
    }

    @DisplayName("P1 plays {9H 10H JH QH KH}")
    @Test
    void testPlayPlaceLongRun() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"9H", "10H", "JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {9H 10H JH QH KH}");
    }

    @DisplayName("P1 plays {KH KC KS K}")
    @Test
    void testPlayPlaceLongSet() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"KH", "KC", "KS", "K"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {KH KC KS K}");
    }

    @DisplayName("P1 plays {2H 3H 4H} {7S 8S 9S}")
    @Test
    void testPlayPlace2Melds() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "3H", "4H"}, new String[]{"7S", "8S", "9S"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 3H 4H} {7S 8S 9S}");
    }

    @DisplayName("P1 plays {2H 2S 2D} {4C 4D 4S 4H} {5D 5S 5H}")
    @Test
    void testPlayPlace3Melds() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "2S", "2D"}, new String[]{"4C", "4D", "4S", "4H"}, new String[]{"5D", "5S", "5H"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 2S 2D} {4C 4D 4S 4H} {5D 5S 5H}");
    }

    @DisplayName("P1 plays {8H 8C 8D} {2H 3H 4H}")
    @Test
    void testPlayPlace2Run() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"8H", "8C", "8D"}, new String[]{"2H", "3H", "4H"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {8H 8C 8D} {2H 3H 4H}");
    }

    @DisplayName("P1 plays {2H 2D 2S} {2C 3C 4C} {3H 3S 3D} {5S 6S 7S}")
    @Test
    void testPlayPlace4Melds() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"8H", "8C", "8D"}, new String[]{"2H", "3H", "4H"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {8H 8C 8D} {2H 3H 4H}");
    }

    @DisplayName("P1 plays {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D} and wins!")
    @Test
    void testPlayPlaceAndWin() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "2S", "2C", "2D"}, new String[]{"3C", "4C", "5C", "6C", "7C"}, new String[]{"4D", "5D", "6D", "7D", "8D"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D}");

        game.currentPlayerNumber = 0;
        game.checkingWin();
        assertTrue(game.win);
    }

    //playing melds out of your hand after initial 30 (row 62 is the setup for each of the tests of row 63 to 68)

    //setup for 1st turn P1 plays {JH QH KH}, P2 {JS QS KS} and P3 {JD QD KD}
    private void gameSetUpForFirstRound(Game game) {
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));
        game.addPlayer(new Player("p3"));

        game.players.get(0).place(new String[]{"JH", "QH", "KH"});
        game.players.get(1).place(new String[]{"JS", "QS", "KS"});
        game.players.get(2).place(new String[]{"JD", "QD", "KD"});
        game.currentPlayerNumber = 2;
    }

    @DisplayName("start of turn 2: P1 then plays {2C 3C 4C} from hand")
    @Test
    void testRound2P1PlaceRun() {
        Game game = new Game();
        gameSetUpForFirstRound(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "3C", "4C"});
        game.place(new String[]{"2C", "3C", "4C"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH} {2C 3C 4C}\r\n" +
                "   Player p2: {JS QS KS}\r\n" +
                "   Player p3: {JD QD KD}");
    }

    @DisplayName("start of turn 2: P1 then plays {2C 3C 4C} {8D 9D 10D} from hand")
    @Test
    void testRound2P1Place2Run() {
        Game game = new Game();
        gameSetUpForFirstRound(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "3C", "4C"}, new String[]{"8D", "9D", "10D"});
        game.place(new String[]{"2C", "3C", "4C"}, new String[]{"8D", "9D", "10D"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH} {2C 3C 4C} {8D 9D 10D}\r\n" +
                "   Player p2: {JS QS KS}\r\n" +
                "   Player p3: {JD QD KD}");
    }

    @DisplayName("start of turn 2: P1 then plays {2C 2H 2D} from hand")
    @Test
    void testRound2P1PlaceSet() {
        Game game = new Game();
        gameSetUpForFirstRound(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "2H", "2D"});
        game.place(new String[]{"2C", "2H", "2D"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH} {2C 2H 2D}\r\n" +
                "   Player p2: {JS QS KS}\r\n" +
                "   Player p3: {JD QD KD}");
    }

    @DisplayName("start of turn 2: P1 then plays {2C 2H 2D} {8D 8H 8S 8C} from hand")
    @Test
    void testRound2P1Place2Set() {
        Game game = new Game();
        gameSetUpForFirstRound(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "2H", "2D"}, new String[]{"8D", "8H", "8S", "8C"});
        game.place(new String[]{"2C", "2H", "2D"}, new String[]{"8D", "8H", "8S", "8C"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH} {2C 2H 2D} {8D 8H 8S 8C}\r\n" +
                "   Player p2: {JS QS KS}\r\n" +
                "   Player p3: {JD QD KD}");
    }

    @DisplayName("start of turn 2: P1 then plays {2C 2H 2D} {8D 9D 10D} from hand")
    @Test
    void testRound2P1PlaceRunSet() {
        Game game = new Game();
        gameSetUpForFirstRound(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "2H", "2D"}, new String[]{"8D", "9D", "10D"});
        game.place(new String[]{"2C", "2H", "2D"}, new String[]{"8D", "9D", "10D"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH} {2C 2H 2D} {8D 9D 10D}\r\n" +
                "   Player p2: {JS QS KS}\r\n" +
                "   Player p3: {JD QD KD}");
    }

    @DisplayName("start of turn 2: P1 then plays {2C 2H 2D} {3C 3H 3D} {8D 9D 10D} {8H 9H 10H} from hand")
    @Test
    void testRound2P1Place4Meld() {
        Game game = new Game();
        gameSetUpForFirstRound(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "2H", "2D"}, new String[]{"3C", "3H", "3D"}, new String[]{"8D", "9D", "10D"}, new String[]{"8H", "9H", "10H"});
        game.place(new String[]{"2C", "2H", "2D"}, new String[]{"3C", "3H", "3D"}, new String[]{"8D", "9D", "10D"}, new String[]{"8H", "9H", "10H"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH} {2C 2H 2D} {3C 3H 3D} {8D 9D 10D} {8H 9H 10H}\r\n" +
                "   Player p2: {JS QS KS}\r\n" +
                "   Player p3: {JD QD KD}");
    }

    //a player having or choosing to draw a tile (2 tests, each starting a new game)

    //P1 starts with {2C 2H 2D}  {3C 3H 3D} {8D 9D 10D} {8H 9H 10H} QC 7H in hand
    private void gameSetUpForP1MeldAndHand(Game game) {
        game.addPlayer(new Player("p1"));

        game.players.get(0).place(new String[]{"2C", "2H", "2D"}, new String[]{"3C", "3H", "3D"}, new String[]{"8D", "9D", "10D"}, new String[]{"8H", "9H", "10H"});
        game.players.get(0).draw(new String[]{"QC", "7H"});
        game.currentPlayerNumber = 2;
    }

    @DisplayName("and then chooses to draw")
    @Test
    void testDrawTile() {
        Game game = new Game();
        gameSetUpForP1MeldAndHand(game);

        game.nextTurn();

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2C 2H 2D} {3C 3H 3D} {8D 9D 10D} {8H 9H 10H}");

        assertEquals(game.players.get(0).hand.toString(), "Hand :{QC 7H}");
        game.draw();
        assertEquals(game.players.get(0).hand.tilesNumber, 3);
    }
}
