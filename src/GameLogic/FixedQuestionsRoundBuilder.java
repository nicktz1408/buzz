package GameLogic;

public class FixedQuestionsRoundBuilder {
    private FixedQuestionsRound fixedQuestionsRound;

    public FixedQuestionsRoundBuilder() {

    }

    public FixedQuestionsRoundBuilder type(FixedQuestionsRound round) {
        this.fixedQuestionsRound = round;

        return this;
    }

    public FixedQuestionsRoundBuilder addQuestion(Question question) {
        this.fixedQuestionsRound.addQuestion(question);

        return this;
    }

    public FixedQuestionsRound build() {
        return this.fixedQuestionsRound;
    }
}
