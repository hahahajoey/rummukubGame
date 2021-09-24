import java.util.Objects;

public class Tile {
    public String number;
    public char color;

    public Tile(String NumberColor) {
        if (NumberColor.charAt(1) == '0') {
            number = "10";
            color = NumberColor.charAt(2);
            return;
        }
        number = String.valueOf(NumberColor.charAt(0));
        color = NumberColor.charAt(1);
    }

    @Override
    public String toString() {
        return number + color;
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
