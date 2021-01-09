package GameLogic;

/**
 * Class for the Quick Answer Round Type (γρήγορη απάντηση)
 */
public class QuickAnswerRound extends FixedQuestionsRound{
    private final int ROUND_NAME= 4;
    private boolean isFirst = true;


    /**
     * Answers the current question by a given player
     * @param player the player that answers the question
     * @param answerIndex the chosen index of the answer
     * @param additionalRequestData {int betSize} a single parameter for the amount of the bet by the player
     */
    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            scoreToAdd = calculateScore();
            isFirst = false; // for when the other player answers
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
     * Utility to calculate the score bestowed to/subtracted from the given plater
     * @return the value of the points to be bestowed/subtracted
     */
    public double calculateScore(){
        return (isFirst ? 1000 : 500);
    }

    /**
     * Overrides the default functionality from the FixedQuestionsRound
     * @param player the associated player we need to apply the action on
     * @return true if the round has finished (no more questions available or concluded a winner), false otherwise
     */
    @Override
    public boolean fetchNextQuestion(GamePlayer player){
        isFirst = true; // return to default
        return super.fetchNextQuestion(player);
    }

    /**
     * @param isFirst the value to be set
     */
    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    /**
     * @return the value of isFirst
     */
    public boolean getIsFirst() {
        return this.isFirst;
    }
}
