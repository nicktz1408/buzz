package GameLogic;

import java.util.List;
import java.util.ArrayList;

/**
 * Class the glues together all parts of the logic of our application, to be used as an entry point from
 * any external sources, including the GUI
 */
public class GameFacade {
    private int currRoundIndex;
    private List<RoundInterface> roundList;

    public GameFacade() {
        this.currRoundIndex = 0;
        this.roundList = new ArrayList<RoundInterface>();
    }

    /**
     * Appends a new Round to the backlog (used only when creating the GameFacade, never during the game)
     * @param round the Round to be appended
     */
    public void addRound(RoundInterface round) {
        roundList.add(round);
    }

    /**
     * Shows the current Question associated with the given Player
     * @param player
     * @return the Question associated with the given Player
     */
    public Question getCurrentQuestion(GamePlayer player) {
        return this.getCurrentRound().getCurrentQuestion(player);
    }

    /**
     * Delegates the fetching the next Question of a given Player
     * @param player
     * @return a status that indicates whether the game has proceeded to a new question, to a new round
     * (if the previous round has finished) or wheter it has finished
     */
    public FetchNextQuestionStatus fetchNextQuestion(GamePlayer player) {
        if(!this.getCurrentRound().fetchNextQuestion(player)) { // ran out of question for this round
            if(this.fetchNextRound()) { // successfully moved to the next round
                return FetchNextQuestionStatus.NEXT_ROUND;
            }

            return FetchNextQuestionStatus.GAME_FINISHED; // ran out of rounds, meaning the game has finished
        }

        return FetchNextQuestionStatus.NEXT_QUESTION;
    }

    /**
     * Given a Player, answers the current Question (associated with them) and it adjusts the Player's
     * score accordingly
     * @param player
     * @param answerIndex the index (0 to 3) of the answer chosen by the Player
     * @param additionalRequestData potential additional data that might be needed by the various round types
     */
    public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
        this.getCurrentRound().answerQuestion(player, answerIndex,additionalRequestData);
    }

    /**
     * Registers a new Player
     * @return the newly registered Player
     */
    public GamePlayer registerPlayer() {
        return new GamePlayer();
    }

    /**
     * Utility function to get the next round (used when the previous one has finished)
     * @return whether the current (previous) round has finished or not
     */
    private boolean fetchNextRound() {
        if(this.currRoundIndex < this.roundList.size() - 1){
            this.currRoundIndex++;
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @return a reference to the current Round
     */
    public RoundInterface getCurrentRound() {
        return this.roundList.get(this.currRoundIndex);
    }

    /**
     * Tells us the arithmetic position of the current round (1st, 2nd, etc.)
     * @return the current Round index
     */
    public int getCurrentRoundIndex(){
        return this.currRoundIndex+1;
    }
}
