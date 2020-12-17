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

    public Question getSampleQuestion() {
        Question myQuestion = new Question();

        myQuestion.setQuestionText("Ερώτηση Χ");
        myQuestion.addAnswer("Answer 1");
        myQuestion.addAnswer("Answer 2");
        myQuestion.addAnswer("Answer 3");
        myQuestion.addAnswer("Answer 4");

        return myQuestion;
    }

    public void addRound(RoundInterface round) {
        roundList.add(round);
    }

    public Question getCurrentQuestion() {
        return this.getCurrentRound().getCurrentQuestion();
    }

    public FetchNextQuestionStatus fetchNextQuestion() {
        if(!this.getCurrentRound().fetchNextQuestion()) { // ran out of question for this round
            if(this.fetchNextRound()) { // successfully moved to the next round
                return FetchNextQuestionStatus.NEXT_ROUND;
            }

            return FetchNextQuestionStatus.GAME_FINISHED; // ran out of rounds, meaning the game has finished
        }

        return FetchNextQuestionStatus.NEXT_QUESTION;
    }

    private boolean fetchNextRound() {
        this.currRoundIndex++;

        return this.currRoundIndex < this.roundList.size();
    }

    private RoundInterface getCurrentRound() {
        return this.roundList.get(this.currRoundIndex);
    }
}
