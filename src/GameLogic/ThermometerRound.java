package GameLogic;

import java.util.HashMap;

/**
 * Class for the Thermometer Round Type (θερμόμετρο)
 */
public class ThermometerRound implements RoundInterface{
    private final int ROUND_NAME = 5;
    private HashMap<GamePlayer, PlayerData> playerData = new HashMap<>();

    /**
     * Inner class used to store data associated with a given user and needed for our class to work right
     * (stores the # of questions answered right and the current Question of the player)
     */
    class PlayerData {
        int rightAnswers;
        Question currentQuestion;

        /**
         * @param rightAnswers the value of rightAnswers to bet set (usually it increases it by 1)
         */
        void setRightAnswers(int rightAnswers) {
            this.rightAnswers = rightAnswers;
        }

        /**
         * @return the questions the player has answered right
         */
        int getRightAnswers() {
            return this.rightAnswers;
        }

        void setCurrentQuestion(Question currentQuestion) {
            this.currentQuestion = currentQuestion;
        }

        Question getCurrentQuestion() {
            return this.currentQuestion;
        }
    }

    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        double scoreToAdd = 0;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            increaseRightAnswer(player);
        }
        if(checkWin(player)){
            scoreToAdd = calculateScore();
            player.setScore(player.getScore() + scoreToAdd);
        }
    }

    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }

    @Override
    public Question getCurrentQuestion(GamePlayer player) {
        return this.getPlayerData(player).getCurrentQuestion();
    }

    @Override
    public boolean fetchNextQuestion(GamePlayer player) {
        if(checkWin(player)) {
            return false;
        } else {
            this.getPlayerData(player).setCurrentQuestion(this.getRandomQuestion());

            return true;
        }
    }

    public int getPlayerWins(GamePlayer player) {
        return this.getPlayerData(player).getRightAnswers();
    }

    private int calculateScore() {
        return 5000;
    }

    private void  increaseRightAnswer(GamePlayer player){
        int currWins = this.getPlayerData(player).getRightAnswers();

        this.getPlayerData(player).setRightAnswers(currWins + 1);
    }

    private boolean checkWin(GamePlayer player){
        return this.getPlayerData(player).getRightAnswers() >= 5;
    }

    private void createNewPlayerData(GamePlayer player) {
        PlayerData playerStatus = new PlayerData();
        playerData.put(player, playerStatus);

        playerStatus.setRightAnswers(0);
        playerStatus.setCurrentQuestion(this.getRandomQuestion());
    }

    private PlayerData getPlayerData(GamePlayer player) {
        if(!this.playerData.containsKey(player)) {
            this.createNewPlayerData(player);
        }

        return this.playerData.get(player);
    }

    private Question getRandomQuestion() {
        return QuestionRepository.getInstance().getSingleRandomQuestion();
    }
}
