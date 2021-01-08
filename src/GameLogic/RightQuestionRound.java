package GameLogic;

public class RightQuestionRound extends FixedQuestionsRound {
    private final int ROUND_NAME = 1;

    /**
     * @return the round id
     */
    public int getRoundName() {
        return this.ROUND_NAME;
    }

    /**
     * Answers the current question by a given player
     * @param player the player that answers the question
     * @param answerIndex the chosen index of the answer
     * @param additionalRequestData {int betSize} a single parameter for the amount of the bet by the player
     */
    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        int scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            scoreToAdd = calculateScore();
        }

        player.setScore(player.getScore() + scoreToAdd);
    }

    /**
     * Utility to calculate the score bestowed to the given plater
     * @return the value of the points to be bestowed
     */
    int calculateScore() {
        return 1000;
    }
}
