import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Player {
    private String name;
    private int wins;
    private int totalGamesAlone;
    private int totalGAmesFriends;
    private int highScore;

    public Player(String name)
    {
        this.name = name;
        wins = 0;
        totalGamesAlone = 0;
        totalGAmesFriends = 0;
        highScore = 0;
    }
    public String getName(){
        return name;
    }

    public int getWins() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(name)) {

                br.close();
                return Integer.parseInt(arrOfStr[1]);
            }
        }
        br.close();
        return wins;
    }

    public int getTotalGamesAlone() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Players"));
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

    public int getTotalGAmesFriends() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Players"));
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

    public int getHighScore() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Players"));
        String data;
        while ((data = br.readLine()) != null) {
            String[] arrOfStr = data.split(" ", 5);
            if (arrOfStr[0].equals(name)) {

                br.close();
                return Integer.parseInt(arrOfStr[4]);
            }
        }
        br.close();
        return wins;
    }

    public void increaseWins(){
        wins++;
    }

    public void newHighScore(int highScore){
        this.highScore = highScore;
    }

    public void increaseTotalGamesAlone(){
        totalGamesAlone++;
    }

    public void increaseTotalGamesFriends(){
        totalGAmesFriends++;
    }



    public boolean checkTheNames(String aName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Players"));
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


    public boolean newPlayer(Player player) throws IOException {
        if(!checkTheNames(player.name)){
            BufferedWriter br = new BufferedWriter(new FileWriter("/home/manolis/Desktop/buzzquizworld-thelamogia/Players",true));
            String output = player.name+" "+player.wins+" "+ player.totalGamesAlone+" "+player.highScore+" "+player.totalGAmesFriends;
            br.newLine();
            br.write(output);
            br.close();
            return true;
        }
        return false;
    }





    public String[] listOfThePlayers() throws IOException {
        Path file = Paths.get("/home/manolis/Desktop/buzzquizworld-thelamogia/Players");

        BufferedReader br = new BufferedReader(new FileReader("/home/manolis/Desktop/buzzquizworld-thelamogia/Players"));

        String[] players = new String[(int) Files.lines(file).count()];
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
}
