import java.util.ArrayList;

public class Set extends Meld {
    public Set(String[] tiles) {
        this.tiles = new ArrayList<>();
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
        }
    }
}
