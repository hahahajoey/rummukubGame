import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        game.nextTurn();
        assertEquals(game.currentPlayer, 0);
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player Bob: \r\n" +
                "   Player Alice: \r\n" +
                "   Player Tim: ");
    }

    @DisplayName("after P1 it is P2 who plays {JH QH KH}: this meld is on the table and seen by all players and hand of P2 is updated")
    @Test
    public void testP2FirstRound() {
        game.currentPlayer = 0;
        game.nextTurn();
        assertEquals(game.currentPlayer, 1);

        game.players.get(1).place(new String[]{"JH", "QH", "KH"});
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player Bob: \r\n" +
                "   Player Alice: {JH QH KH}\r\n" +
                "   Player Tim: ");
    }

    @DisplayName("after P2 it is P3 who plays {KH KS KC} and {2C 2H 2D}: there are now 3 melds on the table and hand of P3 is updated")
    @Test
    public void testP3FirstRound() {
        game.currentPlayer = 1;
        game.nextTurn();
        assertEquals(game.currentPlayer, 2);

        game.players.get(1).place(new String[]{"JH", "QH", "KH"});
        game.players.get(2).place(new String[]{"KH", "KS", "KC"});
        game.players.get(2).place(new String[]{"2C", "2H", "2D"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player Bob: \r\n" +
                "   Player Alice: {JH QH KH}\r\n" +
                "   Player Tim: {KH KS KC} {2C 2H 2D}");
    }

    @DisplayName("after P3 it is P1 who plays {QH QS QD}: there are now 4 melds on the table and the hand of P1 is updated")
    @Test
    public void testP1SecondRound() {
        game.currentPlayer = 2;
        game.nextTurn();
        assertEquals(game.currentPlayer, 0);

        game.players.get(1).place(new String[]{"JH", "QH", "KH"});
        game.players.get(2).place(new String[]{"KH", "KS", "KC"});
        game.players.get(2).place(new String[]{"2C", "2H", "2D"});
        game.players.get(0).place(new String[]{"QH", "QS", "QD"});

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player Bob: {QH QS QD}\r\n" +
                "   Player Alice: {JH QH KH}\r\n" +
                "   Player Tim: {KH KS KC} {2C 2H 2D}");
    }
}
