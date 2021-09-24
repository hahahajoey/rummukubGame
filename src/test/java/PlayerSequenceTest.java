import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerSequenceTest {
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
    public void testP2FirstRound() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile("JH"));
        tiles.add(new Tile("QH"));
        tiles.add(new Tile("KH"));

        game.currentPlayer = 0;
        game.draw();
        assertEquals(game.currentPlayer, 1);

        game.players[1].place(tiles);
        assertEqualToTile(game.players[1].melds.get(0).tiles.get(0), "JH");
        assertEqualToTile(game.players[1].melds.get(0).tiles.get(1), "QH");
        assertEqualToTile(game.players[1].melds.get(0).tiles.get(2), "KH");
    }

    @DisplayName("after P2 it is P3 who plays {KH KS KC} and {2C 2H 2D}: there are now 3 melds on the table and hand of P3 is updated")
    @Test
    public void testP3FirstRound() {
        game.currentPlayer = 1;
        game.draw();
        assertEquals(game.currentPlayer, 2);

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile("KH"));
        tiles1.add(new Tile("KS"));
        tiles1.add(new Tile("KC"));
        game.players[2].place(tiles1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile("2C"));
        tiles2.add(new Tile("2H"));
        tiles2.add(new Tile("2D"));
        game.players[2].place(tiles2);

        assertEquals(game.players[2].melds.toString(), "{KH KS KC}, {2C 2H 2D}");
    }

    private void assertEqualToTile(Tile tile, String jh) {
        assertEquals(tile, new Tile(jh));
    }
}
