import java.util.ArrayList;
import java.util.stream.Collectors;

public class Game {
    public ArrayList<Player> players;
    public int currentPlayerNumber;
    public boolean win;
    int playerNumber;

    public Game() {
        players = new ArrayList<>();
        currentPlayerNumber = -1;
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
    }

    public void draw(String[] tiles) {
        currentPlayer().draw(tiles);
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

    private Player currentPlayer() {
        return players.get(currentPlayerNumber);
    }

    @Override
    public String toString() {
        return "Melds:\r\n   " + players.stream().map(player -> player.toString()).collect(Collectors.joining("\r\n   "));
    }
}
