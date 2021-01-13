package GameLogic;

import java.util.HashMap;

/**
 * Class for the Thermometer Round Type (θερμόμετρο)
 */
public class ThermometerRound implements RoundInterface{
    private final int ROUND_NAME = 5;
    private final HashMap<GamePlayer, PlayerData> playerData = new HashMap<>();
    private Question currentQuestion;
    private boolean hasWon = false;
    private QuestionRepository questionRepository;

    /**
     * Default constructor to be called
     */
    public ThermometerRound(){
        questionRepository = QuestionRepository.getInstance();

        currentQuestion = this.getRandomQuestion();
    }

    /**
     * Constructor used for testing
     * @param questionRepository the mocked QuestionRepository object
     */
    public ThermometerRound(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;

        currentQuestion = this.getRandomQuestion();
    }




    /**
     * Inner class used to store data associated with a given user and needed for our class to work right
     * (stores the # of questions answered right and the current Question of the player)
     */
    class PlayerData {
        int rightAnswers;

        /**
         * @param rightAnswers the value of rightAnswers to bet set (usually it is being increased it by 1)
         */
        void setRightAnswers(int rightAnswers) {
            this.rightAnswers = rightAnswers;
        }

        /**
         * @return the number of questions the Player has answered correctly
         */
        int getRightAnswers() {
            return this.rightAnswers;
        }


    }
    /**
     *
     * @param currentQuestion the current Question to be set for the Player
     */
    private void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }


    /**
     *
     * @param player the player that answers the question
     * @param answerIndex the chosen index of the answer
     * @param additionalRequestData {int betSize} a single parameter for the amount of the bet by the player
     */
    @Override
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        double scoreToAdd;

        if(answerIndex == this.getCurrentQuestion(player).getRightAnswerIndex()) {
            increaseRightAnswer(player);
        }

        if(getPlayerWins(player) >= 5 && !hasDeterminedWinner()){
            scoreToAdd = calculateScore();
            player.setScore(player.getScore() + scoreToAdd);
            hasWon = true;
        }
    }

    /**
     * @return the round id
     */
    @Override
    public int getRoundName() {
        return this.ROUND_NAME;
    }


    @Override
    public Question getCurrentQuestion(GamePlayer player) {
        return this.currentQuestion;
    }

    /**
     * Instructs the class to fetch the next question for the given Player
     * @param player the Player whose question will be updated
     * @return true if the round has finished (a winner has been concluded), false otherwise
     */
    @Override
    public boolean fetchNextQuestion(GamePlayer player) {
        if(hasDeterminedWinner()) {
            return false;
        } else {
            this.setCurrentQuestion(this.getRandomQuestion());
            return true;
        }
    }

    /**
     * Retrieves the # of correct answers of the Player
     * @param player the Player
     * @return the number of correct answers of the given Player
     */
    public int getPlayerWins(GamePlayer player) {
        return this.getPlayerData(player).getRightAnswers();
    }

    /**
     * Utility function to calculate the score bestowed to the given player
     * @return the value of the points to be bestowed
     */
    private int calculateScore() {
        return 5000;
    }

    /**
     * Utility function to increase the count of correct answers of the given Player
     * @param player the associated Player
     */
    private void  increaseRightAnswer(GamePlayer player){
        int currWins = this.getPlayerData(player).getRightAnswers();

        this.getPlayerData(player).setRightAnswers(currWins + 1);
    }

    /**
     * Utility function to determine whether the given Player has won the game (accumulated >= 5 correct answers)
     * @return whether they won or lost
     */
    private boolean hasDeterminedWinner(){
        return hasWon;
    }


    /**
     * Utility function to create a new entry for the player and add it to the HashMap
     * @param player the Player to create the entry for
     */
    private void createNewPlayerData(GamePlayer player) {
        PlayerData playerStatus = new PlayerData();
        playerData.put(player, playerStatus);

        playerStatus.setRightAnswers(0);
    }

    /**
     * Utility function to get the associated PlayerData of the given player from the HashMap
     * @param player the Player whose data will be retrieved
     * @return the associated PlayerData object
     */
    private PlayerData getPlayerData(GamePlayer player) {
        if(!this.playerData.containsKey(player)) {
            this.createNewPlayerData(player);
        }

        return this.playerData.get(player);
    }

    /**
     * Utility function to get a random question from the QuestionRepository
     * @return a random Question
     */
    private Question getRandomQuestion() {
        return this.questionRepository.getSingleRandomQuestion();
    }
}
