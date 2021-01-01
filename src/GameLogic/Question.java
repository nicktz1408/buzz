package GameLogic;

import java.util.List;
import java.util.ArrayList;

public class Question {
    private int rightAnswerIndex;
    private String questionText;
    private String category;
    private List<String> answersList;

    public Question() {
        this.answersList = new ArrayList<String>();
    }

    public Question(Builder<?> builder) {
        this.category = builder.category;
        this.questionText = builder.questionText;
        this.answersList = builder.answersList;
        this.rightAnswerIndex = builder.rightAnswerIndex;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswersList() {
        return this.answersList;
    }

    public String getAnswerAtIndex(int index) {
        return this.answersList.get(index);
    }

    public void addAnswer(String answer) {
        this.answersList.add(answer);
    }

    public int getRightAnswerIndex() {
        return this.rightAnswerIndex;
    }

    public String getRightAnswerAsText() {
        return this.answersList.get(this.rightAnswerIndex);
    }

    public void setRightAnswerIndex(int index) {
        this.rightAnswerIndex = index;
    }

    public boolean isAnswerCorrect(int index) {
        return index == this.rightAnswerIndex;
    }

    public static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }

    public abstract static class Builder<T extends Builder<T>> {
        private Question question;

        String category;
        String questionText;
        ArrayList<String> answersList = new ArrayList<>();
        int rightAnswerIndex;

        public abstract T getThis();

        public Builder() {
            question = this.getNewQuestion();
        }

        public T setCategory(String category) {
            // question.setCategory(category);
            this.category = category;

            return this.getThis();
        }

        public T setQuestionText(String questionText) {
            // question.setQuestionText(questionText);
            this.questionText = questionText;

            return this.getThis();
        }

        public T addAnswer(String answer) {
            // question.addAnswer(answer);
            this.answersList.add(answer);

            return this.getThis();
        }

        public T setRightAnswerIndex(int rightAnswerIndex) {
            // question.setRightAnswerIndex(rightAnswerIndex);
            this.rightAnswerIndex = rightAnswerIndex;

            return this.getThis();
        }

        public Question build() {
            // Question fetched = this.question;
            // question = this.getNewQuestion();

            // return fetched;
            return new Question(this);
        }

        private Question getNewQuestion() {
            return new Question();
        }
    }
}
