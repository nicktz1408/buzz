package GameLogic;

/**
 * Class with functions that initialize the various Round types (to be used instead of the classic initialization
 * using the new keyword)
 */
public class RoundFactory {
    /**
     *
     * @return a new RightQuestionRound object
     */
    public RightQuestionRound getRightQuestionRound() {
        return new RightQuestionRound();
    }

    /**
     *
     * @return a new StopTheClockRound object
     */
    public StopClockRound getStopClockRound(){
        return new StopClockRound();
    }

    /**
     *
     * @return a new BettingRound object
     */
    public BettingRound getBettingRound(){
        return new BettingRound();
    }

    /**
     *
     * @return a new QuickAnswerRound object
     */
    public QuickAnswerRound getQuickAnswerRound() {
        return new QuickAnswerRound();
    }

    /**
     *
     * @return a new ThermometerRound object
     */
    public ThermometerRound getThermometerRound() {
        return new ThermometerRound();
    }
}
