import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

public class Deck {
    public ArrayDeque<Tile> tiles;

    public Deck() {
        tiles = new ArrayDeque<>();
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        char[] colors = {'D', 'H', 'S', 'C'};
        for (int i = 0; i < 2; i++) {
            for (String number : numbers) {
                for (char color : colors) {
                    tiles.push(Tile.createTile(number + color));
                }
            }
        }
        tiles.push(Tile.createTile("*"));
        tiles.push(Tile.createTile("*"));
        Collections.shuffle(Collections.singletonList(tiles));
    }

    public Tile draw() {
        return tiles.pop();
    }
}
