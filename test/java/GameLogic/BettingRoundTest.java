package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BettingRoundTest {
    BettingRound bettingRound;

    @BeforeEach
    void setup() {
        bettingRound = new BettingRound();
    }

    @Test
    void getRoundName_shouldReturnId3() {
        assertEquals(3, bettingRound.getRoundName());
    }

    @Test
    void answerQuestion_whenAnswerWrong_thenSubtractBettingFromScore() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        bettingRound.addQuestion(question);

        bettingRound.answerQuestion(player, 1, 250); // bet 250 points

        assertEquals(-250, player.getScore());
    }

    @Test
    void answerQuestion_whenAnswerRight_thenAddBettingToScore() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        bettingRound.addQuestion(question);

        bettingRound.answerQuestion(player, 0, 750); // bet 750 points

        assertEquals(750, player.getScore());
    }

    @Test
    void answerQuestion_whenTwoPlayersPlay_thenBothGetPoints() {
        GamePlayer player1 = new GamePlayer();
        GamePlayer player2 = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        bettingRound.addQuestion(question);

        bettingRound.answerQuestion(player1, 0, 250); // bet 250 points
        bettingRound.answerQuestion(player2, 1, 1000); // bet 1000 points (lose)

        assertEquals(250, player1.getScore());
        assertEquals(-1000, player2.getScore());
    }
}