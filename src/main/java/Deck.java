import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    public ArrayDeque<Tile> tiles;

    public Deck() {
        tiles = new ArrayDeque<>();
        List<Tile> generator = new LinkedList<>();
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        char[] colors = {'D', 'H', 'S', 'C'};
        for (int i = 0; i < 2; i++) {
            for (String number : numbers) {
                for (char color : colors) {
                    generator.add(Tile.createTile(number + color));
                }
            }
        }
        generator.add(Tile.createTile("*"));
        generator.add(Tile.createTile("*"));
        Collections.shuffle(generator);
        for (Tile tile : generator) {
            tiles.push(tile);
        }
    }

    public Tile draw() {
        return tiles.pop();
    }
}
