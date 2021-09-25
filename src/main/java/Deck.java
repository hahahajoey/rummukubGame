import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    public LinkedList<Tile> tiles;

    public Deck() {
        tiles = new LinkedList<>();
        generateDeck(tiles);
        Collections.shuffle(tiles);
    }

    public Tile draw() {
        return tiles.pop();
    }

    public String[] draw(String[] tiles) {
        for (String tile : tiles) {
            Iterator<Tile> iterator = this.tiles.iterator();
            while (iterator.hasNext()) {
                Tile temp = iterator.next();
                if (temp.equals(Tile.createTile(tile))) {
                    iterator.remove();
                    break;
                }
            }
        }
        System.out.println(this.tiles.size());
        System.out.println(this);
        return tiles;
    }

    @Override
    public String toString() {
        return "Deck :" + "{" + tiles.stream().map(Tile::toString).collect(Collectors.joining(" ")) + "}";
    }

    private void generateDeck(List<Tile> tiles) {
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        char[] colors = {'D', 'H', 'S', 'C'};
        for (int i = 0; i < 2; i++) {
            for (String number : numbers) {
                for (char color : colors) {
                    tiles.add(Tile.createTile(number + color));
                }
            }
        }
        tiles.add(Tile.createTile("*"));
        tiles.add(Tile.createTile("*"));
    }
}
