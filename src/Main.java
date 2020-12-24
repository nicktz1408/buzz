import java.awt.*;

public class Main {
    public static void main(String[] args){
        //GameFacadeDirector gameBuilder = new GameFacadeDirector();
        //GameFacade game = gameBuilder.buildGame();
        Dimension dim = new Dimension(600, 450);
        GUI gui = new GUI(dim); // πρέπει να περάσω το game σαν παράμετρο στο gui
    }
}