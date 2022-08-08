package comp1110.ass1;

import org.junit.jupiter.api.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class IsSolutionTest {

    private final static PenguinsPoolParty[] STARTER_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("EEPIIEPEPIEEEIEEEPEE",
                    new Ice[]{new Ice('A', new int[]{3, 2, 4, 1, 4, 0, 3, 0}, 1)}),
            new PenguinsPoolParty("IIIIIIIPEPEIPEEEEEPE",
                    new Ice[]{new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2}, 4),
                              new Ice('B', new int[]{4, 0, 3, 0, 2, 0, 1, 1}, 5)}),
            new PenguinsPoolParty("EEIIIEPIPIEIIPPIIIII",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('C', new int[]{0, 3, 1, 3, 1, 2, 2, 1}, 1),
                              new Ice('D', new int[]{4, 3, 3, 3, 2, 2, 2, 3}, 5)}),
            new PenguinsPoolParty("IIPIIIPIPIIIIIPIIIII",
                    new Ice[]{new Ice('A', new int[]{3, 2, 4, 1, 4, 0, 3, 0}, 1),
                              new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
                              new Ice('C', new int[]{0, 3, 1, 3, 1, 2, 2, 1}, 1),
                              new Ice('D', new int[]{4, 3, 3, 3, 2, 2, 2, 3}, 5)}),
            new PenguinsPoolParty("EPEEIEPEIPEEEIIPEEEE",
                    new Ice[]{new Ice('A', new int[]{4, 0, 3, 1, 3, 2, 4, 2}, 4)}),
            new PenguinsPoolParty("EEIPEEIPEEEIIPEPIIII",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 1, 1, 2, 2, 2}, 4),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2)}),
            new PenguinsPoolParty("PIIIIIPEEEIEIIIIIIPP",
                    new Ice[]{new Ice('A', new int[]{4, 2, 3, 2, 2, 2, 2, 3}, 5),
                              new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
                              new Ice('D', new int[]{0, 1, 0, 2, 0, 3, 1, 3}, 3)}),
            new PenguinsPoolParty("IIIIPIIIIIIPPIIIIIIP",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 0, 0, 0, 0, 1}, 5),
                              new Ice('B', new int[]{3, 3, 3, 2, 2, 1, 1, 1}, 0),
                              new Ice('C', new int[]{4, 2, 4, 1, 3, 1, 3, 0}, 0),
                              new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5)}),
            new PenguinsPoolParty("IIEEEPIEPEEIEEEPEPEE",
                    new Ice[]{new Ice('D', new int[]{1, 2, 1, 1, 1, 0, 0, 0}, 0)}),
            new PenguinsPoolParty("PIIIEEIIIEPEIIEEEPEP",
                    new Ice[]{new Ice('C', new int[]{2, 2, 2, 1, 1, 1, 1, 0}, 0),
                              new Ice('D', new int[]{3, 2, 3, 1, 3, 0, 2, 0}, 0)}),
            new PenguinsPoolParty("IIIIIIPEIIIPEIIEPEEI",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
                              new Ice('C', new int[]{4, 3, 4, 2, 3, 2, 3, 1}, 0)}),
            new PenguinsPoolParty("PPIPPIIIIIIIIIIIIIII",
                    new Ice[]{new Ice('A', new int[]{2, 2, 1, 2, 0, 2, 0, 3}, 5),
                              new Ice('B', new int[]{3, 1, 2, 0, 1, 1, 0, 1}, 5),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{2, 1, 3, 2, 4, 1, 4, 2}, 2)}),
            new PenguinsPoolParty("EEPIIEEIIPEEEEPEEEEE",
                    new Ice[]{new Ice('D', new int[]{2, 1, 3, 1, 4, 0, 3, 0}, 1)}),
            new PenguinsPoolParty("EEIIEEIIEEEIIIEPPIPP",
                    new Ice[]{new Ice('A', new int[]{3, 0, 2, 0, 2, 1, 3, 2}, 4),
                              new Ice('C', new int[]{2, 3, 2, 2, 1, 2, 1, 1}, 0)}),
            new PenguinsPoolParty("EPPEEIIIIEIIIEPIIIII",
                    new Ice[]{new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{3, 1, 2, 1, 1, 2, 2, 2}, 4)}),
            new PenguinsPoolParty("IIIIIIIIIIIPIPIIIPIP",
                    new Ice[]{new Ice('A', new int[]{3, 3, 4, 2, 4, 1, 3, 1}, 1),
                              new Ice('B', new int[]{2, 2, 2, 1, 1, 1, 0, 0}, 0),
                              new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
                              new Ice('D', new int[]{0, 1, 0, 2, 0, 3, 1, 3}, 3)})
    };

    private final static PenguinsPoolParty[] JUNIOR_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("IIIPEEEIPEEEEEEEEEEP",
                    new Ice[]{new Ice('A', new int[]{2, 1, 2, 0, 1, 0, 0, 0}, 0)}),
            new PenguinsPoolParty("PEEEEEEEPIEPIIIIIPII",
                    new Ice[]{new Ice('B', new int[]{3, 3, 2, 2, 1, 3, 0, 3}, 5),
                              new Ice('D', new int[]{4, 3, 4, 2, 4, 1, 3, 2}, 0)}),
            new PenguinsPoolParty("IIIIIIIIPIEIIIIPEPEP",
                    new Ice[]{new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2}, 4),
                              new Ice('B', new int[]{4, 0, 3, 0, 2, 0, 1, 1}, 5),
                              new Ice('D', new int[]{2, 1, 3, 2, 4, 2, 4, 1}, 2)}),
            new PenguinsPoolParty("IPIIIIPIIIPIIIIPIIII",
                    new Ice[]{new Ice('A', new int[]{3, 0, 2, 0, 2, 1, 3, 2}, 4),
                              new Ice('B', new int[]{0, 0, 0, 1, 1, 2, 2, 2}, 3),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0)}),
            new PenguinsPoolParty("EEEEEIIEEEIEEPEIPEEP",
                    new Ice[]{new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4)}),
            new PenguinsPoolParty("IIIEEEEIEEEPPPEEIIII",
                    new Ice[]{new Ice('A', new int[]{2, 1, 2, 0, 1, 0, 0, 0}, 0),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2)}),
            new PenguinsPoolParty("EEEPEIIEEEIPEEPIIIII",
                    new Ice[]{new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{2, 2, 3, 2, 4, 1, 3, 1}, 1)}),
            new PenguinsPoolParty("IIPEIIIPIIIIIIIEIIII",
                    new Ice[]{new Ice('A', new int[]{1, 1, 1, 2, 2, 2, 3, 2}, 3),
                              new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0)}),
            new PenguinsPoolParty("EIIIIEPPEEEEEPEEEEEE",
                    new Ice[]{new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2)}),
            new PenguinsPoolParty("EEIIIEEEIIPPEIIEEEEI",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('C', new int[]{4, 3, 4, 2, 3, 2, 3, 1}, 0)}),
            new PenguinsPoolParty("IIIIPIIPIIPIEEIIIEEE",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 0, 0, 0, 0, 1}, 5),
                              new Ice('B', new int[]{0, 3, 1, 3, 1, 2, 1, 1}, 1),
                              new Ice('C', new int[]{4, 2, 4, 1, 3, 1, 3, 0}, 0)}),
            new PenguinsPoolParty("EPIIIIIIPIIPIIIIIIII",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{4, 2, 3, 2, 2, 1, 2, 2}, 5)}),
            new PenguinsPoolParty("EEIIIPEIEPEEEEEEEPEE",
                    new Ice[]{new Ice('A', new int[]{4, 0, 3, 0, 2, 0, 2, 1}, 5)}),
            new PenguinsPoolParty("EEPPIEPEIEEEIIIEEIII",
                    new Ice[]{new Ice('A', new int[]{4, 0, 3, 1, 3, 2, 4, 2}, 4),
                              new Ice('D', new int[]{4, 3, 3, 3, 2, 3, 2, 2}, 5)}),
            new PenguinsPoolParty("IIPIIIIIIEIIIEEEPIEE",
                    new Ice[]{new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
                              new Ice('C', new int[]{2, 3, 2, 2, 1, 2, 1, 1}, 0),
                              new Ice('D', new int[]{2, 1, 3, 1, 4, 0, 3, 0}, 1)}),
            new PenguinsPoolParty("IIIIIIIIPIPIIIPEIIII",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('B', new int[]{1, 0, 1, 1, 2, 1, 3, 2}, 3),
                              new Ice('C', new int[]{1, 3, 1, 2, 0, 1, 0, 0}, 0),
                              new Ice('D', new int[]{4, 3, 3, 3, 2, 2, 2, 3}, 5)})
    };

    private final static PenguinsPoolParty[] EXPERT_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("EEEEEPPIIIEIEEEEEEEE",
                    new Ice[]{new Ice('B', new int[]{4, 1, 3, 1, 2, 1, 1, 2}, 5)}),
            new PenguinsPoolParty("EPEPIIEIEIIPEIEEIIEE",
                    new Ice[]{new Ice('A', new int[]{4, 0, 4, 1, 3, 2, 2, 1}, 3),
                              new Ice('B', new int[]{0, 1, 0, 2, 1, 3, 2, 3}, 3)}),
            new PenguinsPoolParty("EEPIEIEEIIPIIEIIIIII",
                    new Ice[]{new Ice('A', new int[]{0, 1, 1, 2, 1, 3, 0, 3}, 2),
                              new Ice('C', new int[]{4, 2, 4, 1, 3, 1, 3, 0}, 0),
                              new Ice('D', new int[]{4, 3, 3, 3, 2, 2, 2, 3}, 5)}),
            new PenguinsPoolParty("EIIPIPIIIIIIIIPIIIII",
                    new Ice[]{new Ice('A', new int[]{2, 2, 1, 2, 0, 2, 0, 3}, 5),
                              new Ice('B', new int[]{1, 0, 1, 1, 2, 1, 3, 2}, 3),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{2, 0, 3, 1, 4, 0, 4, 1}, 2)}),
            new PenguinsPoolParty("IIEEEIPEPEIEPEEEEEEE",
                    new Ice[]{new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4)}),
            new PenguinsPoolParty("EEEEEIIIIEIPIEEPIIPE",
                    new Ice[]{new Ice('A', new int[]{1, 1, 0, 1, 0, 2, 1, 3}, 4),
                              new Ice('B', new int[]{3, 1, 2, 1, 2, 2, 2, 3}, 4)}),
            new PenguinsPoolParty("IEIEEIIPIEIPIEIPIIII",
                    new Ice[]{new Ice('B', new int[]{0, 1, 0, 2, 1, 3, 2, 3}, 3),
                              new Ice('C', new int[]{0, 0, 1, 1, 2, 0, 3, 1}, 2),
                              new Ice('D', new int[]{2, 2, 3, 3, 4, 3, 4, 2}, 2)}),
            new PenguinsPoolParty("EIPEIPIIIIIIIIIIIIII",
                    new Ice[]{new Ice('A', new int[]{2, 2, 1, 2, 0, 2, 0, 3}, 5),
                              new Ice('B', new int[]{1, 0, 1, 1, 2, 1, 3, 2}, 3),
                              new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0)}),
            new PenguinsPoolParty("IIEPEIPEEEIEEEEEEEPE",
                    new Ice[]{new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4)}),
            new PenguinsPoolParty("IIIIIIEPEIIEEEEEPEEE",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4)}),
            new PenguinsPoolParty("IIIIIIIIEEIPIPEIIPEE",
                    new Ice[]{new Ice('B', new int[]{2, 2, 2, 1, 1, 1, 0, 0}, 0),
                              new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
                              new Ice('D', new int[]{0, 1, 0, 2, 0, 3, 1, 3}, 3)}),
            new PenguinsPoolParty("IIIIIIEIIIIIIPIIIIPE",
                    new Ice[]{new Ice('A', new int[]{4, 2, 4, 1, 3, 1, 2, 1}, 0),
                              new Ice('B', new int[]{0, 0, 0, 1, 1, 2, 2, 2}, 3),
                              new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
                              new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5)}),
            new PenguinsPoolParty("IPEEEIEPEEEIIEEEEEPE",
                    new Ice[]{new Ice('B', new int[]{0, 0, 0, 1, 1, 2, 2, 2}, 3)}),
            new PenguinsPoolParty("EEEPIEPIIIIIPIIEEEEE",
                    new Ice[]{new Ice('B', new int[]{3, 2, 2, 1, 1, 2, 0, 2}, 5),
                              new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0)}),
            new PenguinsPoolParty("IIIPEIEPIPIEEIIIIIEI",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 0, 0, 0, 0, 1}, 5),
                              new Ice('C', new int[]{4, 3, 4, 2, 3, 2, 3, 1}, 0),
                              new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5)}),
            new PenguinsPoolParty("IIIIIIIIPIIIIPIPIIIE",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('B', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
                              new Ice('C', new int[]{2, 2, 2, 1, 1, 1, 1, 0}, 0),
                              new Ice('D', new int[]{0, 0, 0, 1, 0, 2, 1, 2}, 3)})
    };

    private final static PenguinsPoolParty[] MASTER_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("PEEEPEEEEIEIIIEPEEEE",
                    new Ice[]{new Ice('B', new int[]{1, 2, 2, 2, 3, 2, 4, 1}, 2)}),
            new PenguinsPoolParty("EEIIIEIIEEPEIEEPPIIE",
                    new Ice[]{new Ice('B', new int[]{4, 0, 3, 0, 2, 0, 1, 1}, 5),
                              new Ice('D', new int[]{2, 1, 2, 2, 2, 3, 3, 3}, 3)}),
            new PenguinsPoolParty("PIIIIIIPIIIEIIPIEEEE",
                    new Ice[]{new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
                              new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
                              new Ice('D', new int[]{2, 2, 3, 2, 4, 1, 3, 1}, 1)}),
            new PenguinsPoolParty("IIIPIIIIIPIIIIIIIIPE",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 0, 0, 0, 0, 1}, 5),
                              new Ice('B', new int[]{1, 1, 2, 1, 3, 1, 4, 0}, 2),
                              new Ice('C', new int[]{1, 2, 2, 2, 3, 2, 4, 2}, 2),
                              new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5)}),
            new PenguinsPoolParty("IEEEEIPPPEEIIEEEEEEE",
                    new Ice[]{new Ice('B', new int[]{0, 0, 0, 1, 1, 2, 2, 2}, 3)}),
            new PenguinsPoolParty("IPIIPIIEEPEIEEEIIEEE",
                    new Ice[]{new Ice('A', new int[]{0, 3, 1, 3, 1, 2, 0, 1}, 1),
                              new Ice('B', new int[]{0, 0, 1, 1, 2, 0, 3, 0}, 2)}),
            new PenguinsPoolParty("EEIIIEEIIIEIPIIIIPIE",
                    new Ice[]{new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
                              new Ice('C', new int[]{0, 3, 1, 3, 1, 2, 2, 1}, 1),
                              new Ice('D', new int[]{3, 1, 3, 2, 3, 3, 4, 2}, 3)}),
            new PenguinsPoolParty("IIIPIIIIIIPIIIEIIIIP",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 0, 0, 0, 0, 1}, 5),
                              new Ice('B', new int[]{1, 1, 2, 1, 3, 1, 4, 0}, 2),
                              new Ice('C', new int[]{2, 3, 3, 3, 3, 2, 4, 1}, 1),
                              new Ice('D', new int[]{0, 3, 1, 3, 2, 2, 1, 2}, 1)}),
            new PenguinsPoolParty("EEEEEPEEEEEEPEEEIIII",
                    new Ice[]{new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2)}),
            new PenguinsPoolParty("EEEEEIIEIIIEIIPIEEPE",
                    new Ice[]{new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
                              new Ice('D', new int[]{2, 2, 3, 2, 3, 1, 4, 1}, 1)}),
            new PenguinsPoolParty("IIPEEIEIEEIIIPEIIIII",
                    new Ice[]{new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2}, 4),
                              new Ice('B', new int[]{2, 1, 2, 2, 3, 3, 4, 3}, 3),
                              new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5)}),
            new PenguinsPoolParty("IIPIEIIIIIIIPIIIIIIE",
                    new Ice[]{new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2}, 4),
                              new Ice('B', new int[]{3, 3, 3, 2, 2, 1, 1, 1}, 0),
                              new Ice('C', new int[]{4, 2, 4, 1, 3, 1, 3, 0}, 0),
                              new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5)})
    };

    private void test(PenguinsPoolParty[] boards) {
        for (int i = 0; i < boards.length; i++) {
            PenguinsPoolParty ppp = boards[i];
            if (i % 4 == 3) {
                assertTrue(ppp.isSolution(), "Expected board state\n" + ppp.boardToString() +
                        "given by ice placements\n" + getAllIceStrings(ppp) + " to be a solution, but got " +
                        "that it wasn't!");
            }
            else {
                assertFalse(ppp.isSolution(), "Expected board state\n" + ppp.boardToString() +
                        "given by ice placements\n" + getAllIceStrings(ppp) + " to not be a solution, but got " +
                        "that it was!");
            }
        }
    }

    @Test
    public void testStarter() {
        test(STARTER_BOARDS);
    }

    @Test
    public void testJunior() {
        test(JUNIOR_BOARDS);
    }

    @Test
    public void testExpert() {
        test(EXPERT_BOARDS);
    }

    @Test
    public void testMaster() {
        test(MASTER_BOARDS);
    }

    private static String getAllIceStrings(PenguinsPoolParty ppp) {
        String res = "";
        for (Ice ice : ppp.getIceBlocks()) {
            if (ice.isOnBoard()) res += ice + "\n";
        }
        return res;
    }

}
