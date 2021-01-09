package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class QuestionWithImageTest {
    QuestionWithImage questionWithImage;

    @BeforeEach
    void setup() {
        questionWithImage = new QuestionWithImage();
    }

    @Test
    void setImagePath() throws Exception {
        final Field imagePathField = questionWithImage.getClass().getDeclaredField("imagePath");
        imagePathField.setAccessible(true);

        questionWithImage.setImagePath("test");

        assertEquals("test", imagePathField.get(questionWithImage));
    }

    @Test
    void getImagePath() throws Exception {
        final Field imagePathField = questionWithImage.getClass().getDeclaredField("imagePath");
        imagePathField.setAccessible(true);

        questionWithImage.setImagePath("test");

        assertEquals(imagePathField.get(questionWithImage), questionWithImage.getImagePath());
        assertEquals("test", questionWithImage.getImagePath());
    }

    @Test
    void builder_whenCalled_thenReturnBuilderObject() {
        assertTrue(questionWithImage.builder() instanceof QuestionWithImage.Builder);
    }
}