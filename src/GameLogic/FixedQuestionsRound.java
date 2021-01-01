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

    @Override
    public Question getCurrentQuestion(GamePlayer player) {
        if(questionsList.size() <= currQuestionIndex) {
            // throw new Exception();
            return null;
        }

        return questionsList.get(currQuestionIndex);
    }

    @Override
    public boolean fetchNextQuestion(GamePlayer player) {
        currQuestionIndex++;

        return currQuestionIndex < questionsList.size();
    }

    public void addQuestion(Question question) {
        questionsList.add(question);
    }
}