package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
    }

    @Test
    void setCategory() throws Exception {
        final Field categoryField = question.getClass().getDeclaredField("category");
        categoryField.setAccessible(true);

        question.setCategory("test");

        assertEquals("test", categoryField.get(question));
    }

    @Test
    void getCategory() throws Exception {
        final Field categoryField = question.getClass().getDeclaredField("category");
        categoryField.setAccessible(true);

        question.setCategory("test");

        assertEquals(categoryField.get(question), question.getCategory());
        assertEquals("test", question.getCategory());
    }

    @Test
    void setQuestionText() throws Exception {
        final Field questionTextField = question.getClass().getDeclaredField("questionText");
        questionTextField.setAccessible(true);

        question.setQuestionText("test");

        assertEquals("test", questionTextField.get(question));
    }

    @Test
    void getQuestionText() throws Exception {
        final Field questionTextField = question.getClass().getDeclaredField("questionText");
        questionTextField.setAccessible(true);
        question.setQuestionText("test");

        assertEquals(questionTextField.get(question), question.getQuestionText());
        assertEquals("test", question.getQuestionText());
    }

    @Test
    void addSingleAnswer() throws Exception {
        final Field answersListField = question.getClass().getDeclaredField("answersList");
        answersListField.setAccessible(true);

        question.addAnswer("test1");

        assertEquals("test1", ((List)answersListField.get(question)).get(0));
    }

    @Test
    void addMultipleAnswers() throws Exception {
        final Field answersListField = question.getClass().getDeclaredField("answersList");
        answersListField.setAccessible(true);

        question.addAnswer("test1");
        assertEquals("test1", ((List)answersListField.get(question)).get(0));

        question.addAnswer("test2");
        assertEquals("test2", ((List)answersListField.get(question)).get(1));
    }

    @Test
    void setAnswersList() throws Exception {
        final Field answersListField = question.getClass().getDeclaredField("answersList");
        answersListField.setAccessible(true);

        ArrayList<String> toBeSet = new ArrayList<>();
        toBeSet.add("test1");
        toBeSet.add("test2");

        question.setAnswersList(toBeSet);

        assertEquals(toBeSet, answersListField.get(question));
        assertEquals(answersListField.get(question), question.getAnswersList());
    }

    @Test
    void getAnswersList() throws Exception {
        final Field answersListField = question.getClass().getDeclaredField("answersList");
        answersListField.setAccessible(true);

        question.addAnswer("test1");
        question.addAnswer("test2");

        List<String> expected = new ArrayList<>();
        expected.add("test1");
        expected.add("test2");

        assertEquals(answersListField.get(question), question.getAnswersList());
        assertEquals(expected, question.getAnswersList());
    }

    @Test
    void getAnswerAtIndexValid_whenIndexWithinBounds_thenReturnAnswerAt() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("test1");
        expected.add("test2");
        expected.add("test3");

        question.setAnswersList(expected);

        assertEquals("test1", question.getAnswerAtIndex(0));
        assertEquals("test2", question.getAnswerAtIndex(1));
        assertEquals("test3", question.getAnswerAtIndex(2));
    }

    @Test
    void getAnswerAtIndex_whenOutOfBound_thenThrowIndexOutOfBoundsException() throws Exception {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("test1");

        question.setAnswersList(expected);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            question.getAnswerAtIndex(1); // out of bounds
        });

        assertTrue(exception instanceof IndexOutOfBoundsException);
    }

    // Suggestion: raise an exception if set an index out of bounds??
    @Test
    void setRightAnswerIndex() throws Exception {
        Field rightAnswerIndexField = question.getClass().getDeclaredField("rightAnswerIndex");
        rightAnswerIndexField.setAccessible(true);

        question.setRightAnswerIndex(2);

        assertEquals(2, rightAnswerIndexField.get(question));
    }

    @Test
    void getRightAnswerIndex() throws Exception {
        Field rightAnswerIndexField = question.getClass().getDeclaredField("rightAnswerIndex");
        rightAnswerIndexField.setAccessible(true);

        question.setRightAnswerIndex(2);

        assertEquals(rightAnswerIndexField.get(question), question.getRightAnswerIndex());
        assertEquals(2, question.getRightAnswerIndex());
    }

    @Test
    void isAnswerCorrect() {
        question.setRightAnswerIndex(2);

        assertTrue(question.getRightAnswerIndex() == 2);
    }

    @Test
    void builder_whenCalled_thenReturnBuilderObject() {
        assertTrue(question.builder() instanceof Question.Builder);
    }
}