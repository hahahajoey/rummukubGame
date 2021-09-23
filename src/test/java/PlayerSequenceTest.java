import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerSequenceTest extends TestCase {
    Game game;

    public PlayerSequenceTest() {
        game = new Game();
        Player bob = new Player("Bob");
        Player alice = new Player("Alice");
        Player tim = new Player("Tim");

        game.addPlayer(bob);
        game.addPlayer(alice);
        game.addPlayer(tim);
    }

    @DisplayName("P1 plays first and draws: all players still see the table empty")
    @Test
    public void testP1FirstRound() {
        game.start();
        assertEquals("Bob", game.players[0].name);
        for (Meld melds : game.players[0].melds) {
            assertEquals(melds , new ArrayList<>());
        }
    }

}
