package GameLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FixedQuestionsRound implements RoundInterface {
    private int currQuestionIndex;
    private List<Question> questionsList;

    public FixedQuestionsRound() {
        currQuestionIndex = 0;
        questionsList = new ArrayList<Question>();
    }

    /**
     * Returns the current question on the queue
     * @param player the associated player we need to apply the action on
     * @return the current Question
     */
    @Override
    public Question getCurrentQuestion(GamePlayer player) {
        if(questionsList.size() <= currQuestionIndex) {
            // throw new Exception();
            return null;
        }

        return questionsList.get(currQuestionIndex);
    }

    /**
     * Instructs the system to move to the next question
     * @param player the associated player we need to apply the action on
     * @return
     */
    @Override
    public boolean fetchNextQuestion(GamePlayer player) {
        currQuestionIndex++;

        return currQuestionIndex < questionsList.size();
    }

    /**
     * Adds a question to our Container (to be used ONLY while building the round)
     * @param question the question to be added
     */
    public void addQuestion(Question question) {
        questionsList.add(question);
    }
}