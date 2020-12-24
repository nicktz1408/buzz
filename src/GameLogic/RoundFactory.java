package GameLogic;

public class RoundFactory {
    public RoundFactory() {

    }

    public RightQuestionRound getRightQuestionAnswerRound() {
        return new RightQuestionRound();
    }
}
