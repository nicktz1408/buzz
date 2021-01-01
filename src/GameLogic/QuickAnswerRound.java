package GameLogic;

public class QuickAnswerRound extends FixedQuestionsRound{
    private final int ROUND_NAME= 4;
    private boolean first;


    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            scoreToAdd = calculateScore();
        }

        player.setScore(player.getScore() + scoreToAdd);
    }

    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }

    public double calculateScore(){
        if(first){
            return 1000;
        }
        return 500;
    }
    @Override
    public boolean fetchNextQuestion(GamePlayer player){
        first = false;
        return super.fetchNextQuestion(player);
    }

    public void setFirst(boolean first){
        this.first = first;
    }

}
