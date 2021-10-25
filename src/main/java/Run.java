import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Run extends Meld {
    private char color;

    public Run(String[] tiles) {
        this.tiles = new ArrayList<>();
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
        }
        Iterator<Tile> iterator = this.tiles.iterator();
        Tile tile = iterator.next();
        while (iterator.hasNext()) {
            if (tile.color != '\0') {
                this.color = tile.color;
                break;
            }
            tile = iterator.next();
        }
    }

    public Run() {
        this.tiles = new ArrayList<>();
    }

    @Override
    public void insert(String[] meld) {
        for (String tile : meld) {
            tiles.add(Tile.createTile(tile));
        }
        tiles = tiles.stream().sorted(Comparator.comparingInt(Tile::getNumber)).collect(Collectors.toList());
    }

    @Override
    public boolean validation() {
        if (tiles.size() < 3) {
            return false;
        }
        for (Tile tile : tiles) {
            if (tile.color != this.color) {
                return false;
            }
        }
        return true;
    }
}
