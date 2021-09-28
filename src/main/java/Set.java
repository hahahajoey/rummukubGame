import java.util.ArrayList;

public class Set extends Meld {
    public Set(String[] tiles) {
        this.tiles = new ArrayList<>();
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
        }
    }

    @Override
    public void insert(String[] meld) {
        for (String tile : meld) {
            this.tiles.add(Tile.createTile(tile));
        }
    }
}
