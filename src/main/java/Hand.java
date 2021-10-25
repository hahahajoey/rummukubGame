import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Hand implements Serializable {
    public ArrayList<Tile> tiles;
    public int tilesNumber;

    public Hand() {
        tiles = new ArrayList<>();
        tilesNumber = 0;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public Tile add(Tile tile) {
        tilesNumber++;
        this.tiles.add(tile);
        return tile;
    }

    public void add(String tile) {
        this.tiles.add(Tile.createTile(tile));
    }

    public void add(String[] tiles) {
        for (String tile : tiles) {
            add(tile);
            tilesNumber++;
        }
    }

    public void fold(String[] meld) {
        for (String tile : meld) {
            removeFromHand(tile);
        }
    }

    private void removeFromHand(String tile) {
        Iterator<Tile> iterator = this.tiles.iterator();
        while (iterator.hasNext()) {
            Tile temp = iterator.next();
            if (temp.equals(Tile.createTile(tile))) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Hand :{" + tiles.stream().map(Tile::toString).collect(Collectors.joining(" ")) + "}";
    }

    public int sum() {
        return tiles.stream().mapToInt(Tile::getNumber).sum();
    }

    public boolean exist(String compereTile) {
        for (Tile tile : tiles) {
            if (tile.equals(Tile.createTile(compereTile))) {
                return true;
            }
        }
        return false;
    }
}
