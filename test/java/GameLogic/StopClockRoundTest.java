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

        stopClockRound.answerQuestion(player, 1, 10);

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

    @Test
    void answerQuestion_whenTwoPlayersPlay_thenBothGetPoints() {
        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        stopClockRound.addQuestion(question); // (right answer is 0)

        stopClockRound.answerQuestion(player1, 0, 1000); // 1000 ms = 1 sec
        stopClockRound.answerQuestion(player2, 0, 2000); // 2000 ms = 2 sc

        assertEquals(200, player1.getScore());
        assertEquals(400, player2.getScore());
    }
}