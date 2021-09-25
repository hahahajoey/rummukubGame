import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player {
    public ArrayList<Meld> melds;
    public Hand hand;
    String name;

    public Player(String name) {
        this.name = name;
        this.melds = new ArrayList<>();
        this.hand = new Hand();
    }

    public void place(String[] meld) {
        melds.add(new Meld(meld));
    }

    public void place(String[]... melds) {
        for (String[] meld : melds) {
            place(meld);
        }
    }

    public Tile draw(Tile tile) {
        return hand.add(tile);
    }

    public void draw(String[] tiles) {
        hand.add(tiles);
    }

    @Override
    public String toString() {
        return "Player " + name + ": " + melds.stream().map(Meld::toString).collect(Collectors.joining(" "));
    }
}
