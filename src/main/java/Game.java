import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Game {
    public ArrayList<Player> players;
    public int currentPlayerNumber;
    public boolean win;
    public Deck deck;
    private ArrayList<ByteArrayOutputStream> playerRecord;
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
        if (!validation()) {
            load();
            draw(3);
        }
        currentPlayerNumber++;
        if (currentPlayerNumber > playerNumber - 1) {
            currentPlayerNumber = 0;
        }
        save();
    }

    private void load() {
        players = new ArrayList<>();
        for (int i = 0; i < playerRecord.size(); i++) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(playerRecord.get(i).toByteArray());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                players.add((Player) objectInputStream.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        playerRecord = new ArrayList<>();
        for (Player player : players) {
            try{
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream =  new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(player);
                playerRecord.add(byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validation() {
        for (Player player : players) {
            if (!player.validation())
                return false;
        }
        return true;
    }

    public void checkingWin() {
        win = players.get(currentPlayerNumber).hand.isEmpty();
        if (win) {
            for (Player player : players) {
                player.score -= player.hand.sum();
            }
        }
    }

    public void start() {
        for (Player player : players) {
            nextTurn();
            draw(14);
        }
        save();
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

    public void insertFromHand(String[] meld, int playerNumber, int meldNumber) {
        currentPlayer().hand.fold(meld);
        players.get(playerNumber - 1).insert(meld, meldNumber - 1);
    }

    public void reuse(int playerNumber, int meldNumber, String tile) {
        currentPlayer().hand.add(players.get(playerNumber - 1).reuse(meldNumber - 1, tile));
    }

    public Player currentPlayer() {
        return players.get(currentPlayerNumber);
    }

    @Override
    public String toString() {
        return "Melds:\r\n   " + players.stream().map(player -> player.toString()).collect(Collectors.joining("\r\n   "));
    }

    public String scoreBoard() {
        return "Score: " + players.stream().map(player -> player.name + ":" + player.score).collect(Collectors.joining(", "));
    }

    public int deckRemain() {
        return deck.tilesNumber();
    }
}
