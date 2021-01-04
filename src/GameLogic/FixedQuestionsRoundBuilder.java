package GameLogic;

/**
 * Builder Class for the FixedQuestionsRound
 */
public class FixedQuestionsRoundBuilder {
    private FixedQuestionsRound fixedQuestionsRound;

    public FixedQuestionsRoundBuilder() {

    }

    /**
     * Sets the FixedQuestionsRound type
     * @param round the round type to be set
     * @return the current FixedQuestionsRoundBuilder reference to allow stacking together function calls
     */
    public FixedQuestionsRoundBuilder type(FixedQuestionsRound round) {
        this.fixedQuestionsRound = round;

        return this;
    }

    /**
     * Appends the given Question to our FixedQuestionsRound
     * @param question the Question to be appended
     * @return the current FixedQuestionsRoundBuilder reference to allow stacking together function calls
     */
    public FixedQuestionsRoundBuilder addQuestion(Question question) {
        this.fixedQuestionsRound.addQuestion(question);

        return this;
    }

    /**
     *
     * @return the constructed FixedQuestionsRound object
     */
    public FixedQuestionsRound build() {
        return this.fixedQuestionsRound;
    }
}
