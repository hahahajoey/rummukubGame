import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @DisplayName("P1 plays{JH QH KH}")
    @Test
    void TestPlayPlaceJH_QH_KH() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));
        game.players.get(0).place(new String[]{"JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH QH KH}");
    }
}
