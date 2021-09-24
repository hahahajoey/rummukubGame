import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testPlayPlace2H_3H_4H7S_8S_9S() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"2H", "3H", "4H"}, new String[]{"7S", "8S", "9S"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 3H 4H} {7S 8S 9S}");
    }

    @DisplayName("P1 plays {2H 2S 2D} {4C 4D 4S 4H} {5D 5S 5H}")
    @Test
    public void testPlayPlace2H_2S_2D4C_4D_4S_4H5D_5S_5H() {
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
}
