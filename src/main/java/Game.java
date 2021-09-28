import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    public ArrayList<Player> players;
    public int currentPlayerNumber;
    public boolean win;
    public Deck deck;
    int playerNumber;

    public Game() {
        players = new ArrayList<>();
        currentPlayerNumber = -1;
        deck = new Deck();
        win = false;
    }

    public void addPlayer(Player player) {
        players.add(player);
        playerNumber++;
    }

    public void nextTurn() {
        currentPlayerNumber++;
        if (currentPlayerNumber > playerNumber - 1) {
            currentPlayerNumber = 0;
        }
    }

    public void checkingWin() {
        win = players.get(currentPlayerNumber).hand.isEmpty();
        if (win) {
            for (Player player : players) {
                player.score -= player.hand.sum();
            }
        }
    }

    public Tile draw() {
        return currentPlayer().draw(deck.draw());
    }

    public void draw(String[] tiles) {
        currentPlayer().draw(deck.draw(tiles));
    }

    public void draw(int times) {
        for (int i = 0; i < times; i++) {
            draw();
        }
    }

    public void draw(String[]... tiles) {
        for (String[] tile : tiles) {
            draw(tile);
        }
    }

    public void place(String[] meld) {
        currentPlayer().place(meld);
    }

    public void place(String[]... melds) {
        for (String[] meld : melds) {
            place(meld);
        }
    }

    public void placeAndReuse(String[] tiles, String... reuseTile) {
        currentPlayer().draw(reuseTile);
        currentPlayer().place(Stream.concat(Stream.of(tiles), Stream.of(reuseTile)).toArray(String[]::new));
    }

    public void insertFromMeld(String[] meld, int playerNumber, int meldNumber) {
    }

    public void insertFromHand(String[] meld, int playerNumber, int meldNumber) {
        currentPlayer().hand.fold(meld);
        players.get(playerNumber).insert(meld, meldNumber);
    }

    public String reuse(int playerNumber, int meldNumber, String tile) {
        return players.get(playerNumber).reuse(meldNumber,tile);
    }

    private Player currentPlayer() {
        return players.get(currentPlayerNumber);
    }

    @Override
    public String toString() {
        return "Melds:\r\n   " + players.stream().map(player -> player.toString()).collect(Collectors.joining("\r\n   "));
    }

    public String scoreBoard() {
        return "Score: " + players.stream().map(player -> player.name + ":" + player.score).collect(Collectors.joining(", "));
    }
}
