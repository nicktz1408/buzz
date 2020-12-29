package GameLogic;

import java.util.HashMap;

public class ThermometerRound extends FixedQuestionsRound{
    private final String ROUND_NAME = "Θερμόμετρο";
    private HashMap<GamePlayer, Integer> rightAnswers = new HashMap<>();

    @Override
    public void answerQuestion(GamePlayer player, int answerIndex) {
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion().getRightAnswerIndex()) {
            increaseRightAnswer(player);
        }
        if(checkWin(player)){
            scoreToAdd = calculateScore();
            player.setScore(player.getScore() + scoreToAdd);
        }
    }

    @Override
    public String getRoundName() {
        return this.ROUND_NAME;
    }




    private void  increaseRightAnswer(GamePlayer player){
        int answer = 1;
        if(rightAnswers.containsKey(player)) {
            answer = rightAnswers.get(player) + 1;
        }
        rightAnswers.put(player, answer);
    }
    public int calculateScore(){
        return 1000;
    }


    private boolean checkWin(GamePlayer player){
        int totalRight = rightAnswers.get(player);
        return (totalRight >= 5);
    }
}
