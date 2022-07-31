package comp1110.ass1;

import org.junit.jupiter.api.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class IsXEvenTest {

    @Test
    public void testIsXEven() {
        boolean expected = true;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                Hex hex = new Hex(x, y);
                if (expected) {
                    assertTrue(hex.isXEven(), "Expected hex at (" + x + ", " +
                            y + ") to have even x-coordinate, but isXEven()" +
                            "was false!");
                }
                else {
                    assertFalse(hex.isXEven(), "Expected hex at (" + x + ", " +
                            y + ") to have odd x-coordinate, but isXEven()" +
                            "was true!");
                }
            }
            expected = !expected;
        }
    }

}
