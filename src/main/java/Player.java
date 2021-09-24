import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player {
    public ArrayList<Meld> melds;
    String name;

    public Player(String name) {
        this.name = name;
        melds = new ArrayList<>();
    }

    public void place(String[] meld) {
        melds.add(new Meld(meld));
    }

    public void place(String[]... melds) {
        for (String[] meld : melds) {
            place(meld);
        }
    }

    @Override
    public String toString() {
        return "Player " + name + ": " + melds.stream().map(Meld::toString).collect(Collectors.joining(" "));
    }
}
