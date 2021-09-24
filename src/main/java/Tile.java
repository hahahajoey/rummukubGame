import java.util.Objects;

public class Tile {
    public char number;
    public char color;

    public Tile(String NumberColor) {
        number = NumberColor.charAt(0);
        color = NumberColor.charAt(1);
    }

    @Override
    public String toString() {
        return String.valueOf(number) + color;
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
