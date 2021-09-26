import java.util.ArrayList;
import java.util.Iterator;

public class Run extends Meld {
    public Run(String[] tiles) {
        this.tiles = new ArrayList<>();
        for (String tile : tiles) {
            this.tiles.add(Tile.createTile(tile));
        }
    }

    public Run() {
        this.tiles = new ArrayList<>();
    }
}
