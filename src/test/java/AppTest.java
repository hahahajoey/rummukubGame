import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @DisplayName("P1 plays{JH QH KH}")
    @Test
    public void TestPlayPlaceJH_QH_KH() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH}");
    }

    @DisplayName("P1 play {QH QC QS}")
    @Test
    public void TestPlayPlaceQH_QC_QS() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"QH", "QC", "QS"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {QH QC QS}");
    }

    @DisplayName("P1 plays {9H 10H JH QH KH}")
    @Test
    public void TestPlayPlace9H_10H_JH_QH_KH() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"9H", "10H", "JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {9H 10H JH QH KH}");
    }

    @DisplayName("P1 plays {KH KC KS K}")
    @Test
    public void TestPlayPlaceKH_KC_KS_K() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"KH", "KC", "KS", "K"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {KH KC KS K}");
    }
}
