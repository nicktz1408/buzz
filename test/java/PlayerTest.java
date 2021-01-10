import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 Test Data:
 Name: test
 High score: 1000
 Total single game: 3
 Wins 2 Players: 4
 Total 2 Player games: 10
 */


class PlayerTest {
    Player player;

    @BeforeEach
    void setup() throws IOException {
        player = new Player("test");
    }


    @Test
    void getName() {
        assertEquals("test", player.getName());
    }

    @Test
    void getWins() throws Exception {
        assertEquals(4, player.getWins());
    }

    @Test
    void getTotalGamesAlone() throws Exception {
        assertEquals(3, player.getTotalGamesAlone());
    }

    @Test
    void getTotalGAmesFriends() throws Exception {
        assertEquals(10, player.getTotalGAmesFriends());
    }
    
    @Test
    void getHighScore() throws Exception {
        assertEquals(1000, player.getHighScore());
    }

    @Test
    void checkTheNames_whenExists_thenReturnTrue() throws Exception {
        boolean exists = player.checkTheNames("test");

        assertTrue(exists);
    }

    @Test
    void checkTheNames_whenNotExists_thenReturnFalse() throws Exception {
        boolean exists = player.checkTheNames("test2");

        assertTrue(!exists);
    }

    @Test
    void setPlayer() throws Exception {
        player.setName("test2");

        Field nameField = player.getClass().getDeclaredField("name");
        nameField.setAccessible(true);

        assertEquals("test2", nameField.get(player));
    }
}