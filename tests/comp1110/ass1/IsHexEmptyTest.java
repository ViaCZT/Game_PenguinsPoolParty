package comp1110.ass1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class IsHexEmptyTest {

    @Test
    public void testHexEmpty() {
        Hex emptyHex = new Hex(HexType.EMPTY);
        assertTrue(emptyHex.isEmpty(), "Expected a hex of type \"EMPTY\" to" +
                "be empty, but instead got that it was not empty!");
        Hex iceHex = new Hex(HexType.ICE);
        assertFalse(iceHex.isEmpty(), "Expected a hex of type \"ICE\" to" +
                "not be empty, but instead got that it was empty!");
        Hex penguinHex = new Hex(HexType.PENGUIN);
        assertFalse(penguinHex.isEmpty(), "Expected a hex of type \"PENGUIN\" to" +
                "not be empty, but instead got that it was empty!");
    }

}
