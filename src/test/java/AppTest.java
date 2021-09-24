import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @DisplayName("P1 plays{JH QH KH}")
    @Test
    public void testPlayPlaceJH_QH_KH() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH}");
    }

    @DisplayName("P1 play {QH QC QS}")
    @Test
    public void testPlayPlaceQH_QC_QS() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"QH", "QC", "QS"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {QH QC QS}");
    }

    @DisplayName("P1 plays {9H 10H JH QH KH}")
    @Test
    public void testPlayPlace9H_10H_JH_QH_KH() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"9H", "10H", "JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {9H 10H JH QH KH}");
    }

    @DisplayName("P1 plays {KH KC KS K}")
    @Test
    public void testPlayPlaceKH_KC_KS_K() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"KH", "KC", "KS", "K"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {KH KC KS K}");
    }

    @DisplayName("P1 plays {2H 3H 4H} {7S 8S 9S}")
    @Test
    public void testPlayPlace2Melds() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "3H", "4H"}, new String[]{"7S", "8S", "9S"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 3H 4H} {7S 8S 9S}");
    }

    @DisplayName("P1 plays {2H 2S 2D} {4C 4D 4S 4H} {5D 5S 5H}")
    @Test
    public void testPlayPlace3Melds() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "2S", "2D"}, new String[]{"4C", "4D", "4S", "4H"}, new String[]{"5D", "5S", "5H"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 2S 2D} {4C 4D 4S 4H} {5D 5S 5H}");
    }

    @DisplayName("P1 plays {8H 8C 8D} {2H 3H 4H}")
    @Test
    public void testPlayPlace8H_8C_8D2H_3H_4H() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"8H", "8C", "8D"}, new String[]{"2H", "3H", "4H"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {8H 8C 8D} {2H 3H 4H}");
    }

    @DisplayName("P1 plays {2H 2D 2S} {2C 3C 4C} {3H 3S 3D} {5S 6S 7S}")
    @Test
    public void testPlayPlace4Melds() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"8H", "8C", "8D"}, new String[]{"2H", "3H", "4H"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {8H 8C 8D} {2H 3H 4H}");
    }

    @DisplayName("P1 plays {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D} and wins!")
    @Test
    public void testPlayPlaceAndWin() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "2S", "2C", "2D"}, new String[]{"3C", "4C", "5C", "6C", "7C"}, new String[]{"4D", "5D", "6D", "7D", "8D"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D}");

        game.currentPlayer = 0;
        game.players.get(0).setHand(new Hand());
        game.CheakWin();
        assertTrue(game.win);
    }
}
