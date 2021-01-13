import java.awt.*;
import java.io.IOException;

/**
 * From here starts all the Buzz Game
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Dimension dim = new Dimension(650, 500);
        new GUI(dim);
    }
}