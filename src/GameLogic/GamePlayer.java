package GameLogic;

public class GamePlayer {
    private double score = 0;
    private int bet;
    public GamePlayer(){}
    public void setScore(double score) {
        this.score = score;//Άλλαξα τον τύπο από ακέραιο σε πραγματικό σιπλής ακρίβειας
    }

    public double getScore() {
        return this.score;
    }//Άλλαξα τον τύπο από ακέραιο σε πραγματικό σιπλής ακρίβειας
    public void setBet(int bet){
        this.bet = bet;
    }

    public int getBet(){
        return bet;
    }

    public boolean checkTheBet(int b){
        return (b==250 || b==500 || b==750 || b==1000);
    }

}
