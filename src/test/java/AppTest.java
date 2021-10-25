import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
public class AppTest extends TestCase {

    @DisplayName("P1 plays {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D} and wins!")
    @Test
    public void testPlayPlaceAndWin() {
        Game game = new Game();
        addPlayer(game);
        game.nextTurn();
        game.place(new String[]{"2H", "2S", "2C", "2D"}, new String[]{"3C", "4C", "5C", "6C", "7C"}, new String[]{"4D", "5D", "6D", "7D", "8D"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {2H 2S 2C 2D} {3C 4C 5C 6C 7C} {4D 5D 6D 7D 8D}\r\n" +
                "   Player p2: \r\n" +
                "   Player p3: ");

        game.currentPlayerNumber = 0;
        assertGameWin(game);
    }

    @DisplayName("P1 starts with 2C 2C 2D 3H 3S 3S 5H 6S 7D 9H 10H JC QS KS and has to draw")
    @Test
    public void testDrawTile2() {
        Game game = new Game();
        game.addPlayer(new Player("p1"));

        game.currentPlayerNumber = 2;
        game.nextTurn();
        game.draw(new String[]{"2C", "2C", "2D", "3H", "3S", "3S", "5H", "6S", "7D", "9H", "10H", "JC", "QS", "KS"});

        assertEquals(game.players.get(0).hand.toString(), "Hand :{2C 2C 2D 3H 3S 3S 5H 6S 7D 9H 10H JC QS KS}");
        Tile drawnTile = game.draw();
        assertNotEquals(drawnTile, Tile.createTile("2C"));
        assertNotEquals(drawnTile, Tile.createTile("3S"));
        assertEquals(game.players.get(0).hand.tilesNumber, 15);
    }

    //declaring a winner upon a player playing all tiles and reporting correct scores (1 test starting with a new game)
    @DisplayName("scores (visible by all players) are P1:-78, P2: 0 and P3:-38")
    @Test
    public void testScore() {
        Game game = new Game();
        addPlayer(game);

        game.nextTurn();
        game.draw(new String[]{"2C", "2C", "2D", "3H", "3S", "3S", "5H", "6S", "7D", "9H", "10H", "JC", "QS", "KS"});
        game.nextTurn();
        game.draw(new String[]{"2H", "2S", "2C", "2D", "3C", "4C", "6C", "7C", "4D", "5D", "6D", "7D", "8D"});
        game.nextTurn();
        game.draw(new String[]{"4H", "6D", "6D", "7S", "7H", "8C", "10H", "JH", "QH", "KH", "10S", "JS", "QS", "KS"});

        game.nextTurn();
        game.draw(new String[]{"2H"});
        assertGameNotWin(game);

        game.nextTurn();
        game.draw(new String[]{"5C"});
        assertGameNotWin(game);

        game.nextTurn();
        game.place(new String[]{"10H", "JH", "QH", "KH"}, new String[]{"10S", "JS", "QS", "KS"});
        assertGameNotWin(game);

        game.nextTurn();
        game.place(new String[]{"2C", "2D", "2H"});
        assertGameNotWin(game);

        game.nextTurn();
        game.place(new String[]{"2H", "2S", "2C", "2D"}, new String[]{"3C", "4C", "5C", "6C", "7C"}, new String[]{"4D", "5D", "6D", "7D", "8D"});
        assertGameWin(game);

        assertEquals(game.scoreBoard(), "Score: p1:-78, p2:0, p3:-38");
    }

    private void addPlayer(Game game) {
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));
        game.addPlayer(new Player("p3"));
    }

    private void assertGameWin(Game game) {
        game.checkingWin();
        assertTrue(game.win);
    }

    private void assertGameNotWin(Game game) {
        game.checkingWin();
        assertFalse(game.win);
    }
}