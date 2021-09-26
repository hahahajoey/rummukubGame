import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableReuseTest {

    // after turn 1, P2 has JS JC among its 11 remaining cards, turn 2: P1 draws then P2 reuses JH from table to form set {JS JC JH} on table
    @DisplayName("completing a partial set from a hand by reusing from a set of 4 of the table")
    @Test
    void testReuseMakeSetFromSet() {
        Game game = new Game();
        setUpGame(game);

        game.nextTurn();
        game.draw(new String[]{"*"});

        game.nextTurn();
        game.draw(new String[]{"JS", "JC"});
        game.draw(9);

        game.placeAndReuse(new String[]{"JS", "JC"}, game.players.get(0).melds.get(0).reuse("JH"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JD JS JC}\r\n" +
                "   Player p2: {QH QS QC} {JS JC JH}\r\n" +
                "   Player p3: {7D 8D 9D 10D JD QD KD}");
    }

    //setup for 1st turn: P1 plays {JH JD JS JC}, P2 {QH QS QC} and P3 {7D 8D 9D 10D JD QD KD}
    private void setUpGame(Game game) {
        addPlayer(game);
        game.currentPlayerNumber = 0;
        game.draw(new String[]{"JH", "JD", "JS", "JC"});
        game.place(new String[]{"JH", "JD", "JS", "JC"});

        game.currentPlayerNumber = 1;
        game.draw(new String[]{"QH", "QS", "QC"});
        game.place(new String[]{"QH", "QS", "QC"});

        game.currentPlayerNumber = 2;
        game.draw(new String[]{"7D", "8D", "9D", "10D", "JD", "QD", "KD"});
        game.place(new String[]{"7D", "8D", "9D", "10D", "JD", "QD", "KD"});
    }

    private void addPlayer(Game game) {
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));
        game.addPlayer(new Player("p3"));
    }
}
