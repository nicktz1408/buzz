package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class QuickAnswerRoundTest {
    QuickAnswerRound quickAnswerRound;
    GamePlayer player1;
    GamePlayer player2;

    @BeforeEach
    void setup() {
        quickAnswerRound = new QuickAnswerRound();

        player1 = new GamePlayer();
        player2 = new GamePlayer();
    }

    @Test
    void getRoundName_shouldReturnId4() {
        assertEquals(4, quickAnswerRound.getRoundName());
    }

    @Test
    void answerQuestion_whenBothAnswerWrong_thenBothGetZeroPoints() {
        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        quickAnswerRound.addQuestion(question);

        quickAnswerRound.answerQuestion(player1, 1); // wrong answer
        quickAnswerRound.answerQuestion(player2, 1); // wrong answer

        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
    }

    @Test
    void answerQuestion_whenBothAnswerRight_thenFaster1000PointsSlower500() {
        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        quickAnswerRound.addQuestion(question);

        quickAnswerRound.answerQuestion(player1, 0); // faster
        quickAnswerRound.answerQuestion(player2, 0); // slower

        assertEquals(1000, player1.getScore());
        assertEquals(500, player2.getScore());
    }

    @Test
    void answerQuestion_whenBothAnswerRight_inReverse() {
        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        quickAnswerRound.addQuestion(question);

        quickAnswerRound.answerQuestion(player2, 0); // faster
        quickAnswerRound.answerQuestion(player1, 0); // slower

        assertEquals(500, player1.getScore());
        assertEquals(1000, player2.getScore());
    }

    @Test
    void answerQuestion_whenFirstAnswerWrongSecondRight_thenSecondGets1000Points() {
        Question question = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        quickAnswerRound.addQuestion(question);

        quickAnswerRound.answerQuestion(player1, 1); // first wrong
        quickAnswerRound.answerQuestion(player2, 0); // second right

        assertEquals(0, player1.getScore());
        assertEquals(1000, player2.getScore());
    }

    @Test
    void fetchNextQuestion_whenCalled_thenPlayerOrderShouldBeRestored() {
        Question question1 = Question.builder().addAnswer("random answer").setRightAnswerIndex(0).build();
        Question question2 = Question.builder().addAnswer("random answer 2").setRightAnswerIndex(0).build();
        quickAnswerRound.addQuestion(question1);
        quickAnswerRound.addQuestion(question2);

        quickAnswerRound.answerQuestion(player1, 0); // both right
        quickAnswerRound.answerQuestion(player2, 0);

        quickAnswerRound.fetchNextQuestion(player1); // move to next question, parameter doesn't matter

        quickAnswerRound.answerQuestion(player1, 0); // should get again 1000 points, 2000 overall

        assertEquals(2000, player1.getScore());
        assertEquals(500, player2.getScore());
    }
}