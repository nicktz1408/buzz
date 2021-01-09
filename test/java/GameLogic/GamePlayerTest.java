package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class GamePlayerTest {
    GamePlayer player;

    @BeforeEach
    void setup() {
        player = new GamePlayer();
    }

    @Test
    void setScore() throws Exception {
        Field scoreField = player.getClass().getDeclaredField("score");
        scoreField.setAccessible(true);

        player.setScore(200.0);

        assertEquals(200.0, scoreField.get(player));
    }

    @Test
    void getScore() throws Exception {
        Field scoreField = player.getClass().getDeclaredField("score");
        scoreField.setAccessible(true);

        player.setScore(300.0);

        assertEquals(scoreField.get(player), player.getScore());
        assertEquals(300.0, player.getScore());
    }
}