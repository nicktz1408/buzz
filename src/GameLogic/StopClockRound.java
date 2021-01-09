package GameLogic;

/**
 * Class for the Stop the Clock Round Type (σταμάτησε το χρονόμετρο)
 */
public class StopClockRound extends FixedQuestionsRound{
    private final int ROUND_NAME = 2;

    /**
     * Answers the current question by a given player
     * @param player the player that answers the question
     * @param answerIndex the chosen index of the answer
     * @param additionalRequestData {int betSize} a single parameter for the amount of the bet by the player
     */
    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        int millisRemaining = (int)additionalRequestData[0];
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            scoreToAdd = calculateScore(millisRemaining);
        }

        player.setScore(player.getScore() + scoreToAdd);
    }

    /**
     * @return the round id
     */
    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }

    /**
     * Utility to calculate the score bestowed to the given plater
     * @return the value of the points to be bestowed
     */
    public double calculateScore(int milli){
        return milli*0.2;
    }
}
