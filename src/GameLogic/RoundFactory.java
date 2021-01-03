package GameLogic;

public class RoundFactory {
    public RoundFactory() {

    }

    public RightQuestionRound getRightQuestionAnswerRound() {
        return new RightQuestionRound();
    }

    public StopClockRound getStopClockRound(){
        return new StopClockRound();
    }

    public BettingRound getBettingRound(){
        return new BettingRound();
    }

    public QuickAnswerRound getQuickAnswerRound() {
        return new QuickAnswerRound();
    }

    public ThermometerRound getThermometerRound() {
        return new ThermometerRound();
    }
}
