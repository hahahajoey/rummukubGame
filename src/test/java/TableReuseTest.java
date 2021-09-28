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

        game.placeAndReuse(new String[]{"JS", "JC"}, game.reuse(0, 0, "JH"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JD JS JC}\r\n" +
                "   Player p2: {QH QS QC} {JS JC JH}\r\n" +
                "   Player p3: {7D 8D 9D 10D JD QD KD}");
    }

    // after turn 1, P2 has QH KH among its 11 remaining cards, turn 2: P1 draws then P2 reuses JH from table to form run {JH QH KH} on table
    @DisplayName("completing a partial run from a hand by reusing from a set of 4 of the table")
    @Test
    void testReuseMakeRunFromSet() {
        Game game = new Game();
        setUpGame(game);

        game.nextTurn();
        game.draw(new String[]{"*"});

        game.nextTurn();
        game.draw(new String[]{"QH", "KH"});
        game.draw(9);

        game.placeAndReuse(new String[]{"QH", "KH"}, game.reuse(0, 0, "JH"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JD JS JC}\r\n" +
                "   Player p2: {QH QS QC} {QH KH JH}\r\n" +
                "   Player p3: {7D 8D 9D 10D JD QD KD}");
    }

    @DisplayName("completing a partial set from a hand by reusing from a run of the table")
    @Test
    void testReuseMakeSetFromRun() {
        Game game = new Game();
        setUpGame(game);

        game.nextTurn();
        game.draw(new String[]{"7S", "7H"});
        game.draw(8);

        game.placeAndReuse(new String[]{"7S", "7H"}, game.reuse(2, 0, "7D"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH JD JS JC} {7S 7H 7D}\r\n" +
                "   Player p2: {QH QS QC}\r\n" +
                "   Player p3: {8D 9D 10D JD QD KD}");
    }

    @DisplayName("completing a partial set from a hand by reusing from a run of the table 2")
    @Test
    void testReuseMakeSetFromRun2() {
        Game game = new Game();
        setUpGame(game);

        game.nextTurn();
        game.draw(new String[]{"KH", "KS"});
        game.draw(8);

        game.placeAndReuse(new String[]{"KH", "KS"}, game.reuse(2, 0, "KD"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH JD JS JC} {KH KS KD}\r\n" +
                "   Player p2: {QH QS QC}\r\n" +
                "   Player p3: {7D 8D 9D 10D JD QD}");
    }

    @DisplayName("completing a partial run from a hand by reusing from run of the table")
    @Test
    void testReuseMakeRunFromRun() {
        Game game = new Game();
        setUpGame(game);

        game.nextTurn();
        game.draw(new String[]{"8D", "9D"});
        game.draw(8);

        game.placeAndReuse(new String[]{"8D", "9D"}, game.reuse(2, 0, "10D"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH JD JS JC} {8D 9D 10D}\r\n" +
                "   Player p2: {QH QS QC}\r\n" +
                "   Player p3: {7D 8D 9D} {JD QD KD}");
    }

    //setup for 1st turn: P1 plays {JH JD JS JC}, P2 {QH QS QC} and P3 {7D 8D 9D 10D JD QD KD}
    private void setUpGame(Game game) {
        addPlayer(game);
        game.nextTurn();
        game.draw(new String[]{"JH", "JD", "JS", "JC"});
        game.place(new String[]{"JH", "JD", "JS", "JC"});

        game.nextTurn();
        game.draw(new String[]{"QH", "QS", "QC"});
        game.place(new String[]{"QH", "QS", "QC"});

        game.nextTurn();
        game.draw(new String[]{"7D", "8D", "9D", "10D", "JD", "QD", "KD"});
        game.place(new String[]{"7D", "8D", "9D", "10D", "JD", "QD", "KD"});
    }

    private void addPlayer(Game game) {
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));
        game.addPlayer(new Player("p3"));
    }
}
