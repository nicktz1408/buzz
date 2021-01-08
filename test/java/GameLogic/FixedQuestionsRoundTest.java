package GameLogic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

class FixedQuestionsRoundTest {
    FixedQuestionsRound fixedRound;

    @BeforeEach
    void setUp() {
        // Create a random subclass to test the already implemented methods
        fixedRound = new FixedQuestionsRound() {
            @Override
            public void answerQuestion(GamePlayer player, int answerIndex, Object... additionalRequestData) {
                System.out.println("Current Question has been answered!");
            }

            @Override
            public int getRoundName() {
                return 0;
            }
        };
    }

    @Test
    void addQuestion_single() throws Exception {
        final Field questionsListField = fixedRound.getClass().getSuperclass().getDeclaredField("questionsList");
        questionsListField.setAccessible(true);

        Question q1 = Question.builder().setQuestionText("test1").build(); // populate it randomly
        fixedRound.addQuestion(q1);

        assertEquals(q1, ((List)questionsListField.get(fixedRound)).get(0));
    }

    @Test
    void addQuestion_multiple() throws Exception {
        final Field questionsListField = fixedRound.getClass().getSuperclass().getDeclaredField("questionsList");
        questionsListField.setAccessible(true);

        Question q1 = Question.builder().setQuestionText("test1").build(); // populate it randomly
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build(); // populate it randomly
        fixedRound.addQuestion(q2);

        assertEquals(q1, ((List)questionsListField.get(fixedRound)).get(0));
        assertEquals(q2, ((List)questionsListField.get(fixedRound)).get(1));
    }

    @Test
    void getCurrentQuestion_whenStillInFirstQuestion_thenReturnFirstQuestion() {
        Question q1 = Question.builder().setQuestionText("test1").build();
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build();
        fixedRound.addQuestion(q2);

        assertEquals(q1, fixedRound.getCurrentQuestion(new GamePlayer()));
    }

    @Test
    void getCurrentQuestion_whenMovedToSecondQuestions_thenReturnSecondQuestion() {
        Question q1 = Question.builder().setQuestionText("test1").build();
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build();
        fixedRound.addQuestion(q2);

        GamePlayer randomPlayer = new GamePlayer();
        fixedRound.fetchNextQuestion(randomPlayer); // move to 2nd question

        assertEquals(q2, fixedRound.getCurrentQuestion(randomPlayer));
    }

    @Test
    void getCurrentQuestion_whenMovedOutOfBounds_thenRaiseIndexOutOfBoundsException() {
        Question q1 = Question.builder().setQuestionText("test1").build();
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build();
        fixedRound.addQuestion(q2);

        GamePlayer randomPlayer = new GamePlayer();
        fixedRound.fetchNextQuestion(randomPlayer); // move to 2nd question, legal
        fixedRound.fetchNextQuestion(randomPlayer); // leads to out of bounds (no more getCurrentQuestion calls are allowed)

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            fixedRound.getCurrentQuestion(randomPlayer); // not allowed!
        });

        assertTrue(exception instanceof IndexOutOfBoundsException);
    }

    @Test
    void fetchNextQuestion_whenCalled_thenCurrentQuestionIndexIncreasedByOne() throws Exception {
        final Field currentQuestionIndexField = fixedRound.getClass().getSuperclass().getDeclaredField("currentQuestionIndex");
        currentQuestionIndexField.setAccessible(true);

        Question q1 = Question.builder().setQuestionText("test1").build();
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build();
        fixedRound.addQuestion(q2);

        fixedRound.fetchNextQuestion(new GamePlayer());

        assertEquals(1, currentQuestionIndexField.get(fixedRound));
    }

    @Test
    void fetchNextQuestion_whenCalledAndIndexNotOutOfBounds_thenReturnTrue() {
        Question q1 = Question.builder().setQuestionText("test1").build();
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build();
        fixedRound.addQuestion(q2);

        assertTrue(fixedRound.fetchNextQuestion(new GamePlayer()));
    }

    @Test
    void fetchNextQuestion_whenCalledAndIndexOutOfBounds_thenReturnFalse() {
        Question q1 = Question.builder().setQuestionText("test1").build();
        fixedRound.addQuestion(q1);

        Question q2 = Question.builder().setQuestionText("test2").build();
        fixedRound.addQuestion(q2);

        GamePlayer randomPlayer = new GamePlayer();

        fixedRound.fetchNextQuestion(randomPlayer); // move to 2nd question, legal

        assertTrue(!fixedRound.fetchNextQuestion(new GamePlayer())); // move to out of bounds, should return false
    }
}