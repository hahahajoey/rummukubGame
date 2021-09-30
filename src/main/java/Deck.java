import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    public LinkedList<Tile> tiles;

    public Deck() {
        tiles = generateDeck();
        Collections.shuffle(tiles);
    }

    public Tile draw() {
        return tiles.pop();
    }

    public String[] draw(String[] tiles) {
        for (String tile : tiles) {
            removeFromDeck(tile);
        }
        return tiles;
    }

    private void removeFromDeck(String tile) {
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
        return "Deck :" + "{" + tiles.stream().map(Tile::toString).collect(Collectors.joining(" ")) + "}";
    }

    private LinkedList<Tile> generateDeck() {
        LinkedList<Tile> tiles = new LinkedList<>();
        String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        char[] colors = {'R', 'G', 'B', 'O'};
        for (int i = 0; i < 2; i++) {
            for (String number : numbers) {
                for (char color : colors) {
                    tiles.add(Tile.createTile(number + color));
                }
            }
        }
        return tiles;
    }
}
