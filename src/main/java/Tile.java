public class Tile {
    public char number;
    public char color;

    public Tile(String NumberColor) {
        number = NumberColor.charAt(0);
        color = NumberColor.charAt(1);
    }

    public boolean equals(Object object) {
        Tile tile = (Tile) object;
        return number == tile.number && color == tile.color;
    }
}
