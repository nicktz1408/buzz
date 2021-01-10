import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * This class manages player statistics by importing and exporting data from the folder stored.
 */
public class Player{
    private String name;
    private final int wins;
    private final int totalGamesAlone;
    private final int totalGAmesFriends;
    private final int highScore;


    /**
     * The constructor of a PLayer
     * @param name the nickname of a player
     */
    public Player(String name)
    {
        this.name = name;
        wins = 0;
        totalGamesAlone = 0;
        totalGAmesFriends = 0;
        highScore = 0;
    }


    /**
     * @return the nickname of the player
     */
    public String getName(){
        return name;
    }


    /**
     * Open the file with players and find the wins of a specific player
     * @return the wins in the 2 player games
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public int getWins() throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader br = new BufferedReader(new FileReader(currentDirectory+"/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(name)) {

                br.close();
                return Integer.parseInt(arrOfStr[3]);
            }
        }
        br.close();
        return wins;
    }


    /**
     * Open the file with players and find the number of gamesn that a specific player had played alone for statistical proposes
     * @return the total alone games
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public int getTotalGamesAlone() throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader br = new BufferedReader(new FileReader(currentDirectory+"/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(name)) {

                br.close();
                return Integer.parseInt(arrOfStr[2]);
            }
        }
        br.close();
        return wins;
    }


    /**
     * In this method return the total games of a player that he/she played with other players.
     * @return the total games that a player had played with other players
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public int getTotalGAmesFriends() throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(name)) {
                br.close();
                return Integer.parseInt(arrOfStr[4]);
            }
        }
        br.close();
        return totalGAmesFriends;
    }


    /**
     * In this method return the current high score of a player
     * @return the high score of the player
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public int getHighScore() throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader br = new BufferedReader(new FileReader(currentDirectory+"/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(name)) {
                br.close();
                return Integer.parseInt(arrOfStr[1]);
            }
        }
        br.close();
        return highScore;
    }


    /**
     *  In this method update the statistics data when a 1 player game finished
     * @param aName the nickname of a player
     * @param highScore the score that the player succeeded in the game
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     * @throws ClassNotFoundException when a class name hasn't found
     */
    public void newDataAlone(String aName, int highScore) throws IOException, ClassNotFoundException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader in = new BufferedReader(new FileReader(currentDirectory + "/Players"));
        BufferedWriter out = new BufferedWriter(new FileWriter(currentDirectory + "/TempPlayers"));
        String data;
        while ((data =  in.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(aName)) {
                arrOfStr[2] = String.valueOf(Integer.parseInt(arrOfStr[2]) + 1);
                if(Integer.parseInt(arrOfStr[1]) < highScore){
                    arrOfStr[1] = String.valueOf(highScore);
                }
            }
            String output = arrOfStr[0]+" "+arrOfStr[1]+" "+ arrOfStr[2]+" "+arrOfStr[3]+" "+arrOfStr[4];
            out.write(output);
            out.newLine();
            }

            File temp = new File(currentDirectory + "/TempPlayers");
            if(temp.renameTo(new File(currentDirectory + "/Players"))){
                out.close();
                in.close();
            }

    }

    /**
     *In this method update the statistics data when a 2 players game finished
     * @param aName the nickname of a player
     * @param win boolean value that its value is true when the player has won and false when the player has defeated
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public void newDataWithTwoPlayers(String aName, boolean win) throws  IOException{
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader in = new BufferedReader(new FileReader(currentDirectory + "/Players"));
        BufferedWriter out = new BufferedWriter(new FileWriter(currentDirectory + "/TempPlayers"));
        String data;
        while ((data =  in.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(aName)) {
                arrOfStr[4] = String.valueOf(Integer.parseInt(arrOfStr[4]) + 1);
                if(win){
                    arrOfStr[3] = String.valueOf(Integer.parseInt(arrOfStr[3]) + 1);
                }
            }
            String output = arrOfStr[0]+" "+arrOfStr[1]+" "+ arrOfStr[2]+" "+arrOfStr[3]+" "+arrOfStr[4];
            out.write(output);
            out.newLine();
        }

        File temp = new File(currentDirectory + "/TempPlayers");
        if(temp.renameTo(new File(currentDirectory + "/Players"))){
            out.close();
            in.close();
        }


    }


    /**
     * In this method check if a nickname already exist in the file with the registered players
     * @param aName the nickname of a player
     * @return true if the nickaname already exists, or false if the nickname does not exists.
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public boolean checkTheNames(String aName) throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(aName)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    /**
     * Open the players file and check the new player nickname if exists in the file and then we add he/she as a new player
     * @param player take for parameter a object of the class player
     * @return true if the new player added in the file, or false if his/her nickname already exist in the file.
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public boolean newPlayer(Player player) throws IOException {
        if(!checkTheNames(player.name)){
            String currentDirectory = new File(".").getCanonicalPath();
            BufferedWriter br = new BufferedWriter(new FileWriter(currentDirectory+"/Players",true));
            String output = player.name+" "+player.highScore+" "+ player.totalGamesAlone+" "+player.wins+" "+player.totalGAmesFriends;
            br.newLine();
            br.write(output);
            br.close();
            return true;
        }
        return false;
    }

    /**
     * method that return a list of the names of the players for the JComboBox object in the GUI
     * @return a list with all the players
     * @throws IOException when there is an issue with opening the file with the statistics of the players
     */
    public String[] listOfThePlayers() throws IOException {
        String currentDirectory = new File(".").getCanonicalPath();
        BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/Players"));

        String[] players = new String[(int) Files.lines(Path.of(currentDirectory + "/Players")).count()];
        String data;
        int i=0;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            players[i] = arrOfStr[0];
            i++;
        }
        br.close();
        return players;

    }


    /**
     * @param aName set a new name for a player
     */
    public void setName(String aName){
        name = aName;
    }
}
