import java.util.ArrayList;
import java.util.stream.Collectors;

public class Game {
    public ArrayList<Player> players;
    public int currentPlayer;
    public boolean win;
    int playerNumber;

    public Game() {
        players = new ArrayList<>();
        currentPlayer = -1;
        win = false;
    }

    public void addPlayer(Player player) {
        players.add(player);
        playerNumber++;
    }

    public void nextTurn() {
        currentPlayer++;
        if (currentPlayer > playerNumber - 1) {
            currentPlayer = 0;
        }
    }

    public void checking() {
        win = players.get(currentPlayer).hand.isEmpty();
    }

    public void draw(String[] strings) {
    }

    public void place(String[] meld) {

    }

    @Override
    public String toString() {
        return "Melds:\r\n   " + players.stream().map(player -> player.toString()).collect(Collectors.joining("\r\n   "));
    }
}
