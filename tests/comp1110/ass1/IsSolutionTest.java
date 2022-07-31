package comp1110.ass1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class IsSolutionTest {

    private final static String[] ONE_BLOCK = new String[]{
            "EEPIIEPEPIEEEIEEEPEE", "EPIIEEPIEPEEPEEPEEEE",
            "EEEEEPEEPEIEIEEPIPIE", "EEPIIEEIIPEEEEPEEEEE",
            "IIIPEEEIPEEEEEEEEEEP", "EEEEEIIEEEIEEPEIPEEP",
            "EIIIIEPPEEEEEPEEEEEE", "IIEEEPIEEPEIEEEEEPEE",
            "EEEEEPPEEEEEIIIEEEEI", "IIEEEIPEPEIEPEEEEEEE",
            "EEEPEEPIEEEIEEEIIEEP", "EPEEEEEPEEIEEEEIIIPE"
    };

    private final static String[] TWO_BLOCKS = new String[]{
            "IIIIIIIPEPEIPEEEEEPE", "EEIPEEIPEEEIIPEPIIII",
            "PEIIEIEEIEPIEIEIIPEP", "IIEEEIIEEEIIIEEPPIPP",
            "PEEEEEEEPIEPIIIIIPII", "EEEEIEEEIIEPPPIEIIII",
            "EEIIIEEEEIPPIEEIIEIE", "EEPPIEPIIEEIEIIIIEEE",
            "EPEPIEEIEIEPIIIEEEII", "EIIIIEEIIEEPIEEPEIPE",
            "EEEEEEIPIEEIIIIEPIIE", "EEEPIEPEIIEEPEIEIIII",
            "PEEEPIIIEIIIIIEPEEEE", "IIIPEIEEEPEIIIIEEEPE",
            "EEIIIEEEIIEEPIIEEPIE", "EIIIIIIEEEIEEEPIEEPE"
    };

    private final static String[] THREE_BLOCKS = new String[]{
            "IIIIIIPIPIIIEPPIIEEE", "PEEEEIPIIIIIIIIIIIPP",
            "EEIIIEPIIIEPIIIEPIII", "EPPEEIIIIEIIIEPIIIII",
            "IIIIIIIEPEIIIEEPIPIP", "IIIPEIIIIIIPIIPIEEEE",
            "IIIIPIEPIIPEIEIEEIII", "IIPIIIIIIEIIIEEEPIEE",
            "EIPIEIIIIIPIEIIIIEEE", "EEEIIIEPEIIPIIIPIIII",
            "EIIIIIEEIIIPEPIIIPIE", "EEEPEEIPIPIIIIIIIIII",
            "IIIIIIIEIEPIEIIPPEEI", "IEEEEIPPPIIIIIEIIIII",
            "IIIPEIEEEIPIIIEIIIIP", "EEPIEEEIIIIEIPIIIIII"
    };

    private final static String[] FOUR_BLOCKS = new String[]{
            "IIPIIIPIPIIIIIPIIIII", "IIIIPIIIIIIPPIIIIIIP",
            "PPIPPIIIIIIIIIIIIIII", "IIIIIIIIIIIPIPIIIPIP",
            "IPIIIIPIIIPIIIIPIIII", "IIPEIIIPIIIIIIIEIIII",
            "EPIIIIIIPIIPIIIIIIII", "IIIIIIIIPIPIIIPEIIII",
            "EIIPIPIIIIIIIIPIIIII", "EIPEIPIIIIIIIIIIIIII",
            "IIIIIIEIIIIIIPIIIIPE", "IIIIIIIIPIIIIPIPIIIE",
            "PIIIIIIPIIIIIIPIIIIE", "IPIIPIIIIPEIIIIIIIII",
            "EIIIIPIIIIIIPIIEIIII", "IIPIEIIIIIIIPIIIIIIE"
    };

    private void test(int from, int to) {
        for (int i = from; i < to; i++) {
            if (i < ONE_BLOCK.length) {
                PenguinsPoolParty penguinsPoolParty1 = new PenguinsPoolParty(ONE_BLOCK[i]);
                assertFalse(penguinsPoolParty1.isSolution(),
                        "For board state\n" + penguinsPoolParty1.boardToString() +
                                "With one ice block placed, expected isSolution() to be false, but it returned true!");
            }
            PenguinsPoolParty penguinsPoolParty2 = new PenguinsPoolParty(TWO_BLOCKS[i]);
            assertFalse(penguinsPoolParty2.isSolution(),
                    "For board state\n" + penguinsPoolParty2.boardToString() +
                            "With two ice blocks placed, expected isSolution() to be false, but it returned true!");
            PenguinsPoolParty penguinsPoolParty3 = new PenguinsPoolParty(THREE_BLOCKS[i]);
            assertFalse(penguinsPoolParty3.isSolution(),
                    "For board state\n" + penguinsPoolParty3.boardToString() +
                            "With three ice blocks placed, expected isSolution() to be false, but it returned true!");
            PenguinsPoolParty penguinsPoolParty4 = new PenguinsPoolParty(FOUR_BLOCKS[i]);
            assertTrue(penguinsPoolParty4.isSolution(),
                    "For board state\n" + penguinsPoolParty4.boardToString() +
                            "With all four ice blocks placed, expected isSolution() to be true, but it returned false!");
        }
    }

    @Test
    public void testStarter() {
        test(0, 4);
    }

    @Test
    public void testJunior() {
        test(4, 8);
    }

    @Test
    public void testExpert() {
        test(8, 12);
    }

    @Test
    public void testMaster() {
        test(12, 16);
    }

}
