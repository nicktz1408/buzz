package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FixedQuestionsRoundBuilderTest {
    FixedQuestionsRoundBuilder fixedQuestionsRoundBuilder;

    @BeforeEach
    void setup() {
        fixedQuestionsRoundBuilder = new FixedQuestionsRoundBuilder();
    }

    @Test
    void type_shouldSetParameterOnObjectToBeBuilt() {
        StopClockRound stopClockRound = new StopClockRound();
        fixedQuestionsRoundBuilder.type(stopClockRound);

        assertEquals(stopClockRound, fixedQuestionsRoundBuilder.build());
    }

    @Test
    void addQuestion_shouldAddQuestion() throws Exception {
        Question questionToBeAdded = Question.builder().setCategory("test").build();

        fixedQuestionsRoundBuilder.type(new StopClockRound());
        fixedQuestionsRoundBuilder.addQuestion(questionToBeAdded);
        StopClockRound round = (StopClockRound)fixedQuestionsRoundBuilder.build();

        Field questionsListField = round.getClass().getSuperclass().getDeclaredField("questionsList");
        questionsListField.setAccessible(true);

        assertEquals(questionToBeAdded, ((List)questionsListField.get(round)).get(0));
    }


    @Test
    void builder_whenRoundTypeSetThenReturnFixedQuestionsRoundInstance() {
        fixedQuestionsRoundBuilder.type(new BettingRound());

        assertTrue(fixedQuestionsRoundBuilder.build() instanceof FixedQuestionsRound);
    }
}