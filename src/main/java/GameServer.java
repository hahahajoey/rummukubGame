import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    ServerSocket serverSocket;
    Game game = new Game();
    int numPlayers;

    Server[] playerServers = new Server[3];

    public static void main(String[] args) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
        gameServer.gameLoop();
        gameServer.gameEnd();
    }

    private void gameEnd() {
        sendScore();
    }

    private void gameLoop() {
        game.start();
        while (!game.win) {
            game.nextTurn();
            sendAllStr("Player " + game.currentPlayer().name + "’s turn");
            sendAllStr(game.toString());
            sendPlayerHand();
            playerRound(game.currentPlayerNumber);
            sendWin();
            game.checkingWin();
        }
    }

    private void sendWin() {
        for (Server server : playerServers) {
            server.sendBoolean(game.win);
        }
        System.out.println(game.scoreBoard());
    }

    private void sendScore() {
        for (int i = 0; i < numPlayers; i++) {
            playerServers[i].sendStr(game.scoreBoard());
        }
    }

    private void sendPlayerHand() {
        for (int i = 0; i < numPlayers; i++) {
            playerServers[i].sendStr(game.players.get(i).hand.toString());
        }
    }

    private void playerRound(int playerNumber) {
        sendSetRoundPlay(playerNumber);
        while (playerServers[playerNumber].receiveRound()) {
        }
    }

    private void sendSetRoundPlay(int playerNumber) {
        for (int i = 0; i < numPlayers; i++) {
            if (i == playerNumber) {
                playerServers[i].sendBoolean(true);
            } else {
                playerServers[i].sendBoolean(false);
            }
        }
    }

    private void sendAllStr(String string) {
        for (int i = 0; i < numPlayers; i++) {
            playerServers[i].sendStr(string);
        }
    }

    public GameServer() {
        System.out.println("Starting game server");
        numPlayers = 0;
        try {
            serverSocket = new ServerSocket(Config.GAME_SERVER_PORT_NUMBER);
        } catch (IOException ex) {
            System.out.println("Server Failed to open");
        }
    }

    public void acceptConnections() throws ClassNotFoundException {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < 3) {
                Socket socket = serverSocket.accept();
                numPlayers++;

                Server server = new Server(socket, numPlayers);

                // send the player number
                server.dOut.writeInt(server.playerId);
                server.dOut.flush();

                // get the player name
                String in = server.dIn.readUTF();
                System.out.println("Player " + server.playerId + " ~ " + in + " ~ has joined");
                // add the player to the player list
                game.addPlayer(new Player(in));
                playerServers[numPlayers - 1] = server;
            }
            System.out.println("Three players have joined the game");

            // start the server threads
            for (int i = 0; i < playerServers.length; i++) {
                Thread t = new Thread(playerServers[i]);
                t.start();
            }
            // start their threads
        } catch (IOException ex) {
            System.out.println("Could not connect 3 players");
        }
    }

    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        private int playerId;

        public Server(Socket s, int playerId) {
            socket = s;
            this.playerId = playerId;
            try {
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }

        public void run() {
            try {
                while (true) {
                }

            } catch (Exception ex) {
                {
                    System.out.println("Run failed");
                    ex.printStackTrace();
                }
            }
        }

        public void sendStr(String string) {
            try {
                dOut.writeUTF(string);
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("String not sent");
                ex.printStackTrace();
            }
        }

        public void sendBoolean(boolean b) {
            try {
                dOut.writeBoolean(b);
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("Boolean not sent");
                ex.printStackTrace();
            }
        }

        private String receiveString() {
            try {
                return dIn.readUTF();
            } catch (IOException e) {
                System.out.println("Int not received");
                e.printStackTrace();
            }
            return "";
        }

        public boolean receiveRound() {
            System.out.println(receiveString());
            if (receiveString().equals("draw")) {
                game.draw();
                return false;
            } else if (receiveString().equals("place")) {
                String[] tiles = receiveString().split(" ");
                game.place(tiles);
            } else if (receiveString().equals("place and reuse")) {
                String[] tiles = receiveString().split(" ");
                String[] reuseSet = reuse(receiveString().split(" "));
                game.placeAndReuse(tiles, reuseSet);
            } else if (receiveString().equals("insert from hand")) {
                String[] tiles = receiveString().split(" ");
                String[] insertTile = receiveString().split(" ");
                game.insertFromHand(tiles, Integer.valueOf(insertTile[0]), Integer.valueOf(insertTile[1]));
            } else if (receiveString().equals("insert from meld")) {
                String[] reuseSet = reuse(receiveString().split(" "));
                String[] insertTile = receiveString().split(" ");
                game.insertFromHand(reuseSet, Integer.valueOf(insertTile[0]), Integer.valueOf(insertTile[1]));
            }
            return false;
        }

        private String[] reuse(String[] meldChange) {
            ArrayList<String> output = new ArrayList<>();
            for (int i = 1; i < Integer.valueOf(meldChange[0]); i += 3) {
                output.add(game.reuse(Integer.valueOf(meldChange[i]), Integer.valueOf(meldChange[i + 1]), meldChange[i + 2]));
            }
            return (String[]) output.toArray();
        }
    }
}
