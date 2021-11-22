
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Meld implements Serializable {
    public List<Tile> tiles;

    public static Meld createMeld(String[] tiles) {
        Iterator<String> iterator = Arrays.stream(tiles).iterator();
        char color = '\0';
        while (iterator.hasNext()) {
            String tile = iterator.next();
            if (tile.length() < 2) {
                continue;
            }
            if (color == '\0') {
                color = tile.charAt(0);
            } else if (color != tile.charAt(0)) {
                return new Set(tiles);
            } else {
                return new Run(tiles);
            }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (tiles.size() != ((Meld) o).tiles.size()) {
            return false;
        }
        for (int i = 0; i < tiles.size(); i++) {
            if (!tiles.get(i).equals(((Meld) o).tiles.get(i))) {
                return false;
            }
        }
        return true;
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

    public abstract void insert(String[] meld);

    public abstract boolean validation();

    public abstract int score();
}
