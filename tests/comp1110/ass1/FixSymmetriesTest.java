package comp1110.ass1;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FixSymmetriesTest {

    private static final Ice[] SAMPLE_ICE_ROTATION_THREE = new Ice[]{
            new Ice('C', new int[]{0, 0, 0, 1, 1, 2, 1, 3}, 3),
            new Ice('C', new int[]{1, 3, 1, 2, 0, 1, 0, 0}, 0),
            new Ice('C', new int[]{1, 0, 1, 1, 2, 1, 2, 2}, 3),
            new Ice('C', new int[]{2, 2, 2, 1, 1, 1, 1, 0}, 0),
            new Ice('C', new int[]{2, 0, 2, 1, 3, 2, 3, 3}, 3),
            new Ice('C', new int[]{3, 3, 3, 2, 2, 1, 2, 0}, 0),
            new Ice('C', new int[]{3, 0, 3, 1, 4, 1, 4, 2}, 3),
            new Ice('C', new int[]{4, 2, 4, 1, 3, 1, 3, 0}, 0),
            new Ice('C', new int[]{1, 1, 1, 2, 2, 2, 2, 3}, 3),
            new Ice('C', new int[]{2, 3, 2, 2, 1, 2, 1, 1}, 0),
            new Ice('C', new int[]{3, 1, 3, 2, 4, 2, 4, 3}, 3),
            new Ice('C', new int[]{4, 3, 4, 2, 3, 2, 3, 1}, 0)
    };

    private static final Ice[] SAMPLE_ICE_ROTATION_FOUR = new Ice[]{
            new Ice('C', new int[]{2, 0, 1, 1, 1, 2, 0, 2}, 4),
            new Ice('C', new int[]{0, 2, 1, 2, 1, 1, 2, 0}, 1),
            new Ice('C', new int[]{3, 0, 2, 0, 2, 1, 1, 2}, 4),
            new Ice('C', new int[]{1, 2, 2, 1, 2, 0, 3, 0}, 1),
            new Ice('C', new int[]{4, 0, 3, 1, 3, 2, 2, 2}, 4),
            new Ice('C', new int[]{2, 2, 3, 2, 3, 1, 4, 0}, 1),
            new Ice('C', new int[]{2, 1, 1, 2, 1, 3, 0, 3}, 4),
            new Ice('C', new int[]{0, 3, 1, 3, 1, 2, 2, 1}, 1),
            new Ice('C', new int[]{3, 1, 2, 1, 2, 2, 1, 3}, 4),
            new Ice('C', new int[]{1, 3, 2, 2, 2, 1, 3, 1}, 1),
            new Ice('C', new int[]{4, 1, 3, 2, 3, 3, 2, 3}, 4),
            new Ice('C', new int[]{2, 3, 3, 3, 3, 2, 4, 1}, 1)
    };

    private static final Ice[] SAMPLE_ICE_ROTATION_FIVE = new Ice[]{
            new Ice('C', new int[]{3, 1, 2, 0, 1, 1, 0, 0}, 5),
            new Ice('C', new int[]{0, 0, 1, 1, 2, 0, 3, 1}, 2),
            new Ice('C', new int[]{4, 0, 3, 0, 2, 0, 1, 0}, 5),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('C', new int[]{3, 2, 2, 1, 1, 2, 0, 1}, 5),
            new Ice('C', new int[]{0, 1, 1, 2, 2, 1, 3, 2}, 2),
            new Ice('C', new int[]{4, 1, 3, 1, 2, 1, 1, 1}, 5),
            new Ice('C', new int[]{1, 1, 2, 1, 3, 1, 4, 1}, 2),
            new Ice('C', new int[]{3, 3, 2, 2, 1, 3, 0, 2}, 5),
            new Ice('C', new int[]{0, 2, 1, 3, 2, 2, 3, 3}, 2),
            new Ice('C', new int[]{4, 2, 3, 2, 2, 2, 1, 2}, 5),
            new Ice('C', new int[]{1, 2, 2, 2, 3, 2, 4, 2}, 2),
            new Ice('C', new int[]{4, 3, 3, 3, 2, 3, 1, 3}, 5),
            new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2)
    };

    private void test(Ice[] iceBlocks) {
        for (int i = 0; i < iceBlocks.length; i += 2) {
            String originalIceBlock = iceBlocks[i].toString();
            iceBlocks[i].fixSymmetries();
            Ice exp = iceBlocks[i + 1];
            Ice out = iceBlocks[i];
            assertEquals(exp.getRotation(), out.getRotation(), "For ice block " +
                    originalIceBlock + ", expected its rotation to be " + exp.getRotation() +
                    ", but instead got " + out.getRotation() + "!");
            assertEquals(exp.getOriginX(), out.getOriginX(), "For ice block " +
                    originalIceBlock + ", expected its origin x-coordinate to be " + exp.getOriginX() +
                    ", but instead got " + out.getOriginX() + "!");
            assertEquals(exp.getOriginY(), out.getOriginY(), "For ice block " +
                    originalIceBlock + ", expected its origin y-coordinate to be " + exp.getOriginY() +
                    ", but instead got " + out.getOriginY() + "!");
            assertArrayEquals(exp.getHexes(), out.getHexes(), "For ice block " +
                    originalIceBlock + ", expected hexes " + Arrays.toString(exp.getHexes()) +
                    ", but instead got hexes " + Arrays.toString(out.getHexes()) + "!");
            originalIceBlock = iceBlocks[i + 1].toString();
            iceBlocks[i + 1].fixSymmetries();
            exp = iceBlocks[i];
            out = iceBlocks[i + 1];
            assertEquals(exp.getRotation(), out.getRotation(), "For ice block " +
                    originalIceBlock + ", expected its rotation to be " + exp.getRotation() +
                    ", but instead got " + out.getRotation() + "!");
            assertEquals(exp.getOriginX(), out.getOriginX(), "For ice block " +
                    originalIceBlock + ", expected its origin x-coordinate to be " + exp.getOriginX() +
                    ", but instead got " + out.getOriginX() + "!");
            assertEquals(exp.getOriginY(), out.getOriginY(), "For ice block " +
                    originalIceBlock + ", expected its origin y-coordinate to be " + exp.getOriginY() +
                    ", but instead got " + out.getOriginY() + "!");
            assertArrayEquals(exp.getHexes(), out.getHexes(), "For ice block " +
                    originalIceBlock + ", expected hexes " + Arrays.toString(exp.getHexes()) +
                    ", but instead got hexes " + Arrays.toString(out.getHexes()) + "!");
        }
    }

    @Test
    public void testRotationThree() {
        test(SAMPLE_ICE_ROTATION_THREE);
    }

    @Test
    public void testRotationFour() {
        test(SAMPLE_ICE_ROTATION_FOUR);
    }

    @Test
    public void testRotationFive() {
        test(SAMPLE_ICE_ROTATION_FIVE);
    }

}
