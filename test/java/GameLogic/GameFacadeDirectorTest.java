package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameFacadeDirectorTest {
    GameFacadeDirector director;

    @BeforeEach
    void setup() {
        director = new GameFacadeDirector();
    }

    @Test
    void buildSoloGame_shouldBuildGameAsExpected() throws Exception {
        GameFacade game = director.buildSoloGame();

        Field roundListField = game.getClass().getDeclaredField("roundList");
        roundListField.setAccessible(true);
        List<RoundInterface> roundList = (List)roundListField.get(game);

        assertTrue(roundList.size() == 6);

        assertTrue(roundList.get(0) instanceof RightQuestionRound);
        assertTrue(roundList.get(1) instanceof BettingRound);
        assertTrue(roundList.get(2) instanceof StopClockRound);
        assertTrue(roundList.get(3) instanceof BettingRound);
        assertTrue(roundList.get(4) instanceof StopClockRound);
        assertTrue(roundList.get(5) instanceof RightQuestionRound);
    }

    @Test
    void buildTwoPlayersGame_shouldBuildGameAsExpected() throws Exception {
        GameFacade game = director.buildTwoPlayersGame();

        Field roundListField = game.getClass().getDeclaredField("roundList");
        roundListField.setAccessible(true);
        List<RoundInterface> roundList = (List)roundListField.get(game);

        assertTrue(roundList.size() == 6);

        assertTrue(roundList.get(0) instanceof RightQuestionRound);
        assertTrue(roundList.get(1) instanceof StopClockRound);
        assertTrue(roundList.get(2) instanceof BettingRound);
        assertTrue(roundList.get(3) instanceof ThermometerRound);
        assertTrue(roundList.get(4) instanceof QuickAnswerRound);
        assertTrue(roundList.get(5) instanceof RightQuestionRound);
    }
}