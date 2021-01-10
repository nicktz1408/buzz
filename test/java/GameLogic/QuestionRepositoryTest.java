package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionRepositoryTest {
    QuestionRepository questionRepository;

    @BeforeEach
    void setup() throws Exception {
        Constructor<QuestionRepository> constructor = QuestionRepository.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        questionRepository = constructor.newInstance();
    }

    @Test
    void constructor_shouldBeProtected() throws Exception {
        Constructor<QuestionRepository> constructor = QuestionRepository.class.getDeclaredConstructor();

        assertTrue(Modifier.isProtected(constructor.getModifiers()));
    }

    @Test
    void getInstance_shouldReturnSingletonObject() {
        QuestionRepository singleton1 = QuestionRepository.getInstance();
        QuestionRepository singleton2 = QuestionRepository.getInstance();

        assertEquals(singleton1, singleton2); // should be the same object
    }

    @Test
    void getSingleRandomQuestion_shouldReturnSingleRandomQuestion() {
        assertTrue(questionRepository.getSingleRandomQuestion() instanceof Question);
    }

    @Test
    void getSingleRandomQuestion_shouldIncrementIndex() throws Exception {
        Field indexField = questionRepository.getClass().getDeclaredField("index");
        indexField.setAccessible(true);

        // Initially -1
        questionRepository.getSingleRandomQuestion(); // now it's 0

        assertEquals(0, indexField.get(questionRepository));
    }

    @Test
    void getRandomQuestionBatch_shouldReturnCorrectNumberOfQuestions() {
        ArrayList<Question> questionsList = questionRepository.getRandomQuestionBatch(10);

        assertEquals(10, questionsList.size());
    }
}