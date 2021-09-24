import java.util.Objects;

public class Tile {
    public String number;
    public char color;

    public static Tile createTile(String NumberColor) {
        if (NumberColor.length() == 1) {
            return new Tile(NumberColor, '\0');
        }
        if (NumberColor.charAt(1) == '0') {
            return new Tile("10", NumberColor.charAt(2));
        }
        return new Tile(String.valueOf(NumberColor.charAt(0)), NumberColor.charAt(1));
    }

    private Tile(String number, char color) {
        this.number = number;
        this.color = color;
    }

    @Override
    public String toString() {
        return number + (color == 0 ? "" : String.valueOf(color));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tile tile = (Tile) object;
        return number == tile.number && color == tile.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, color);
    }
}
