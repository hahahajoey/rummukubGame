import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player implements Serializable {
    public ArrayList<Meld> melds;
    public Hand hand;
    public int score;
    String name;
    private int placedScore;
    private boolean invalidBehavior;

    public Player(String name) {
        this.name = name;
        this.melds = new ArrayList<>();
        this.hand = new Hand();
        this.score = 0;
        this.placedScore = 0;
        this.invalidBehavior = false;
    }

    public void place(String[] meld) {
        hand.fold(meld);
        Meld newMeld = Meld.createMeld(meld);
        melds.add(newMeld);
        placedScore += newMeld.score();
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

    public void insert(String[] meld, int meldNumber) {
        if (placedScore < 30) {
            invalidBehavior = true;
        }
        melds.get(meldNumber).insert(meld);
    }

    public String reuse(int meldNumber, String tile) {
        if (placedScore < 30) {
            invalidBehavior = true;
        }
        Meld meld = melds.get(meldNumber);
        if (meld instanceof Set) {
            return meld.reuse(tile);
        }
        int location = meld.locationForTile(tile);
        if (location == 0 || location == meld.size() - 1) {
            return meld.reuse(tile);
        }
        melds.remove(meld);
        melds.add(meld.subMeld(0, location));
        melds.add(meld.subMeld(location + 1, meld.size()));
        return tile;
    }

    @Override
    public String toString() {
        return "Player " + name + ": " + melds.stream().map(Meld::toString).collect(Collectors.joining(" "));
    }

    public boolean hasTilesInHand(String[] tiles) {
        for (String tile : tiles) {
            if (!hand.exist(tile)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasMeld(String[] compereMeld) {
        for (Meld meld : melds) {
            if (meld.equals(Meld.createMeld(compereMeld))) {
                return true;
            }
        }
        return false;
    }

    public boolean validation() {
        for (Meld meld : melds) {
            if (!meld.validation()) {
                return false;
            }
        }
        if (invalidBehavior) {
            invalidBehavior = false;
            return false;
        }
        return hand.validation();
    }
}
