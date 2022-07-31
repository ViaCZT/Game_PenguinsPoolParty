package comp1110.ass1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PlaceRemoveIceBlockTest {

    private static final String[] STATES = new String[]{
            "IIPEEIPEPEIEIEIEEPII",
            "IIEEEIEPEPEIPEEEEEPE",
            "EEIIIEPEPIEEEPPEEEEE",
            "IIPEEIPEPEIEEEPEEEEE",
            "IPEEEIPEEPIIEEEPEEEE",
            "EEIPIEIPIIEIIPIPEEEE",
            "PEEEEEPEEEEEIIIEEIPP",
            "IIIIPIEEIIEPPEIEEEEP",
            "EEEEEPEEPEEEEEEPEPEE",
            "PIIIEEIIIEPEIIEEEPEP",
            "IIEEEIPIEEIPIEEEPIIE",
            "PPIPPIIEIEEEEEEEIIII",
            "EEPIIEEIIPEEEEPEEEEE",
            "EEEEIEIEIIEIIEIPPIPP",
            "EPPEEIIEEEIEEEPIEEEE",
            "IEEEEEIIEEEPIPEEEPEP",
            "EEEPEEEEPEEEEEEEEEEP",
            "PEEEEIIIPEIPEEEEEPEE",
            "EEEEEEEEPEEEEEEPEPEP",
            "IPIIEIPIEEPIIIEPIIII",
            "EIIIIIIIEEIIIPEIPIEP",
            "EEEEIEEEIIEPPPIEEEEE",
            "EEEPEIIEEEIPEEPIEEEE",
            "EEPEEEIPEEEIIIEEIIII",
            "EEEEEEPPEEEEEPEEEEEE",
            "IEIIIIIIIIPPEIIEEEEI",
            "IIIEPIIPEEPIEEEIIEEE",
            "EPEEEIIEPEIPEEEIIIII",
            "EEEEEPEEEPEEEEEEEPEE",
            "EEPPEEPIEEEIIEEIIIII",
            "EEPIIEEIIEEEEEEEPEEE",
            "EEEEEEEEPEPEEEPEEEEE",
            "EIIIIPPIIIIIEEEIIIEE",
            "IPIPEEIEIEEPIEIEEEII",
            "EIPEEIIIEEPIEIEIIEEE",
            "EEEPEPEEEEIIIEPIIIII",
            "EEEEEEPEPEEEPIIEEIIE",
            "EIIIIIIEEEIPEEEPIEPE",
            "EEEEEIEPEEIPEEEPIIEE",
            "EEPEEPEEEEIIIEEIIIII",
            "EEEPEEPIEEEIEEEIIEEP",
            "EEEEEEIPIEEIIIIEPIIE",
            "EEEEEIEEIIIPEPIIIPIE",
            "IEEEEIEIIIEIIPIEEEPE",
            "EPEEEEEPIEEEEIIEEEPI",
            "EEEPEEPEEEEEPEEEIIII",
            "IIIPEIEPEPIEEEEIIIEE",
            "EEIIIEEEPIEEEPEPEEEE",
            "PEEEPEEEEEEEEEEPIIII",
            "EEEEEEEIIEPEIIIPPIII",
            "PEEEEIIPIIIEIIPIEEEE",
            "EEEPEEEEEPEEEEEEEEPE",
            "EEEEEEPPPEEEEEEEEEEE",
            "EPEEPIEEIPEIEIIIIEEI",
            "EEEEEEEEEEEEPEEEEPEE",
            "IIIPEIEEEIPEEIEEEIIP",
            "EEEEIPEEIIEEPEIEIIII",
            "EEEEEEEEIIEEIIPEEEPE",
            "IIPEEIEEEEIIEPEIIIEE",
            "IIPEEIEEEEEIPEEEEEEE"
    };

    private static final String[] EXPECTED_STATES = new String[]{
            "IIPIIIPEPIIEIIIEEPII",
            "IIIIIIIPEPEIPEEEEEPE",
            "EEIIIEPIPIEIEPPIIEEE",
            "IIPEEIPEPEIEIEPEEIII",
            "IPEEIIPEIPIIEIIPEEEE",
            "IIIPIIIPIIIIIPIPEEEE",
            "PIIIIEPEEEEEIIIEEIPP",
            "IIIIPIEEIIIPPEIIIIEP",
            "EEIIIPEEPIEEEEEPEPEE",
            "PIIIIEIIIIPEIIIEEPIP",
            "IIEEEIPIIEIPIIIEPIII",
            "PPIPPIIIIIEEEIIEIIII",
            "EEPIIEIIIPEIIIPEEEEE",
            "IIEEIIIEIIIIIEIPPIPP",
            "EPPEEIIEEEIEEEPIIIII",
            "IEEEEIIIEEIPIPEIIPEP",
            "IIIPEEEIPEEEEEEEEEEP",
            "PEEEEIIIPEIPIEEIIPIE",
            "EEEEEEEEPEIEIEEPIPIP",
            "IPIIIIPIIIPIIIIPIIII",
            "EIIIIIIIIIIIIPIIPIIP",
            "EEEEIIIEIIIPPPIIEEEE",
            "EEEPEIIEEEIPEEPIIIII",
            "EEPEIEIPIIEIIIIEIIII",
            "EEEEEEPPIIEEEPIEEEIE",
            "IEIIIIIIIIPPIIIIIEII",
            "IIIIPIIPIIPIEEIIIEEE",
            "EPEEEIIIPEIPIIIIIIII",
            "EEIIIPEIEPEEEEEEEPEE",
            "IIPPEIPIEEIIIEEIIIII",
            "EEPIIEIIIEEIIEEEPIEE",
            "EEEEEEEEPEPEIEPEEIII",
            "EIIIIPPIIIIIIIIIIIEI",
            "IPIPEIIEIEIPIEIEIIII",
            "EIPIEIIIIIPIEIIIIEEE",
            "EEIPIPEEIIIIIEPIIIII",
            "EEIIIEPEPIEEPIIEEIIE",
            "EIIIIIIIIEIPIEEPIIPE",
            "IEIEEIIPIEIPEEEPIIEE",
            "EEPEIPEEIIIIIEIIIIII",
            "EEEPEEPIEEEIIIIIIIEP",
            "IIEEEIIPIEIIIIIEPIIE",
            "EIIIIIEEIIIPEPIIIPIE",
            "IEEEEIEIIIIIIPIIIIPE",
            "EPIIIEEPIIEEEIIEEEPI",
            "EEEPEEPIEEIIPIEEIIII",
            "IIIPEIEPIPIEEIIIIIEI",
            "IEIIIIEEPIIIEPEPEEEE",
            "PEEEPIIIEEIEEEEPIIII",
            "EEIIIEIIIEPEIIIPPIII",
            "PIIIIIIPIIIEIIPIEEEE",
            "EEEPEEEEEPIEEEEIIIPE",
            "EEEEEEPPPIEEEIEEEEII",
            "IPIIPIIEIPEIEIIIIEEI",
            "EEEEEEEIEEEIPEEIIPEE",
            "IIIPEIEEEIPIIIEIIIIP",
            "EEIIIPEIIIEEPIIEIIII",
            "EEEEEIIEIIIEIIPIEEPE",
            "IIPIEIEEIIIIEPIIIIEE",
            "IIPEEIEEEEIIPEEIIIEE",
    };

    private static final Ice[] ICE_BLOCKS = new Ice[]{
            new Ice('A', new int[]{3, 2, 4, 1, 4, 0, 3, 0}, 1),
            new Ice('B', new int[]{4, 0, 3, 0, 2, 0, 1, 1}, 5),
            new Ice('C', new int[]{2, 1, 1, 2, 1, 3, 0, 3}, 4),
            new Ice('D', new int[]{4, 3, 3, 3, 2, 3, 2, 2}, 5),
            new Ice('A', new int[]{4, 0, 3, 1, 3, 2, 4, 2}, 4),
            new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('D', new int[]{2, 3, 1, 3, 0, 3, 0, 2}, 5),
            new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
            new Ice('B', new int[]{3, 3, 4, 2, 4, 1, 4, 0}, 1),
            new Ice('C', new int[]{3, 1, 3, 2, 4, 2, 4, 3}, 3),
            new Ice('D', new int[]{2, 1, 3, 2, 4, 2, 4, 1}, 2),
            new Ice('A', new int[]{1, 1, 1, 2, 2, 2, 3, 2}, 3),
            new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
            new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
            new Ice('D', new int[]{0, 1, 0, 2, 0, 3, 1, 3}, 3),
            new Ice('A', new int[]{2, 1, 2, 0, 1, 0, 0, 0}, 0),
            new Ice('B', new int[]{3, 3, 2, 2, 1, 3, 0, 3}, 5),
            new Ice('C', new int[]{0, 2, 1, 3, 2, 2, 3, 3}, 2),
            new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0),
            new Ice('A', new int[]{3, 3, 4, 2, 4, 1, 3, 1}, 1),
            new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
            new Ice('C', new int[]{1, 3, 2, 3, 3, 3, 4, 3}, 2),
            new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1}, 0),
            new Ice('A', new int[]{3, 3, 4, 2, 4, 1, 3, 1}, 1),
            new Ice('B', new int[]{3, 3, 2, 2, 1, 3, 0, 3}, 5),
            new Ice('C', new int[]{3, 0, 3, 1, 4, 1, 4, 2}, 3),
            new Ice('D', new int[]{4, 2, 3, 2, 2, 1, 2, 2}, 5),
            new Ice('A', new int[]{4, 0, 3, 0, 2, 0, 2, 1}, 5),
            new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
            new Ice('C', new int[]{1, 1, 1, 2, 2, 2, 2, 3}, 3),
            new Ice('D', new int[]{4, 3, 3, 3, 2, 2, 2, 3}, 5),
            new Ice('A', new int[]{4, 3, 4, 2, 3, 2, 2, 2}, 0),
            new Ice('B', new int[]{0, 1, 0, 2, 1, 3, 2, 3}, 3),
            new Ice('C', new int[]{3, 0, 3, 1, 4, 1, 4, 2}, 3),
            new Ice('D', new int[]{2, 0, 3, 1, 4, 1, 4, 0}, 2),
            new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
            new Ice('B', new int[]{3, 1, 2, 1, 2, 2, 2, 3}, 4),
            new Ice('C', new int[]{0, 0, 1, 1, 2, 0, 3, 1}, 2),
            new Ice('D', new int[]{4, 2, 4, 1, 3, 1, 4, 0}, 0),
            new Ice('A', new int[]{4, 2, 3, 2, 2, 2, 2, 3}, 5),
            new Ice('B', new int[]{1, 0, 0, 0, 0, 1, 0, 2}, 4),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('D', new int[]{2, 3, 1, 3, 0, 2, 0, 3}, 5),
            new Ice('A', new int[]{4, 1, 4, 0, 3, 0, 2, 0}, 0),
            new Ice('B', new int[]{3, 2, 2, 1, 1, 2, 0, 2}, 5),
            new Ice('C', new int[]{3, 1, 3, 2, 4, 2, 4, 3}, 3),
            new Ice('D', new int[]{0, 0, 0, 1, 0, 2, 1, 2}, 3),
            new Ice('A', new int[]{2, 1, 1, 1, 0, 1, 0, 2}, 5),
            new Ice('B', new int[]{4, 0, 3, 0, 2, 0, 1, 1}, 5),
            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0}, 2),
            new Ice('D', new int[]{2, 3, 1, 3, 0, 2, 0, 3}, 5),
            new Ice('A', new int[]{4, 1, 3, 2, 3, 3, 4, 3}, 4),
            new Ice('B', new int[]{0, 0, 1, 1, 2, 0, 3, 0}, 2),
            new Ice('C', new int[]{2, 1, 1, 2, 1, 3, 0, 3}, 4),
            new Ice('D', new int[]{0, 3, 1, 3, 2, 2, 1, 2}, 1),
            new Ice('A', new int[]{3, 0, 2, 0, 2, 1, 3, 2}, 4),
            new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3}, 4),
            new Ice('C', new int[]{3, 0, 3, 1, 4, 1, 4, 2}, 3),
            new Ice('D', new int[]{2, 3, 1, 3, 0, 2, 0, 3}, 5)
    };

    @Test
    public void testPlacement() {
        for (int i = 0; i < ICE_BLOCKS.length; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(STATES[i]);
            PenguinsPoolParty expected = new PenguinsPoolParty(EXPECTED_STATES[i]);
            String boardString = penguinsPoolParty.boardToString();
            penguinsPoolParty.placeIceBlock(ICE_BLOCKS[i]);
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 5; x++) {
                    Hex actual = penguinsPoolParty.getHex(x, y);
                    Hex exp = expected.getHex(x, y);
                    assertEquals(exp.getType(), actual.getType(), "Given board state\n" + boardString + "\n" +
                            "And ice placement\n" + getIcePositions(ICE_BLOCKS[i]) + "\n" +
                            "Expected resulting board state\n" + expected.boardToString() + ",\n" +
                            "but instead got\n" + penguinsPoolParty.boardToString() + "!");
                }
            }
        }
    }

    @Test
    public void testPlaceIceBlockNotChanged() {
        testPlacement();
        for (int i = 0; i < ICE_BLOCKS.length; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(STATES[i]);
            Ice iceCopy = new Ice(ICE_BLOCKS[i].getId(), getPositions(ICE_BLOCKS[i]), ICE_BLOCKS[i].getRotation());
            penguinsPoolParty.placeIceBlock(ICE_BLOCKS[i]);
            assertTrue(ICE_BLOCKS[i].isOnBoard(), "Expected the ice block to be placed on the board after" +
                    "placement, but found that it was not!");
            assertEquals(iceCopy.getId(), ICE_BLOCKS[i].getId(), "Expected " +
                    "placing an ice block to not mutate its ID, but it did!");
            assertEquals(iceCopy.getOriginX(), ICE_BLOCKS[i].getOriginX(), "Expected " +
                    "placing an ice block to not mutate its origin x-coordinate, but it did!");
            assertEquals(iceCopy.getOriginY(), ICE_BLOCKS[i].getOriginY(), "Expected " +
                    "placing an ice block to not mutate its origin y-coordinate, but it did!");
            assertEquals(iceCopy.getRotation(), ICE_BLOCKS[i].getRotation(), "Expected " +
                    "placing an ice block to not mutate its rotation, but it did!");
            assertEquals(iceCopy.getHexes().length, ICE_BLOCKS[i].getHexes().length, "Expected " +
                    "placing an ice block to not mutate the number of hexes in the ice block, but it did!");
            for (int j = 0; j < iceCopy.getHexes().length; j++) {
                assertEquals(iceCopy.getHexes()[j].getX(), ICE_BLOCKS[i].getHexes()[j].getX(), "X-coordinates " +
                        "of equivalent hexes do not match, meaning a hexagon has been mutated during placement!");
                assertEquals(iceCopy.getHexes()[j].getY(), ICE_BLOCKS[i].getHexes()[j].getY(), "Y-coordinates " +
                        "of equivalent hexes do not match, meaning a hexagon has been mutated during placement!");
                assertEquals(iceCopy.getHexes()[j].getType(), ICE_BLOCKS[i].getHexes()[j].getType(), "Types " +
                        "of equivalent hexes do not match, meaning a hexagon has been mutated during placement!");
            }
        }
    }

    @Test
    public void testRemoveIceBlock() {
        testPlacement();
        for (int i = 0; i < ICE_BLOCKS.length; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(STATES[i]);
            PenguinsPoolParty expected = new PenguinsPoolParty(STATES[i]);
            penguinsPoolParty.placeIceBlock(ICE_BLOCKS[i]);
            penguinsPoolParty.removeIceBlock(ICE_BLOCKS[i]);
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 5; x++) {
                    Hex exp = expected.getHex(x, y);
                    Hex actual = penguinsPoolParty.getHex(x, y);
                    assertEquals(exp.getType(), actual.getType(), "For board state\n" +
                            expected.boardToString() + ",\nexpected placing and removing an ice block to not" +
                            "modify the board, but instead got board state\n" +
                            penguinsPoolParty.boardToString());
                }
            }
        }
    }

    @Test
    public void testPlaceAndRemoveIceNotChanged() {
        testPlacement();
        for (int i = 0; i < ICE_BLOCKS.length; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(STATES[i]);
            Ice iceCopy = new Ice(ICE_BLOCKS[i].getId(), getPositions(ICE_BLOCKS[i]), ICE_BLOCKS[i].getRotation());
            penguinsPoolParty.placeIceBlock(ICE_BLOCKS[i]);
            penguinsPoolParty.removeIceBlock(ICE_BLOCKS[i]);
            assertFalse(ICE_BLOCKS[i].isOnBoard(), "Expected the ice block to not be placed on the board after" +
                    "placement and removal, but found that it was on the board!");
            assertEquals(iceCopy.getId(), ICE_BLOCKS[i].getId(), "Expected " +
                    "placing and removing an ice block to not mutate its ID, but it did!");
            assertEquals(iceCopy.getOriginX(), ICE_BLOCKS[i].getOriginX(), "Expected " +
                    "placing and removing an ice block to not mutate its origin x-coordinate, but it did!");
            assertEquals(iceCopy.getOriginY(), ICE_BLOCKS[i].getOriginY(), "Expected " +
                    "placing and removing an ice block to not mutate its origin y-coordinate, but it did!");
            assertEquals(iceCopy.getRotation(), ICE_BLOCKS[i].getRotation(), "Expected " +
                    "placing and removing an ice block to not mutate its rotation, but it did!");
            assertEquals(iceCopy.getHexes().length, ICE_BLOCKS[i].getHexes().length, "Expected " +
                    "placing and removing an ice block to not mutate the number of hexes in the ice block, but it did!");
            for (int j = 0; j < iceCopy.getHexes().length; j++) {
                assertEquals(iceCopy.getHexes()[j].getX(), ICE_BLOCKS[i].getHexes()[j].getX(), "X-coordinates " +
                        "of equivalent hexes do not match, meaning a hexagon has been mutated during placement!");
                assertEquals(iceCopy.getHexes()[j].getY(), ICE_BLOCKS[i].getHexes()[j].getY(), "Y-coordinates " +
                        "of equivalent hexes do not match, meaning a hexagon has been mutated during placement!");
                assertEquals(iceCopy.getHexes()[j].getType(), ICE_BLOCKS[i].getHexes()[j].getType(), "Types " +
                        "of equivalent hexes do not match, meaning a hexagon has been mutated during placement!");
            }
        }
    }

    private static String getIcePositions(Ice ice) {
        StringBuilder s = new StringBuilder();
        for (Hex hex : ice.getHexes()) {
            s.append(hex.toString()).append("\n");
        }
        return s.toString();
    }

    private static int[] getPositions(Ice ice) {
        int[] res = new int[8];
        for (int i = 0; i < 8; i += 2) {
            res[i] = ice.getHexes()[i / 2].getX();
            res[i + 1] = ice.getHexes()[i / 2].getY();
        }
        return res;
    }

}
