package GameLogic;

public class StopClockRound extends FixedQuestionsRound{
    private final String ROUND_NAME = "Σταμάτησε το Χρονόμετρο";
    private int milli;


    @Override
    public void answerQuestion(GamePlayer player, int answerIndex) {
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion().getRightAnswerIndex()) {
            scoreToAdd = calculateScore();
        }


        player.setScore(player.getScore() + scoreToAdd);
    }

    @Override
    public String getRoundName() {
        return this.ROUND_NAME;
    }

    public double calculateScore(){
        return milli*0.2;
    }
}
