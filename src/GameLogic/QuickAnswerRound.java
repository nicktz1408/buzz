package GameLogic;

public class QuickAnswerRound extends FixedQuestionsRound{
    private final String ROUND_NAME="Γρήγορη Απάντηση";
    private boolean first;


    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
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
        if(first){
            return 1000;
        }
        return 500;
    }
    @Override
    public boolean fetchNextQuestion(){
        first = false;
        return super.fetchNextQuestion();
    }

    public void setFirst(boolean first){
        this.first = first;
    }

}
