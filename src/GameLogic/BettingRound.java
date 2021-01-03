package GameLogic;

/**
 * Class for the Betting Round Type (ποντάρισμα)
 */
public class BettingRound extends FixedQuestionsRound{
    private final int ROUND_NAME = 3;

    /**
     * Answers the current question by a given player
     * @param player the player that answers the question
     * @param answerIndex the chosen index of the answer
     * @param additionalRequestData {int betSize} a single parameter for the amount of the bet by the player
     */
    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        int betSize = (int)additionalRequestData[0];
        int scoreToAdd = betSize;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            player.setScore(player.getScore() + scoreToAdd);
        } else{
            player.setScore(player.getScore() - scoreToAdd);
        }
    }

    /**
     * @return the round id
     */
    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }
}
