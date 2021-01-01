package GameLogic;

public class BettingRound extends FixedQuestionsRound{
    private final int ROUND_NAME = 3;

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

    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }
}
