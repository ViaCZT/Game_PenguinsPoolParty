package comp1110.ass1;

import org.junit.jupiter.api.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class GetNeighboursTest {

    private final static PenguinsPoolParty PENGUINS_POOL_PARTY
            = new PenguinsPoolParty(Challenge.CHALLENGES[0]);

    private final static Hex[] EVEN_X_HEXES = new Hex[]{
            PENGUINS_POOL_PARTY.getHex(2, 1),
            PENGUINS_POOL_PARTY.getHex(2, 2)
    };

    private final static Hex[][] EVEN_X_EXPECTED = new Hex[][]{
            new Hex[]{PENGUINS_POOL_PARTY.getHex(2, 0),
                    PENGUINS_POOL_PARTY.getHex(3, 1),
                    PENGUINS_POOL_PARTY.getHex(3, 2),
                    PENGUINS_POOL_PARTY.getHex(2, 2),
                    PENGUINS_POOL_PARTY.getHex(1, 2),
                    PENGUINS_POOL_PARTY.getHex(1, 1)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(2, 1),
                    PENGUINS_POOL_PARTY.getHex(3, 2),
                    PENGUINS_POOL_PARTY.getHex(3, 3),
                    PENGUINS_POOL_PARTY.getHex(2, 3),
                    PENGUINS_POOL_PARTY.getHex(1, 3),
                    PENGUINS_POOL_PARTY.getHex(1, 2)}
    };

    private final static Hex[] ODD_X_HEXES = new Hex[]{
            PENGUINS_POOL_PARTY.getHex(1, 1),
            PENGUINS_POOL_PARTY.getHex(3, 1),
            PENGUINS_POOL_PARTY.getHex(1, 2),
            PENGUINS_POOL_PARTY.getHex(3, 2)
    };

    private final static Hex[][] ODD_X_EXPECTED = new Hex[][]{
            new Hex[]{PENGUINS_POOL_PARTY.getHex(1, 0),
                    PENGUINS_POOL_PARTY.getHex(2, 0),
                    PENGUINS_POOL_PARTY.getHex(2, 1),
                    PENGUINS_POOL_PARTY.getHex(1, 2),
                    PENGUINS_POOL_PARTY.getHex(0, 1),
                    PENGUINS_POOL_PARTY.getHex(0, 0)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(3, 0),
                    PENGUINS_POOL_PARTY.getHex(4, 0),
                    PENGUINS_POOL_PARTY.getHex(4, 1),
                    PENGUINS_POOL_PARTY.getHex(3, 2),
                    PENGUINS_POOL_PARTY.getHex(2, 1),
                    PENGUINS_POOL_PARTY.getHex(2, 0)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(1, 1),
                    PENGUINS_POOL_PARTY.getHex(2, 1),
                    PENGUINS_POOL_PARTY.getHex(2, 2),
                    PENGUINS_POOL_PARTY.getHex(1, 3),
                    PENGUINS_POOL_PARTY.getHex(0, 2),
                    PENGUINS_POOL_PARTY.getHex(0, 1)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(3, 1),
                    PENGUINS_POOL_PARTY.getHex(4, 1),
                    PENGUINS_POOL_PARTY.getHex(4, 2),
                    PENGUINS_POOL_PARTY.getHex(3, 3),
                    PENGUINS_POOL_PARTY.getHex(2, 2),
                    PENGUINS_POOL_PARTY.getHex(2, 1)}
    };

    private final static Hex[] EDGE_HEXES = new Hex[]{
            PENGUINS_POOL_PARTY.getHex(0, 0),
            PENGUINS_POOL_PARTY.getHex(1, 0),
            PENGUINS_POOL_PARTY.getHex(2, 0),
            PENGUINS_POOL_PARTY.getHex(3, 0),
            PENGUINS_POOL_PARTY.getHex(4, 0),
            PENGUINS_POOL_PARTY.getHex(0, 1),
            PENGUINS_POOL_PARTY.getHex(4, 1),
            PENGUINS_POOL_PARTY.getHex(0, 2),
            PENGUINS_POOL_PARTY.getHex(4, 2),
            PENGUINS_POOL_PARTY.getHex(0, 3),
            PENGUINS_POOL_PARTY.getHex(1, 3),
            PENGUINS_POOL_PARTY.getHex(2, 3),
            PENGUINS_POOL_PARTY.getHex(3, 3),
            PENGUINS_POOL_PARTY.getHex(4, 3)
    };

    private final static Hex[][] EDGE_EXPECTED = new Hex[][]{
            new Hex[]{null, PENGUINS_POOL_PARTY.getHex(1, 0),
                    PENGUINS_POOL_PARTY.getHex(1, 1),
                    PENGUINS_POOL_PARTY.getHex(0, 1), null, null},
            new Hex[]{null, null, PENGUINS_POOL_PARTY.getHex(2, 0),
                    PENGUINS_POOL_PARTY.getHex(1, 1),
                    PENGUINS_POOL_PARTY.getHex(0, 0), null},
            new Hex[]{null, PENGUINS_POOL_PARTY.getHex(3, 0),
                    PENGUINS_POOL_PARTY.getHex(3, 1),
                    PENGUINS_POOL_PARTY.getHex(2, 1),
                    PENGUINS_POOL_PARTY.getHex(1, 1),
                    PENGUINS_POOL_PARTY.getHex(1, 0)},
            new Hex[]{null, null, PENGUINS_POOL_PARTY.getHex(4, 0),
                    PENGUINS_POOL_PARTY.getHex(3, 1),
                    PENGUINS_POOL_PARTY.getHex(2, 0), null},
            new Hex[]{null, null, null, PENGUINS_POOL_PARTY.getHex(4, 1),
                    PENGUINS_POOL_PARTY.getHex(3, 1),
                    PENGUINS_POOL_PARTY.getHex(3, 0)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(0, 0),
                    PENGUINS_POOL_PARTY.getHex(1, 1),
                    PENGUINS_POOL_PARTY.getHex(1, 2),
                    PENGUINS_POOL_PARTY.getHex(0, 2), null, null},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(4, 0), null, null,
                    PENGUINS_POOL_PARTY.getHex(4, 2),
                    PENGUINS_POOL_PARTY.getHex(3, 2),
                    PENGUINS_POOL_PARTY.getHex(3, 1)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(0, 1),
                    PENGUINS_POOL_PARTY.getHex(1, 2),
                    PENGUINS_POOL_PARTY.getHex(1, 3),
                    PENGUINS_POOL_PARTY.getHex(0, 3), null, null},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(4, 1), null, null,
                    PENGUINS_POOL_PARTY.getHex(4, 3),
                    PENGUINS_POOL_PARTY.getHex(3, 3),
                    PENGUINS_POOL_PARTY.getHex(3, 2)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(0, 2),
                    PENGUINS_POOL_PARTY.getHex(1, 3), null, null, null, null},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(1, 2),
                    PENGUINS_POOL_PARTY.getHex(2, 2),
                    PENGUINS_POOL_PARTY.getHex(2, 3), null,
                    PENGUINS_POOL_PARTY.getHex(0, 3),
                    PENGUINS_POOL_PARTY.getHex(0, 2)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(2, 2),
                    PENGUINS_POOL_PARTY.getHex(3, 3), null, null, null,
                    PENGUINS_POOL_PARTY.getHex(1, 3)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(3, 2),
                    PENGUINS_POOL_PARTY.getHex(4, 2),
                    PENGUINS_POOL_PARTY.getHex(4, 3), null,
                    PENGUINS_POOL_PARTY.getHex(2, 3),
                    PENGUINS_POOL_PARTY.getHex(2, 2)},
            new Hex[]{PENGUINS_POOL_PARTY.getHex(4, 2), null, null, null, null,
                    PENGUINS_POOL_PARTY.getHex(3, 3)}
    };

    private void test(Hex[] expected, Hex hex) {
        Hex[] out = PENGUINS_POOL_PARTY.getNeighbours(hex);
        assertEquals(6, out.length, "Expected the hexagon to have six " +
                "neighbours, but instead it had " + out.length + "!");
        for (int i = 0; i < 6; i++) {
            String expString = expected[i] == null ? "null" : expected[i].toString();
            String outString = out[i] == null ? "null" : out[i].toString();
            assertSame(expected[i], out[i], "For hex " + hex.toString() +
                    ", expected the " + i + "-th neighbour to point to " + expString +
                    ", but instead found it pointed to " + outString + "!\n" +
                    "Hint: if the two hexes are the same, are you creating a new hex? " +
                    "You should return the hex that is on the board.");
        }
    }

    @Test
    public void testEvenXCoordinate() {
        for (int i = 0; i < EVEN_X_HEXES.length; i++) {
            test(EVEN_X_EXPECTED[i], EVEN_X_HEXES[i]);
        }
    }

    @Test
    public void testOddXCoordinate() {
        for (int i = 0; i < ODD_X_HEXES.length; i++) {
            test(ODD_X_EXPECTED[i], ODD_X_HEXES[i]);
        }
    }

    @Test
    public void testEdgeHexes() {
        for (int i = 0; i < EDGE_HEXES.length; i++) {
            test(EDGE_EXPECTED[i], EDGE_HEXES[i]);
        }
    }
}
