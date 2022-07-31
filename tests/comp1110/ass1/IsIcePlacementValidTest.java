package comp1110.ass1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class IsIcePlacementValidTest {

    private static final String[] EDGES_STATES = new String[]{
            "IIPEEIPIPEIIIEIIIPII",
            "IIPEEIPIPEIIIEPIIIII",
            "PEEEEIPEEEIEIIIIIIPP",
            "PEIIEIEEIEPIEIEIIPEP",
            "IIPIIIIIIPIIIIPEEEEE",
            "IEEEEIIIIIIPIPIIIPIP",
            "IIIIIIIEPEIIIEEPIPIP",
            "IIIEEIIIEEIPPPEIIIII",
            "IIIIIIPPEEIIIPEIIIEE",
            "EPEEEIIIPEIPIIIIIIII",
            "EEPIIEIIIIEIIIEEPIII",
            "IPIPIEIIIIEPIIIEEEII",
            "EEIIIEPEPIEEPIIEEIIE",
            "EEPEIPEEIIIIIEIIEEEE",
            "IIIIIEIIIIEPIPIEEPIE",
            "IIIPEIPIEEIIPIEEIIII",
            "PIIIPEEEIEEEEEEPIIII",
            "EEEPEEEEEPIIIIIIIIPE",
            "EEIIIEEIIIEIPIIIIPIE",
            "EIIIIEEIIIEIIIPEIIPE"
    };

    private static final Ice[] EDGES_ICE = new Ice[]{
            new Ice('A', new int[]{4, 1, 5, 1, 5, 0, 4, -1}, 1),
            new Ice('A', new int[]{3, 2, 4, 1, 4, 0, 3, 0}, 1),
            new Ice('B', new int[]{5, 1, 4, 0, 3, 1, 2, 1}, 5),
            new Ice('B', new int[]{3, 3, 4, 2, 4, 1, 4, 0}, 1),
            new Ice('C', new int[]{1, 4, 2, 4, 3, 4, 4, 4}, 2),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('D', new int[]{3, 2, 4, 2, 5, 2, 5, 3}, 3),
            new Ice('D', new int[]{4, 2, 4, 1, 3, 1, 4, 0}, 0),
            new Ice('A', new int[]{4, 3, 5, 3, 5, 2, 4, 1}, 2),
            new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
            new Ice('B', new int[]{0, -1, -1, 0, -1, 1, -1, 2}, 4),
            new Ice('B', new int[]{0, 1, 0, 2, 1, 3, 2, 3}, 3),
            new Ice('C', new int[]{1, 2, 0, 2, 0, 3, -1, 4}, 0),
            new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
            new Ice('D', new int[]{-1, 1, -1, 2, -1, 3, 0, 3}, 3),
            new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0),
            new Ice('A', new int[]{1, 2, 0, 1, -1, 2, -1, 3}, 5),
            new Ice('A', new int[]{2, 0, 1, 0, 0, 0, 0, 1}, 5),
            new Ice('B', new int[]{0, 0, -1, 1, -1, 2, -1, 3}, 4),
            new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4)
    };

    private static final String[] PENGUIN_STATES = new String[]{
            "IIEEEIEPEPIIPEEIIIPE",
            "IPEEIIPEIPIIEIIPEEEE",
            "EEEIPEIIIIEPPIIEEEIP",
            "IIEEEIPEIEIPEIIEPEEI",
            "IIEEEIEEEEIEEEEPPEPP",
            "EEEPIEEEPIEEEEIEEEIP",
            "EPIIIEPIIIPEEIIPEEEE",
            "IIIPEEEIIIEPIIPEEEEE",
            "EEIIIEEEEIPPEEEEEEEE",
            "EEIIIPEIEPEEEEEEEPEE",
            "IEIIIIEEPIPIEEPEIEEE",
            "EEPIEIEEIIPIEEIIIEEE",
            "EEEEEEEIIIEPIIIPEIPI",
            "IIIPIIPEIIIEEEEEEEEP",
            "EIIIIEEIIIIEEPIIIIPE",
            "IIIPEIEPIPIEEIIIIIEI",
            "EEIIIEIIEEPEIEEPPIIE",
            "IEEEEIPPPEIIIEEIIIEE",
            "IIIPEIEEEIPEEIEEEIIP",
            "IIPIEIEEIIEIEPIEEEEE"
    };

    private static final Ice[] PENGUIN_ICE = new Ice[]{
            new Ice('C', new int[]{3, 0, 3, 1, 4, 1, 4, 2}, 3),
            new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
            new Ice('D', new int[]{2, 2, 1, 2, 0, 2, 0, 1}, 0),
            new Ice('D', new int[]{2, 1, 2, 2, 2, 3, 3, 3}, 3),
            new Ice('A', new int[]{3, 1, 2, 1, 2, 2, 3, 3}, 4),
            new Ice('A', new int[]{2, 1, 2, 0, 1, 0, 0, 0}, 0),
            new Ice('B', new int[]{1, 1, 1, 2, 2, 2, 3, 3}, 3),
            new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
            new Ice('C', new int[]{1, 1, 1, 2, 2, 2, 2, 3}, 3),
            new Ice('C', new int[]{3, 1, 3, 2, 4, 2, 4, 3}, 3),
            new Ice('D', new int[]{4, 2, 3, 2, 2, 2, 2, 1}, 5),
            new Ice('D', new int[]{4, 3, 3, 3, 2, 3, 2, 2}, 5),
            new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2}, 4),
            new Ice('A', new int[]{4, 2, 3, 2, 2, 2, 2, 3}, 5),
            new Ice('B', new int[]{1, 1, 1, 2, 2, 2, 3, 3}, 3),
            new Ice('B', new int[]{1, 1, 1, 2, 2, 2, 3, 3}, 3),
            new Ice('C', new int[]{0, 0, 0, 1, 1, 2, 1, 3}, 3),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('D', new int[]{0, 2, 1, 2, 2, 1, 1, 1}, 1),
            new Ice('D', new int[]{2, 3, 1, 3, 0, 2, 0, 3}, 5)
    };

    private static final String[] OTHER_ICE_STATES = new String[]{
            "EEEEEEPIPEEIEPPIIEEE",
            "EEEPEEEPEEEEEPEEIIII",
            "IIEEEPIEPEIIIEEPIPIE",
            "PPEPPEEIEIEEEIIEIIII",
            "EPPEEIIIIEIIIEPIEEEE",
            "PEEEEEEEPIEPIIIIIPII",
            "EIIIIIIEEEIEEPEIPEEP",
            "IIPEEIEPEEIEEEEEIIII",
            "EEEIPEIPIIPIEEIIIEEE",
            "IIPPEIPIEEIIEEEIIEEE",
            "EEEEEPPEEEIEIIIIIIEI",
            "EEIPIPEEIIIIIEPIEEEE",
            "EEEIIEEPEIEPIIIPEEII",
            "EEIIIEEPIIEEEIIEPEIE",
            "IPEEEIEPEEEIIEEEEEPE",
            "EEEEEEEEPEEEEPIPIIIE",
            "PEEEEIIPIIIEIIPIEEEE",
            "IPIIPEIIEPEEIEEEEIIE",
            "EEEEEPEEEEEEPEEEIIII",
            "EEPIEEEEIIEEPEIEEEEE"
    };

    private static final Ice[] OTHER_ICE_ICE = new Ice[]{
            new Ice('A', new int[]{2, 3, 2, 2, 1, 2, 0, 2}, 0),
            new Ice('A', new int[]{2, 0, 1, 1, 1, 2, 2, 2}, 4),
            new Ice('B', new int[]{3, 3, 3, 2, 2, 1, 1, 1}, 0),
            new Ice('B', new int[]{3, 1, 2, 0, 1, 1, 0, 1}, 5),
            new Ice('C', new int[]{0, 2, 1, 3, 2, 2, 3, 3}, 2),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('D', new int[]{2, 2, 2, 1, 2, 0, 1, 1}, 0),
            new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0),
            new Ice('A', new int[]{3, 1, 2, 0, 1, 1, 1, 2}, 5),
            new Ice('A', new int[]{4, 0, 3, 1, 3, 2, 4, 2}, 4),
            new Ice('B', new int[]{4, 2, 3, 2, 2, 2, 1, 3}, 5),
            new Ice('B', new int[]{1, 0, 1, 1, 2, 1, 3, 2}, 3),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('C', new int[]{1, 1, 1, 2, 2, 2, 2, 3}, 3),
            new Ice('D', new int[]{2, 2, 1, 2, 0, 1, 0, 2}, 5),
            new Ice('D', new int[]{0, 0, 0, 1, 0, 2, 1, 2}, 3),
            new Ice('A', new int[]{0, 1, 0, 2, 1, 3, 2, 2}, 3),
            new Ice('A', new int[]{0, 3, 1, 3, 1, 2, 0, 1}, 1),
            new Ice('B', new int[]{0, 3, 1, 3, 1, 2, 1, 1}, 1),
            new Ice('B', new int[]{3, 3, 3, 2, 2, 1, 1, 1}, 0)
    };

    private void test(String[] states, Ice[] placements) {
        boolean expected = false;
        for (int i = 0; i < states.length; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(states[i]);
            boolean actual = penguinsPoolParty.isIcePlacementValid(placements[i]);
            String exp = expected ? "valid" : "invalid";
            String notExp = expected ? "invalid" : "valid";
            assertEquals(expected, actual, "For board state\n" + penguinsPoolParty.boardToString() +
                    "Expected placing hexagons at\n" + getIcePositions(placements[i]) + " to be " + exp + ", " +
                    "but got that it was " + notExp + "!");
            expected = !expected;
        }
    }

    private static String getIcePositions(Ice ice) {
        StringBuilder s = new StringBuilder();
        for (Hex hex : ice.getHexes()) {
            s.append(hex.toString()).append("\n");
        }
        return s.toString();
    }

    @Test
    public void testEdges() {
        test(EDGES_STATES, EDGES_ICE);
    }

    @Test
    public void testPenguin() {
        test(PENGUIN_STATES, PENGUIN_ICE);
    }

    @Test
    public void testOtherIce() {
        test(OTHER_ICE_STATES, OTHER_ICE_ICE);
    }

}
