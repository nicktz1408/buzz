package GameLogic;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for the Question (without an associated image)
 */
public class Question {
    private int rightAnswerIndex;
    private String questionText;
    private String category;
    private final List<String> answersList = new ArrayList<>();

    public Question() {

    }

    /**
     * Initializes the object from a Question.Builder class
     * @param builder the Builder to build our Question from
     */
    public Question(Builder<?> builder) {
        this.setCategory(builder.category);
        this.setQuestionText(builder.questionText);
        this.setAnswersList(builder.answersList);
        this.setRightAnswerIndex(builder.rightAnswerIndex);

        if(this.answersList.size() > 0) {
            this.shuffleAnswers();
        }
    }

    /**
     *
     * @return the category of the Question
     */
    public String getCategory() {
        return this.category;
    }

    /**
     *
     * @param category the category name to be set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return the question text
     */
    public String getQuestionText() {
        return this.questionText;
    }

    /**
     *
     * @param questionText the question text to be set
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     *
     * @return the answersList altogether as a List
     */
    public List<String> getAnswersList() {
        return this.answersList;
    }

    /**
     *
     * @param index the index of the answer to be returned
     * @return the answer text at the given index
     */
    public String getAnswerAtIndex(int index) throws IndexOutOfBoundsException {
        if(index >= this.answersList.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.answersList.get(index);
    }

    /**
     * Appends an answer to the answersList
     * @param answer the answer to be appended
     */
    public void addAnswer(String answer) {
        this.answersList.add(answer);
    }

    /**
     *
     * @param answersList the answerList to be set
     */
    public void setAnswersList(ArrayList<String> answersList) {
        for(int i = 0; i < answersList.size(); i++) {
            this.addAnswer(answersList.get(i));
        }
    }

    /**
     *
     * @return the index of the right answer
     */
    public int getRightAnswerIndex() {
        return this.rightAnswerIndex;
    }

    /**
     *
     * @return the right answer as a string
     */
    public String getRightAnswerAsText() {
        return this.answersList.get(this.rightAnswerIndex);
    }

    /**
     * In this method we shuffle the answers order randomly
     */
    private void shuffleAnswers(){
        int i=0,finalIndex = 0;
        String rightAnswer = this.getRightAnswerAsText();
        Collections.shuffle(this.answersList, new Random(System.currentTimeMillis()));
        for(String answers :answersList){
            if(answers.equals(rightAnswer)){
                finalIndex = i;
            }
            i++;
        }
        this.rightAnswerIndex = finalIndex;
    }

    /**
     *
     * @param index the index of the right answer to be set
     */
    public void setRightAnswerIndex(int index) {
        this.rightAnswerIndex = index;
    }

    /**
     * Check whether the answer at the given index is the correct one
     * @param index the index of the answer to be checked
     * @return whether this answer is correct or false
     */
    public boolean isAnswerCorrect(int index) {
        return index == this.rightAnswerIndex;
    }

    /**
     *
     * @return a new Builder object by initializing it
     */
    public static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }

    /**
     * Inner class for the Class' builder
     */
    public abstract static class Builder<T extends Builder<T>> {

        String category;
        String questionText;
        ArrayList<String> answersList = new ArrayList<>();
        int rightAnswerIndex;

        /**
         * To be used due to limitations of a Generic (can't reference the this keyword)
         * @return a refernce to itself (the this keyword)
         */
        public abstract T getThis();

        /**
         * Constructs the category
         * @param category the given category
         * @return the current Builder reference to allow stacking together function calls
         */
        public T setCategory(String category) {
            this.category = category;

            return this.getThis();
        }

        /**
         * Constructs the questionText
         * @param questionText the given questionText
         * @return the current Builder reference to allow stacking together function calls
         */
        public T setQuestionText(String questionText) {
            this.questionText = questionText;

            return this.getThis();
        }

        /**
         * Appends an answer to the answers Container
         * @param answer the given imagePath
         * @return the current Builder reference to allow stacking together function calls
         */
        public T addAnswer(String answer) {
            this.answersList.add(answer);

            return this.getThis();
        }

        /**
         * Constructs the rightAnswerIndex
         * @param rightAnswerIndex given rightAnswerIndex
         * @return the current Builder reference to allow stacking together function calls
         */
        public T setRightAnswerIndex(int rightAnswerIndex) {
            this.rightAnswerIndex = rightAnswerIndex;

            return this.getThis();
        }

        /**
         *
         * @return the constructed Question
         */
        public Question build() {
            return new Question(this);
        }
    }
}
