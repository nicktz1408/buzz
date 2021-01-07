import java.awt.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Dimension dim = new Dimension(600, 450);
        new GUI(dim); // πρέπει να περάσω το game σαν παράμετρο στο gui
    }
}