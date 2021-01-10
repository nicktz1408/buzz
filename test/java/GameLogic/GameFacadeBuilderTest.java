package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameFacadeBuilderTest {
    GameFacadeBuilder gameFacadeBuilder;

    @BeforeEach
    void setup() {
        gameFacadeBuilder = new GameFacadeBuilder();
    }

    @Test
    void build_shouldReturnGameFacadeInstance() {
        assertTrue(gameFacadeBuilder.build() instanceof GameFacade);
    }

    @Test
    void addRound_shouldAddRoundToFacade() throws Exception {
        QuickAnswerRound roundToBeAdded = new QuickAnswerRound();

        gameFacadeBuilder.addRound(roundToBeAdded);
        GameFacade game = gameFacadeBuilder.build();

        Field roundListField = game.getClass().getDeclaredField("roundList");
        roundListField.setAccessible(true);

        assertEquals(roundToBeAdded, ((List)roundListField.get(game)).get(0));
    }
}