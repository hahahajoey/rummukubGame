import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableReuseTest extends TestCase {

    // after turn 1, P2 has JS JC among its 11 remaining cards, turn 2: P1 draws then P2 reuses JH from table to form set {JS JC JH} on table
    @DisplayName("completing a partial set from a hand by reusing from a set of 4 of the table")
    @Test
    public void testReuseMakeSetFromSet() {
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
    public void testReuseMakeRunFromSet() {
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
    public void testReuseMakeSetFromRun() {
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
    public void testReuseMakeSetFromRun2() {
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
    public void testReuseMakeRunFromRun() {
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

    @DisplayName("video simple 3")
    @Test
    public void testVideoSimple3() {
        Game game = new Game();
        addPlayer(game);

        game.nextTurn();
        game.draw(new String[]{"JH", "JD", "JS", "JC"});
        game.place(new String[]{"JH", "JD", "JS", "JC"});

        game.nextTurn();
        game.draw(new String[]{"QH", "QS", "QC"});
        game.place(new String[]{"QH", "QS", "QC"});

        game.nextTurn();
        game.draw(new String[]{"4H", "4C", "4S"}, new String[]{"5H", "6H", "7H"});
        game.place(new String[]{"4H", "4C", "4S"}, new String[]{"5H", "6H", "7H"});

        game.nextTurn();
        game.draw(new String[]{"4D", "4H", "8H"});
        game.insertFromHand(new String[]{"4D"}, 2, 0);
        game.insertFromHand(new String[]{"4H", "8H"}, 2, 1);
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH JD JS JC}\r\n" +
                "   Player p2: {QH QS QC}\r\n" +
                "   Player p3: {4H 4C 4S 4D} {4H 5H 6H 7H 8H}");
    }

    @DisplayName("video simple 1")
    @Test
    public void testVideoSimple1() {
        Game game = new Game();
        addPlayer(game);

        game.nextTurn();
        game.draw(new String[]{"JH", "JD", "JS"});
        game.place(new String[]{"JH", "JD", "JS"});

        game.nextTurn();
        game.draw(new String[]{"JC", "QC", "KC"});
        game.place(new String[]{"JC", "QC", "KC"});

        game.nextTurn();
        game.draw(new String[]{"4H", "4C", "4S"}, new String[]{"5H", "6H", "7H"});
        game.place(new String[]{"4H", "4C", "4S"}, new String[]{"5H", "6H", "7H"});

        game.nextTurn();
        game.draw(new String[]{"AC", "QS", "KS"});
        game.insertFromMeld(new String[]{game.reuse(1, 0, "JC")}, 0, 0);
        game.insertFromHand(new String[]{"AC"}, 1, 0);
        game.placeAndReuse(new String[]{"QS", "KS"}, game.reuse(0, 0, "JS"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH JD JC} {QS KS JS}\r\n" +
                "   Player p2: {AC QC KC}\r\n" +
                "   Player p3: {4H 4C 4S} {5H 6H 7H}");
    }

    @DisplayName("video simple 2")
    @Test
    public void testVideoSimple2() {
        Game game = new Game();
        addPlayer(game);

        game.nextTurn();
        game.draw(new String[]{"JD", "JS", "JC"});
        game.place(new String[]{"JD", "JS", "JC"});

        game.nextTurn();
        game.draw(new String[]{"QD", "QS", "QC"});
        game.place(new String[]{"QD", "QS", "QC"});

        game.nextTurn();
        game.draw(new String[]{"7H", "8H", "9H", "10H", "JH", "QH"});
        game.place(new String[]{"7H", "8H", "9H", "10H", "JH", "QH"});

        game.nextTurn();
        game.draw(new String[]{"10C", "10S", "KH"});
        game.insertFromHand(new String[]{"KH"}, 2, 0);
        game.placeAndReuse(new String[]{"10C", "10S"}, game.reuse(2, 0, "10H"));
        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JD JS JC} {10C 10S 10H}\r\n" +
                "   Player p2: {QD QS QC}\r\n" +
                "   Player p3: {7H 8H 9H} {JH QH KH}");
    }

    @DisplayName("video complex  (postulates king - ace - two is VALID)")
    @Test
    public void testVideoComplex() {
        Game game = new Game();
        addPlayer(game);

        game.nextTurn();
        game.draw(new String[]{"JH", "JS", "JC"}, new String[]{"3H", "4H", "5H"}, new String[]{"AS", "2S", "3S", "4S"});
        game.place(new String[]{"JH", "JS", "JC"}, new String[]{"3H", "4H", "5H"}, new String[]{"AS", "2S", "3S", "4S"});
        game.draw(new String[]{"4C", "4S", "5D", "KD"});

        game.nextTurn();
        game.draw(new String[]{"AD", "2D", "3D", "4D"}, new String[]{"3H", "3D", "3S", "3C"}, new String[]{"3C", "4C", "5C"});
        game.place(new String[]{"AD", "2D", "3D", "4D"}, new String[]{"3H", "3D", "3S", "3C"}, new String[]{"3C", "4C", "5C"});

        game.nextTurn();
        game.draw();

        System.out.println(game);
        game.nextTurn();
        game.placeAndReuse(new String[]{"4C", "4S"}, game.reuse(1, 0, "4D"));
        game.insertFromHand(new String[]{"KD"}, 1, 0);
        game.placeAndReuse(new String[]{"5D"}, game.reuse(0, 1, "5H"), game.reuse(1, 2, "5C"));
        game.insertFromMeld(new String[]{game.reuse(1, 1, "3S"),
                game.reuse(1, 2, "3C")}, 0, 1);
        game.insertFromMeld(new String[]{game.reuse(0, 1, "4H"),
                game.reuse(0, 2, "4S")}, 1, 2);

        assertEquals(game.toString(), "Melds:\r\n" +
                "   Player p1: {JH JS JC} {3H 3S 3C} {AS 2S 3S} {4C 4S 4D} {5D 5H 5C}\r\n" +
                "   Player p2: {AD 2D 3D KD} {3H 3D 3C} {4C 4H 4S}\r\n" +
                "   Player p3: ");
    }
}
