import java.util.ArrayList;
import java.util.stream.Collectors;

public class Meld {
    public ArrayList<Tile> tiles;

    public Meld(String[] tiles) {
        this.tiles = new ArrayList<>();
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
        }
    }

    @Override
    public String toString() {
        return "{" + tiles.stream().map(Tile::toString).collect(Collectors.joining(" ")) + "}";
    }
}
