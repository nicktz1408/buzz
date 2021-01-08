package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopClockRoundTest {
    StopClockRound stopClockRound;

    @BeforeEach
    void setUp() {
        stopClockRound = new StopClockRound();
    }

    @Test
    void getRoundName_shouldReturnId2() {
        assertEquals(2, stopClockRound.getRoundName());
    }

    @Test
    void answerQuestion_whenAnswerWrong_thenShouldGetZeroPoints() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        stopClockRound.addQuestion(question); // (right answer is 0)

        stopClockRound.answerQuestion(player, 1, 0);

        assertEquals(0, player.getScore());
    }

    @Test
    void answerQuestion_whenAnswerRightAndRanOutOfTime_thenShouldGetZeroPoints() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        stopClockRound.addQuestion(question); // (right answer is 0)

        stopClockRound.answerQuestion(player, 0, 0);

        assertEquals(0, player.getScore());
    }

    @Test
    void answerQuestion_whenAnswerRightAndLeft1Second_thenShouldGet200Points() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        stopClockRound.addQuestion(question); // (right answer is 0)

        stopClockRound.answerQuestion(player, 0, 1000); // 1000 ms = 1 sec

        assertEquals(200, player.getScore());
    }
}