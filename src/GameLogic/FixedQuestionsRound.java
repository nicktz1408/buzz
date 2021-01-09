package GameLogic;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that encapsulates repeated functionality among rounds that feature common Questions for each Player
 */
public abstract class FixedQuestionsRound implements RoundInterface {
    private int currentQuestionIndex;
    private final List<Question> questionsList;

    public FixedQuestionsRound() {
        currentQuestionIndex = 0;
        questionsList = new ArrayList<>();
    }

    /**
     * Returns the current question on the queue
     * @param player the associated player we need to apply the action on
     * @return the current Question
     */
    @Override
    public Question getCurrentQuestion(GamePlayer player) {
        if(questionsList.size() <= currentQuestionIndex) {
            throw new IndexOutOfBoundsException();
        }

        return questionsList.get(currentQuestionIndex);
    }

    /**
     * Instructs the system to move to the next question
     * @param player the associated player we need to apply the action on
     * @return true if there is new question, false if the round is over.
     */
    @Override
    public boolean fetchNextQuestion(GamePlayer player) {
        if(currentQuestionIndex >= questionsList.size() - 1) {
            currentQuestionIndex = questionsList.size();

            return false;
        }

        currentQuestionIndex++;
        return true;
    }

    /**
     * Adds a question to our Container (to be used ONLY while building the round)
     * @param question the question to be added
     */
    public void addQuestion(Question question) {
        questionsList.add(question);
    }
}