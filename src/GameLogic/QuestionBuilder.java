package GameLogic;

public class QuestionBuilder {
    private Question question;

    public QuestionBuilder() {
        question = this.getNewQuestion();
    }

    public QuestionBuilder setCategory(String category) {
        question.setCategory(category);

        return this;
    }

    public QuestionBuilder setQuestionText(String questionText) {
        question.setQuestionText(questionText);

        return this;
    }

    public QuestionBuilder addAnswer(String answer) {
        question.addAnswer(answer);

        return this;
    }

    public QuestionBuilder setRightAnswerIndex(int rightAnswerIndex) {
        question.setRightAnswerIndex(rightAnswerIndex);

        return this;
    }

    public Question build() {
        Question fetched = this.question;
        question = this.getNewQuestion();

        return fetched;
    }

    private Question getNewQuestion() {
        return new Question();
    }
}
