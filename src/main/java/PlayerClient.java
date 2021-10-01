import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class PlayerClient {
    private int playerId;
    String name;
    Client clientConnection;
    Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your name ? ");
        PlayerClient playerClient = new PlayerClient();
        playerClient.name = scanner.next();
        playerClient.connectToClient();
        playerClient.startGame();
        playerClient.endScore();
        scanner.close();
    }

    private void endScore() {
    }

    private void startGame() {
        while (true) {
            for (int i = 0; i < 3; i++) {
                System.out.println(clientConnection.receiveStr());
            }
            if (clientConnection.receiveBoolean()) {
                while (playRound()) {
                }
            }
            if (clientConnection.receiveBoolean()) {
                break;
            }
        }
    }

    private boolean playRound() {
        System.out.println("Which move do you want to do: draw, place," +
                " place and reuse, insert from hand, insert from meld, end");
        String behaviour = scanner.nextLine();
        clientConnection.sendStr(behaviour);
        if (behaviour.equals("place")) {
            System.out.println("Place type in the tiles that you want to place :");
            clientConnection.sendStr(scanner.nextLine());
            return true;
        } else if (behaviour.equals("place and reuse")) {
            System.out.println("Place type in the tiles that you want to place from hand :");
            clientConnection.sendStr(scanner.nextLine());
            sendSelectedTile();
            return true;
        } else if (behaviour.equals("insert from hand")) {
            System.out.println("Place type in the tiles that you want to place from hand :");
            clientConnection.sendStr(scanner.nextLine());
            System.out.println("Place type in which player, meld, tile that you want to insert");
            clientConnection.sendStr(scanner.nextLine());
            return true;
        } else if (behaviour.equals("insert from meld")) {
            sendSelectedTile();
            System.out.println("Place type in which player, meld, tile that you want to insert");
            clientConnection.sendStr(scanner.nextLine());
            return true;
        }
        return false;
    }

    private void sendSelectedTile() {
        String input = "";
        int number = 0;
        while (true) {
            System.out.println("Place type in which player, meld, tile that you want to reuse");
            input += scanner.nextLine();
            number++;
            System.out.println("Any more reuse tile?");
            if (scanner.nextLine() == "Y") {
                break;
            }
        }
        clientConnection.sendStr(number + " " + input);
    }

    public void connectToClient() {
        clientConnection = new Client();
    }

    private class Client {
        Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        public Client() {
            try {
                socket = new Socket("localhost", Config.GAME_SERVER_PORT_NUMBER);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                playerId = dIn.readInt();

                System.out.println("Connected as " + playerId);
                sendStr(name);

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        private void sendStr(String string) {
            try {
                dOut.writeUTF(string);
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("String not sent");
                ex.printStackTrace();
            }
        }

        public String receiveStr() {
            try {
                return dIn.readUTF();
            } catch (IOException e) {
                System.out.println("String not received");
                e.printStackTrace();
            }
            return "";
        }

        public boolean receiveBoolean() {
            try {
                return dIn.readBoolean();
            } catch (IOException e) {
                System.out.println("Int not received");
                e.printStackTrace();
            }
            return false;
        }
    }
}

