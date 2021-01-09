package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundFactoryTest {
    RoundFactory roundFactory;

    @BeforeEach
    void setup() {
        roundFactory = new RoundFactory();
    }

    @Test
    void getRightQuestionRound_returnNewInstanceOfRightQuestionRound() {
        assertTrue(roundFactory.getRightQuestionRound() instanceof RightQuestionRound);
    }

    @Test
    void getStopClockRound_returnNewInstanceOfStopClockRound() {
        assertTrue(roundFactory.getStopClockRound() instanceof StopClockRound);
    }

    @Test
    void getBettingRound_returnNewInstanceOfBettingRound() {
        assertTrue(roundFactory.getBettingRound() instanceof BettingRound);
    }

    @Test
    void getQuickAnswerRound_returnNewInstanceOfQuickAnswerRound() {
        assertTrue(roundFactory.getQuickAnswerRound() instanceof QuickAnswerRound);
    }

    @Test
    void getThermometerRound_returnNewInstanceOfThermometerRound() {
        assertTrue(roundFactory.getThermometerRound() instanceof ThermometerRound);
    }
}