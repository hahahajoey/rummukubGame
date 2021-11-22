import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.Objects;

public class Tile implements Serializable {
    public String number;
    public char color;

    public static Tile createTile(String NumberColor) {
        if (NumberColor.length() == 1) {
            return new Tile(String.valueOf(NumberColor.charAt(0)), '\0');
        }
        if (NumberColor.length() == 3) {
            return new Tile(NumberColor.substring(1, 3), NumberColor.charAt(0));
        }
        return new Tile(String.valueOf(NumberColor.charAt(1)), NumberColor.charAt(0));
    }

    private Tile(String number, char color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        switch (number) {
            case "J":
            case "K":
            case "Q":
            case "10":
            case "11":
            case "12":
                return 10;
            case "*":
                return 0;
            case "A":
                return 1;
            default:
                return Integer.valueOf(number);
        }
    }

    @Override
    public String toString() {
        return ((color == 0 ? "" : String.valueOf(color)) + number);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tile tile = (Tile) object;
        return number.equals(tile.number) && color == tile.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, color);
    }
}
