import java.util.ArrayList;
import java.util.Iterator;

public class Set extends Meld {
    private String number;

    public Set(String[] tiles) {
        this.tiles = new ArrayList<>();
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
        }
        Iterator<Tile> iterator = this.tiles.iterator();
        Tile tile = iterator.next();
        while (iterator.hasNext()) {
            if (!tile.number.equals("*")) {
                this.number = tile.number;
                break;
            }
            tile = iterator.next();
        }
    }

    @Override
    public void insert(String[] meld) {
        for (String tile : meld) {
            this.tiles.add(Tile.createTile(tile));
        }
    }

    @Override
    public boolean validation() {
        if (tiles.size() < 3 || tiles.size() > 4) {
            return false;
        }
        for (Tile tile : tiles) {
            if (tile.number.equals("*")) {
                continue;
            }
            if (!tile.number.equals(this.number)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int score() {
        return getNumber() * tiles.size();
    }

    public int getNumber() {
        if (number == null) {
            return 0;
        }
        switch (number) {
            case "J":
            case "K":
            case "Q":
            case "10":
            case "11":
            case "12":
                return 10;
            case "A":
                return 1;
            case "*":
                return 0;
            default:
                return Integer.valueOf(number);
        }
    }
}
