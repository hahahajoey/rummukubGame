import java.util.ArrayList;
import java.util.stream.Collectors;

public class Hand {
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
        System.out.println(this.toString());
        this.tiles.add(tile);
        return tile;
    }

    public void add(String[] tiles) {
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
            tilesNumber++;
        }
    }

    @Override
    public String toString() {
        return "Hand :{" + tiles.stream().map(Tile::toString).collect(Collectors.joining(" ")) + "}";
    }
}
