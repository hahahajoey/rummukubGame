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
            if (tile.number != "*") {
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
        if (tiles.size() < 3)
        {return false;}
        for (Tile tile : tiles) {
            if (tile.number != this.number) {
                return false;
            }
        }
        return true;
    }
}
