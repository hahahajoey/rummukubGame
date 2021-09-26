import java.util.ArrayList;
import java.util.Iterator;
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

    public String reuse(String tile) {
        return removeFromMeld(tile).toString();
    }

    private Tile removeFromMeld(String tile) {
        Iterator<Tile> iterator = this.tiles.iterator();
        while (iterator.hasNext()) {
            Tile temp = iterator.next();
            if (temp.equals(Tile.createTile(tile))) {
                iterator.remove();
                return temp;
            }
        }
        return null;
    }
}
