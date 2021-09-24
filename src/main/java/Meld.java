import java.util.ArrayList;
import java.util.stream.Collectors;

public class Meld {
    public ArrayList<Tile> tiles;

    public Meld(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public String toString() {
        return "{" + tiles.stream().map(tile -> tile.toString()).collect(Collectors.joining(" ")) + "}";
    }
}
