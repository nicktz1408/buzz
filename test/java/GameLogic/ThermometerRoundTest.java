package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermometerRoundTest {
    ThermometerRound thermometerRound;
    GamePlayer player1;
    GamePlayer player2;

    @BeforeEach
    void setup() {
        thermometerRound = new ThermometerRound();

        player1 = new GamePlayer();
        player2 = new GamePlayer();
    }

    @Test
    void getRoundName_shouldReturnId5() {
        assertEquals(5, thermometerRound.getRoundName());
    }


}