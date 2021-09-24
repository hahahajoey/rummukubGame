import java.util.ArrayList;

public class Hand {
    public ArrayList<Tile> tiles;

    public Hand() {
        tiles = new ArrayList<>();
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
