package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightQuestionRoundTest {
    RightQuestionRound rightQuestionRound;

    @BeforeEach
    void setUp() {
        rightQuestionRound = new RightQuestionRound();
    }

    @Test
    void getRoundName_shouldReturnId1() {
        assertEquals(1, rightQuestionRound.getRoundName());
    }

    @Test
    void answerQuestion_whenAnswerCorrectly_thenShouldGet1000Points() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        rightQuestionRound.addQuestion(question); // (right answer is 0)

        rightQuestionRound.answerQuestion(player, 0);

        assertEquals(1000, player.getScore());
    }

    @Test
    void answerQuestion_whenAnswerWrong_thenShouldGetZeroPoints() {
        GamePlayer player = new GamePlayer();

        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        rightQuestionRound.addQuestion(question); // (right answer is 0)

        rightQuestionRound.answerQuestion(player, 1);

        assertEquals(0, player.getScore());
    }
}