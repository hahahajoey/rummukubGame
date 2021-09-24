import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Game {
    public ArrayList<Player> players;
    public int currentPlayer;
    int playerNumber;

    public Game() {
        players = new ArrayList<>();
        currentPlayer = -1;
    }

    public void addPlayer(Player player) {
        players.add(player);
        playerNumber++;
    }

    public void draw() {
        currentPlayer++;
        if (currentPlayer > playerNumber - 1) {
            currentPlayer = 0;
        }
    }

    @Override
    public String toString() {
        return "Melds:\r\n   " + players.stream().map(player -> player.toString()).collect(Collectors.joining("\r\n   "));
    }
}
