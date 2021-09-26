import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Meld {
    public ArrayList<Tile> tiles;

    public static Meld createMeld(String[] tiles) {
        if (tiles[tiles.length - 1].charAt(tiles[tiles.length - 1].length() - 1) == tiles[tiles.length - 2].charAt(tiles[tiles.length - 2].length() - 1)) {
            return new Run(tiles);
        }
        return new Set(tiles);
    }

    public int locationForTile(String tile) {
        Iterator<Tile> iterator = this.tiles.iterator();
        int location = 0;
        while (iterator.hasNext()) {
            Tile temp = iterator.next();
            if (temp.equals(Tile.createTile(tile))) {
                return location;
            }
            location++;
        }
        return -1;
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

    public int size() {
        return tiles.size();
    }

    public Meld subMeld(int head, int tail) {
        Run run = new Run();
        for (int i = head; i < tail; i++) {
            run.tiles.add(this.tiles.get(i));
        }
        return run;
    }
}
