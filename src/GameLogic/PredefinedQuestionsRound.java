import java.util.ArrayList;
import java.util.List;

public abstract class PredefinedQuestionsRound implements RoundInterface {
    private int currQuestionIndex;
    private List<Question> questionsList;

    public PredefinedQuestionsRound() {
        currQuestionIndex = 0;
        questionsList = new ArrayList<Question>();
    }

    public Question getCurrentQuestion() {
        if(questionsList.size() >= currQuestionIndex) {
            // throw new Exception();
            return null;
        }

        return questionsList.get(currQuestionIndex);
    }

    public boolean fetchNextQuestion() {
        currQuestionIndex++;

        return currQuestionIndex < questionsList.size();
    }

    public void addQuestion(Question question) {
        questionsList.add(question);
    }
}