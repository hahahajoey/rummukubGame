public class Game {
    public Player[] players;
    public int currentPlayer;
    int playerNumber;

    public Game() {
        players = new Player[4];
        currentPlayer = -1;
    }

    public void addPlayer(Player player) {
        players[playerNumber] = player;
        playerNumber++;
    }

    public void draw() {
        currentPlayer++;
        if (currentPlayer > playerNumber-1) {
            currentPlayer = 0;
        }
    }
}
