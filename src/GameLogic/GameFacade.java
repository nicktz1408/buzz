package GameLogic;

import java.util.List;
import java.util.ArrayList;

public class GameFacade {
    private int currRoundIndex;
    private List<RoundInterface> roundList;

    public GameFacade() {
        this.currRoundIndex = 0;
        this.roundList = new ArrayList<RoundInterface>();
    }

    public void addRound(RoundInterface round) {
        roundList.add(round);
    }

    public Question getCurrentQuestion(GamePlayer player) {
        return this.getCurrentRound().getCurrentQuestion(player);
    }

    public FetchNextQuestionStatus fetchNextQuestion(GamePlayer player) {
        if(!this.getCurrentRound().fetchNextQuestion(player)) { // ran out of question for this round
            if(this.fetchNextRound()) { // successfully moved to the next round
                return FetchNextQuestionStatus.NEXT_ROUND;
            }

            return FetchNextQuestionStatus.GAME_FINISHED; // ran out of rounds, meaning the game has finished
        }

        return FetchNextQuestionStatus.NEXT_QUESTION;
    }

    public void answerQuestion(GamePlayer player, int rightAnswerIndex) {
        this.getCurrentRound().answerQuestion(player, rightAnswerIndex);
    }

    public GamePlayer registerPlayer() {
        return new GamePlayer();
    }

    private boolean fetchNextRound() {
        this.currRoundIndex++;

        return this.currRoundIndex < this.roundList.size();
    }

    public RoundInterface getCurrentRound() {
        return this.roundList.get(this.currRoundIndex);
    }

    public int getCurrRoundIndex(){
        return this.currRoundIndex+1;
    }
}
