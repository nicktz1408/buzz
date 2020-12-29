package GameLogic;

public class BettingRound extends FixedQuestionsRound{
    private final String ROUND_NAME = "Ποντάρισμα";

    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        int betSize = (int)additionalRequestData[0];
        int scoreToAdd = betSize;

        if(answerIndex == this.getCurrentQuestion().getRightAnswerIndex()) {
            player.setScore(player.getScore() + scoreToAdd);
        } else{
            player.setScore(player.getScore() - scoreToAdd);
        }
    }

    @Override
    public String getRoundName() {
        return this.ROUND_NAME;
    }
}
