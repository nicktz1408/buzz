package GameLogic;

/**
 * Class for the Player (stores their score)
 */
public class GamePlayer {
    private double score = 0;

    /**
     *
     * @param score new score to be set
     */
    public void setScore(double score) {
        this.score = score;//Άλλαξα τον τύπο από ακέραιο σε πραγματικό σιπλής ακρίβειας
    }

    /**
     *
     * @return the current score of the Player
     */
    public double getScore() {
        return this.score;
    }//Άλλαξα τον τύπο από ακέραιο σε πραγματικό σιπλής ακρίβειας
}
