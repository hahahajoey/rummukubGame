public class Game {
    public Player[] players;
    int playerNumber;

    public Game() {
        players = new Player[4];
    }

    public void addPlayer(Player player) {
        players[playerNumber] = player;
        playerNumber++;
    }

    public void start() {
    }
}
