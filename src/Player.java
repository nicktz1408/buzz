import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Player implements  Serializable{

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
        return wins;
    }

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
        return wins;
    }

    public void increaseWins(){
        wins++;
    }
    public void setHighScore(int highScore){
        this.highScore = highScore;
    }

    public void newData(String aName, int highScore) throws IOException, ClassNotFoundException {
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
            out.close();
            in.close();
            new File(currentDirectory + "/TempPlayers").renameTo(new File(currentDirectory + "/Players"));
        }


    public void increaseTotalGamesAlone(){
        totalGamesAlone++;
    }

    public void increaseTotalGamesFriends(){
        totalGAmesFriends++;
    }



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





    public String[] listOfThePlayers() throws IOException {
        Path file = Paths.get("/home/manolis/Desktop/buzzquizworld-thelamogia/Players");
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



    public void setName(String aName){
        name = aName;
    }
}
