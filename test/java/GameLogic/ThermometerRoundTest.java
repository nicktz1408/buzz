package GameLogic;

import MockedObjects.QuestionRepositoryMocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThermometerRoundTest {
    ThermometerRound thermometerRound;
    GamePlayer player1;
    GamePlayer player2;

    @BeforeEach
    void setup() {
        thermometerRound = new ThermometerRound(new QuestionRepositoryMocked());

        player1 = new GamePlayer();
        player2 = new GamePlayer();
    }

    @Test
    void getRoundName_shouldReturnId5() {
        assertEquals(5, thermometerRound.getRoundName());
    }

    @Test
    void getCurrentQuestion_shouldReturnCurrentQuestionForBothPlayers() throws Exception {
        Field currentQuestion = thermometerRound.getClass().getDeclaredField("currentQuestion");
        currentQuestion.setAccessible(true);

        assertEquals(currentQuestion.get(thermometerRound), thermometerRound.getCurrentQuestion(player1));
        assertEquals(currentQuestion.get(thermometerRound), thermometerRound.getCurrentQuestion(player2));
    }

    @Test
    void getPlayerWins_initialShouldReturnZero() {
        assertEquals(0, thermometerRound.getPlayerWins(player1));
    }

    @Test
    void getPlayerWins_whenAnswerRight_thenBecomeOne() {
        thermometerRound.answerQuestion(player1, 0); // correctly

        assertEquals(1, thermometerRound.getPlayerWins(player1));
    }

    @Test
    void answerQuestion_whenAnswerWrong_thenNumberOfWinsStaysSame() {
        thermometerRound.answerQuestion(player1, 1); // wrong, right is index 0

        assertEquals(0, thermometerRound.getPlayerWins(player1));
    }

    @Test
    void answerQuestion_whenAnswerRight_theNumberOfWinsIncreasesByOne() {
        thermometerRound.answerQuestion(player1, 0); // correctly

        assertEquals(1, thermometerRound.getPlayerWins(player1));
    }

    @Test
    void answerQuestion_whenBothAnswerRight_thenNumberOfWinsIncreasesByOneForBoth() {
        thermometerRound.answerQuestion(player1, 0); // correctly
        thermometerRound.answerQuestion(player2, 0); // correctly

        assertEquals(1, thermometerRound.getPlayerWins(player1));
        assertEquals(1, thermometerRound.getPlayerWins(player2));
    }

    @Test
    void fetchNextQuestion_whenCalled_thenCurrentQuestionGetsUpdated() {
        Question prev = thermometerRound.getCurrentQuestion(player1);

        thermometerRound.fetchNextQuestion(player1); // player doesn't matter

        assertTrue(!prev.equals(thermometerRound.getCurrentQuestion(player1)));
    }

    @Test
    void integrationTest_whenGets5Correct_thenAwarded5000Points() {
        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);

        assertEquals(5, thermometerRound.getPlayerWins(player1));
        assertEquals(5000, player1.getScore());
    }

    @Test
    void integrationTest_whenBothGet5Correct_thenFirstToAnswerGets5000Points() {
        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);

        assertEquals(5000, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @Test
    void integrationTest_whenBothGet5Correct_thenFirstToAnswerGets5000Points_reverseOrder() {
        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player1, 0);
        thermometerRound.answerQuestion(player2, 0);
        thermometerRound.fetchNextQuestion(player1);

        thermometerRound.answerQuestion(player2, 0); // reverse order
        thermometerRound.answerQuestion(player1, 0);

        assertEquals(0, player1.getScore());
        assertEquals(5000, player2.getScore());
    }
}