import java.util.ArrayList;
import java.util.stream.Collectors;

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

    @Override
    public void insert(String[] meld) {
        for (String tile : meld) {
            tiles.add(Tile.createTile(tile));
        }
        tiles = tiles.stream().sorted((a,b) -> a.getNumber() - b.getNumber()).collect(Collectors.toList());
    }
}
