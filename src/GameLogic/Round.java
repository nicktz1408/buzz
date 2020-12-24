package GameLogic;

import java.util.List;
import java.util.ArrayList;

public abstract class Round {
    private int currQuestionIndex;
    private List <Question> questionsList;

    public Round() {
        currQuestionIndex = 0;
        questionsList = new ArrayList<Question> ();
    }   

    public Question getCurrentQuestion() {
        if(questionsList.size() >= currQuestionIndex) {
            // throw new Exception();
            return null;
        }

        return questionsList.get(currQuestionIndex);
    }

    public boolean nextQuestion() {
        currQuestionIndex++;

        return currQuestionIndex < questionsList.size();
    }

    public void addQuestion(Question question) {
        questionsList.add(question);
    }

    public int answerQuestion(int answerIndex) {
        if(getCurrentQuestion().isAnswerCorrect(answerIndex)) {
            return calculateScore();
        } else {
            return 0;
        }
    }

    abstract public int calculateScore();
    //abstract public
}