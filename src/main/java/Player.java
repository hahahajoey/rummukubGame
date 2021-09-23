import java.util.ArrayList;

public class Player {
    public ArrayList<Meld> melds;
    String name;

    public Player(String name) {
        this.name = name;
        melds = new ArrayList<>();
    }
}
