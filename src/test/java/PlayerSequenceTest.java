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
        game.draw();
        assertEquals(game.currentPlayer, 0);
        for (Meld melds : game.players[0].melds) {
            assertEquals(melds, new Meld[]{});
        }
    }

    @DisplayName("after P1 it is P2 who plays {JH QH KH}: this meld is on the table and seen by all players and hand of P2 is updated")
    @Test
    public void testP2Plays() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile("JH"));
        tiles.add(new Tile("QH"));
        tiles.add(new Tile("KH"));
        game.draw();
        game.players[1].place(tiles);
        assertEquals(game.players[1].melds.get(0).tiles.get(0),new Tile("JH"));
        assertEquals(game.players[1].melds.get(0).tiles.get(1),new Tile("QH"));
        assertEquals(game.players[1].melds.get(0).tiles.get(2),new Tile("KH"));
    }
}
