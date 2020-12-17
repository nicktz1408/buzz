public class RightQuestionRound extends PredefinedQuestionsRound {
    public void answerQuestion(Player player, int answerIndex) {
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
