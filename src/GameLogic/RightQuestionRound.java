package GameLogic;

public class RightQuestionRound extends FixedQuestionsRound {
    private final String ROUND_NAME = "Σωστή Απάντηση";

    public String getRoundName() {
        return this.ROUND_NAME;
    }

    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        int scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion().getRightAnswerIndex()) {
            scoreToAdd = calculateScore();
        }

        // TODO (@nicktz1408): if codebase grows big enough, replace this with a Command object on Game (ie. gameCommand.addScore(player, score);
        player.setScore(player.getScore() + scoreToAdd);
    }

    int calculateScore() {
        return 1000;
    }
}
