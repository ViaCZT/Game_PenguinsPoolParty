package comp1110.ass1;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class GetAllValidPlacementsTest {

    private final static PenguinsPoolParty[] STARTER_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("IEPEEIPEPEEIIEEEEPEE",
                    new Ice[]{new Ice('B', new int[]{0, 0, 0, 1, 1, 2, 2, 2, }, 3),
                    }),
            new PenguinsPoolParty("EIEEEEIPEPIIPEEEEEPE",
                    new Ice[]{new Ice('B', new int[]{0, 2, 1, 2, 1, 1, 1, 0, }, 1),
                    }),
            new PenguinsPoolParty("EEEEEIPEPEEIIPPEEIEE",
                    new Ice[]{new Ice('B', new int[]{2, 3, 2, 2, 1, 2, 0, 1, }, 0),
                    }),
            new PenguinsPoolParty("EEPEEEPEPEIEIEPEIEIE",
                    new Ice[]{new Ice('C', new int[]{0, 2, 1, 3, 2, 2, 3, 3, }, 2),
                    }),
            new PenguinsPoolParty("IPEEEIPIEPEIIEIPIEIE",
                    new Ice[]{new Ice('A', new int[]{2, 1, 2, 2, 3, 3, 4, 2, }, 3),
                            new Ice('C', new int[]{1, 3, 1, 2, 0, 1, 0, 0, }, 0),
                    }),
            new PenguinsPoolParty("IIIPEIIPIEEIEPEPIEEE",
                    new Ice[]{new Ice('C', new int[]{0, 0, 0, 1, 1, 2, 1, 3, }, 3),
                            new Ice('D', new int[]{3, 1, 2, 0, 1, 0, 1, 1, }, 5),
                    }),
            new PenguinsPoolParty("PIIIIEPEEEEIIEEIIEPP",
                    new Ice[]{new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0, }, 2),
                            new Ice('D', new int[]{0, 3, 1, 3, 2, 2, 1, 2, }, 1),
                    }),
            new PenguinsPoolParty("EEEEPEEIEIEPPIIEEEEP",
                    new Ice[]{new Ice('D', new int[]{2, 1, 3, 2, 4, 2, 4, 1, }, 2),
                    }),
            new PenguinsPoolParty("EEIEEPIEPEEIIEEPEPEE",
                    new Ice[]{new Ice('A', new int[]{2, 0, 1, 1, 1, 2, 2, 2, }, 4),
                    }),
            new PenguinsPoolParty("PEIEEEIEIEPIIEEIIPIP",
                    new Ice[]{new Ice('A', new int[]{3, 1, 2, 0, 1, 1, 1, 2, }, 5),
                            new Ice('B', new int[]{3, 3, 2, 2, 1, 3, 0, 3, }, 5),
                    }),
            new PenguinsPoolParty("EEEEEEPEIEEPEIIEPEEI",
                    new Ice[]{new Ice('C', new int[]{3, 1, 3, 2, 4, 2, 4, 3, }, 3),
                    }),
            new PenguinsPoolParty("PPEPPEEIEEEEEIEEEIIE",
                    new Ice[]{new Ice('A', new int[]{2, 3, 3, 3, 3, 2, 2, 1, }, 1),
                    }),
            new PenguinsPoolParty("EEPEEIEEEPEIIEPEEIEE",
                    new Ice[]{new Ice('B', new int[]{2, 3, 2, 2, 1, 2, 0, 1, }, 0),
                    }),
            new PenguinsPoolParty("EEIEEEIEEEIIEEEPPEPP",
                    new Ice[]{new Ice('C', new int[]{2, 0, 1, 1, 1, 2, 0, 2, }, 4),
                    }),
            new PenguinsPoolParty("EPPEEIEEEEIEEEPEIIEE",
                    new Ice[]{new Ice('B', new int[]{0, 1, 0, 2, 1, 3, 2, 3, }, 3),
                    }),
            new PenguinsPoolParty("EIIEEIIEEEEPEPEEEPEP",
                    new Ice[]{new Ice('D', new int[]{0, 1, 1, 1, 2, 0, 1, 0, }, 1),
                    }),
    };
    private final static String[][] STARTER_EXPECTED_PLACEMENTS = new String[][]{
            new String[]{"A212", "A321", "A414", "D212", "D430", },
            new String[]{"A404", "C430", "D320", },
            new String[]{"A210", "A405", "A410", "C102", },
            new String[]{"A003", "A104", "A212", "A321", },
            new String[]{"D320", },
            new String[]{"A222", "B331", },
            new String[]{"A420", },
            new String[]{"A114", "A205", "B002", "B013", "B104", "B114", "B315", "C002", },
            new String[]{"B331", "B430", "D212", "D430", },
            new String[]{"D212", },
            new String[]{"A205", "A210", "A405", "A410", "B104", "B304", "D213", },
            new String[]{"B031", "B114", "B204", "B315", "B420", "C021", "D013", "D031", "D113", "D130", "D204", "D225", },
            new String[]{"A103", "A112", "D112", "D211", "D330", "D404", },
            new String[]{"A212", "A321", "A404", "A415", "A420", "A425", "B221", "B314", "D211", "D212", "D221", "D303", "D404", "D420", "D425", },
            new String[]{"A112", "A113", "A122", "A212", "A314", "A321", "A330", "A414", "A415", "C112", "C221", "D112", "D122", "D211", "D221", "D303", "D314", "D325", "D330", "D404", "D414", },
            new String[]{"A022", "A213", "A222", "A314", "A331", "A415", "A420", "B331", "B335", "C022", "C131", "C420", },
    };

    private final static String[][] STARTER_EXPECTED_PLACEMENTS_EXTENDED = new String[][]{
            new String[]{"A212", "A321", "A414", "D212", "D430", },
            new String[]{"A404", "C430", "C313", "D320", },
            new String[]{"A210", "A405", "A410", "C102", "C405"},
            new String[]{"A003", "A104", "A212", "A321", },
            new String[]{"D320", },
            new String[]{"A222", "B331", },
            new String[]{"A420", },
            new String[]{"A114", "A205", "B002", "B013", "B104", "B114", "B315", "C002", "C315"},
            new String[]{"B331", "B430", "D212", "D430", },
            new String[]{"D212", },
            new String[]{"A205", "A210", "A405", "A410", "B104", "B304", "D213", },
            new String[]{"B031", "B114", "B204", "B315", "B420", "C021", "C204", "D013", "D031", "D113", "D130", "D204", "D225", },
            new String[]{"A103", "A112", "D112", "D211", "D330", "D404", },
            new String[]{"A212", "A321", "A404", "A415", "A420", "A425", "B221", "B314", "D211", "D212", "D221", "D303", "D404", "D420", "D425", },
            new String[]{"A112", "A113", "A122", "A212", "A314", "A321", "A330", "A414", "A415", "C112", "C415", "C221", "C404", "D112", "D122", "D211", "D221", "D303", "D314", "D325", "D330", "D404", "D414", },
            new String[]{"A022", "A213", "A222", "A314", "A331", "A415", "A420", "B331", "B335", "C022", "C335", "C131", "C314", "C420", "C303" },
    };

    private final static PenguinsPoolParty[] JUNIOR_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("IEIPEIIIPEEIIEEEIEEP",
                    new Ice[]{new Ice('C', new int[]{1, 3, 1, 2, 0, 1, 0, 0, }, 0),
                            new Ice('D', new int[]{2, 2, 2, 1, 2, 0, 1, 1, }, 0),
                    }),
            new PenguinsPoolParty("PIEEEEIIPEIPIIEEIPIE",
                    new Ice[]{new Ice('B', new int[]{1, 0, 1, 1, 2, 1, 3, 2, }, 3),
                            new Ice('C', new int[]{0, 2, 1, 3, 2, 2, 3, 3, }, 2),
                    }),
            new PenguinsPoolParty("EIIIIEEIPEIIEEEPIPEP",
                    new Ice[]{new Ice('C', new int[]{4, 0, 3, 0, 2, 0, 1, 0, }, 5),
                            new Ice('D', new int[]{2, 1, 1, 2, 0, 2, 1, 3, }, 4),
                    }),
            new PenguinsPoolParty("EPEEIEPIIIPEIEIPEEII",
                    new Ice[]{new Ice('B', new int[]{2, 1, 2, 2, 3, 3, 4, 3, }, 3),
                            new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1, }, 0),
                    }),
            new PenguinsPoolParty("IIEEEIEEEEEIEPEEPEEP",
                    new Ice[]{new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2, }, 4),
                    }),
            new PenguinsPoolParty("EEIIEEIIEEEPPPEEEEEE",
                    new Ice[]{new Ice('D', new int[]{3, 0, 2, 0, 1, 1, 2, 1, }, 4),
                    }),
            new PenguinsPoolParty("EEEPIEEIIEIPIIPEIEIE",
                    new Ice[]{new Ice('C', new int[]{0, 2, 1, 3, 2, 2, 3, 3, }, 2),
                            new Ice('D', new int[]{4, 0, 3, 1, 2, 1, 3, 2, }, 4),
                    }),
            new PenguinsPoolParty("EEPEIIEPIEIEEIEIIEIE",
                    new Ice[]{new Ice('B', new int[]{4, 0, 3, 1, 3, 2, 3, 3, }, 4),
                            new Ice('D', new int[]{0, 1, 0, 2, 0, 3, 1, 3, }, 3),
                    }),
            new PenguinsPoolParty("EEEEIEPPEIIIIPIIEEIE",
                    new Ice[]{new Ice('A', new int[]{2, 2, 1, 2, 0, 2, 0, 3, }, 5),
                            new Ice('B', new int[]{3, 3, 4, 2, 4, 1, 4, 0, }, 1),
                    }),
            new PenguinsPoolParty("EEIIIEEIIIPPEIIEEEEE",
                    new Ice[]{new Ice('A', new int[]{3, 0, 2, 0, 2, 1, 3, 2, }, 4),
                            new Ice('D', new int[]{4, 2, 4, 1, 4, 0, 3, 1, }, 0),
                    }),
            new PenguinsPoolParty("EEEEPEEPEIPEIIEEEEIE",
                    new Ice[]{new Ice('D', new int[]{4, 1, 3, 2, 2, 2, 3, 3, }, 4),
                    }),
            new PenguinsPoolParty("EPEEEEEEPIEPIIEEEEIE",
                    new Ice[]{new Ice('D', new int[]{4, 1, 3, 2, 2, 2, 3, 3, }, 4),
                    }),
            new PenguinsPoolParty("EEEEEPEEEPEIIEEIIPEE",
                    new Ice[]{new Ice('D', new int[]{0, 3, 1, 3, 2, 2, 1, 2, }, 1),
                    }),
            new PenguinsPoolParty("EEPPEEPEIEEEIIIIIEII",
                    new Ice[]{new Ice('B', new int[]{3, 3, 2, 2, 1, 3, 0, 3, }, 5),
                            new Ice('C', new int[]{4, 3, 4, 2, 3, 2, 3, 1, }, 0),
                    }),
            new PenguinsPoolParty("EEPEEEEIEEEEEIIEPEEI",
                    new Ice[]{new Ice('B', new int[]{4, 3, 4, 2, 3, 2, 2, 1, }, 0),
                    }),
            new PenguinsPoolParty("EEIIEEEIPEPIEEPEEEEE",
                    new Ice[]{new Ice('C', new int[]{1, 2, 2, 1, 2, 0, 3, 0, }, 1),
                    }),
    };

    private final static String[][] JUNIOR_EXPECTED_PLACEMENTS = new String[][]{
            new String[]{"B331", },
            new String[]{"A410", },
            new String[]{"A222", },
            new String[]{"C130", },
            new String[]{"B112", "B304", "B314", "B331", "B405", "B420", "C112", "C420", "D202", "D211", "D213", "D220", "D304", "D415", "D420", },
            new String[]{"A331", "B013", "B104", "B132", "B331", "C132", },
            new String[]{"A205", },
            new String[]{"C230", },
            new String[]{"D102", },
            new String[]{"B335", "C132", },
            new String[]{"A031", "A104", "A205", "A315", "B002", "B031", "B204", "B315", "C002", "C130", },
            new String[]{"A114", "A215", "A405", "B002", "B013", "B114", "B405", },
            new String[]{"A103", "A112", "A210", "A304", "A320", "A404", "A405", "B002", "B103", "B112", "B203", "B320", "B330", "B404", "B405", "B430", "C002", "C102", "C330", "C430", },
            new String[]{"A003", "A104", "D003", },
            new String[]{"A021", "A104", "A225", "A230", "C230", "D003", "D120", "D225", },
            new String[]{"A132", "A414", "B335", "D324", "D414", "D435", },
    };

    private final static String[][] JUNIOR_EXPECTED_PLACEMENTS_EXTENDED = new String[][]{
            new String[]{"B331", },
            new String[]{"A410", },
            new String[]{"A222", },
            new String[]{"C130", "C003"},
            new String[]{"B112", "B304", "B314", "B331", "B405", "B420", "C112", "C415", "C420", "C303", "D202", "D211", "D213", "D220", "D304", "D415", "D420", },
            new String[]{"A331", "B013", "B104", "B132", "B331", "C132", "C435" },
            new String[]{"A205", },
            new String[]{"C230", "C113" },
            new String[]{"D102", },
            new String[]{"B335", "C132", "C435"},
            new String[]{"A031", "A104", "A205", "A315", "B002", "B031", "B204", "B315", "C002", "C315", "C130", "C003" },
            new String[]{"A114", "A215", "A405", "B002", "B013", "B114", "B405", },
            new String[]{"A103", "A112", "A210", "A304", "A320", "A404", "A405", "B002", "B103", "B112", "B203", "B320", "B330", "B404", "B405", "B430", "C002", "C315", "C102", "C405", "C330", "C203", "C430", "C313"},
            new String[]{"A003", "A104", "D003", },
            new String[]{"A021", "A104", "A225", "A230", "C230", "C113", "D003", "D120", "D225", },
            new String[]{"A132", "A414", "B335", "D324", "D414", "D435", },
    };

    private final static PenguinsPoolParty[] EXPERT_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("EEEIEPPEIIEEEIEEIIII",
                    new Ice[]{new Ice('C', new int[]{4, 3, 3, 3, 2, 3, 1, 3, }, 5),
                            new Ice('D', new int[]{3, 0, 3, 1, 3, 2, 4, 1, }, 3),
                    }),
            new PenguinsPoolParty("EPEPIEEEIEEPIIEEEEEE",
                    new Ice[]{new Ice('C', new int[]{2, 2, 3, 2, 3, 1, 4, 0, }, 1),
                    }),
            new PenguinsPoolParty("EEPIEEIIIEPEEEEEEEEE",
                    new Ice[]{new Ice('A', new int[]{1, 1, 2, 1, 3, 1, 3, 0, }, 2),
                    }),
            new PenguinsPoolParty("EEEPEPEIEEEEIIPEIEEE",
                    new Ice[]{new Ice('D', new int[]{1, 3, 2, 2, 3, 2, 2, 1, }, 1),
                    }),
            new PenguinsPoolParty("IIIEEIPIPEEIPIEEEEIE",
                    new Ice[]{new Ice('A', new int[]{1, 0, 0, 0, 0, 1, 1, 2, }, 4),
                            new Ice('C', new int[]{2, 0, 2, 1, 3, 2, 3, 3, }, 3),
                    }),
            new PenguinsPoolParty("EEIIEEEIIEEPIIIPEEPI",
                    new Ice[]{new Ice('B', new int[]{3, 0, 2, 0, 2, 1, 2, 2, }, 4),
                            new Ice('C', new int[]{3, 1, 3, 2, 4, 2, 4, 3, }, 3),
                    }),
            new PenguinsPoolParty("EIIIIEEPIEEPEIIPEEIE",
                    new Ice[]{new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0, }, 2),
                            new Ice('D', new int[]{3, 1, 3, 2, 3, 3, 4, 2, }, 3),
                    }),
            new PenguinsPoolParty("EEPEIPEIIEEIEIIIIEEE",
                    new Ice[]{new Ice('A', new int[]{4, 0, 3, 1, 3, 2, 4, 2, }, 4),
                            new Ice('C', new int[]{2, 1, 1, 2, 1, 3, 0, 3, }, 4),
                    }),
            new PenguinsPoolParty("EEEPEEPEEEEEEIIEEIIP",
                    new Ice[]{new Ice('D', new int[]{2, 3, 3, 3, 4, 2, 3, 2, }, 1),
                    }),
            new PenguinsPoolParty("IEEEEIEPEEIIEEEEPEEE",
                    new Ice[]{new Ice('D', new int[]{0, 0, 0, 1, 0, 2, 1, 2, }, 3),
                    }),
            new PenguinsPoolParty("EEEEEIIIIEIPIPEIIPEE",
                    new Ice[]{new Ice('B', new int[]{1, 1, 0, 1, 0, 2, 0, 3, }, 4),
                            new Ice('C', new int[]{3, 1, 2, 1, 2, 2, 1, 3, }, 4),
                    }),
            new PenguinsPoolParty("EIIEEEIEIEEEEPEEEEPE",
                    new Ice[]{new Ice('D', new int[]{3, 1, 2, 0, 1, 0, 1, 1, }, 5),
                    }),
            new PenguinsPoolParty("EPIEEEIPIIEIIIIEEEPE",
                    new Ice[]{new Ice('A', new int[]{1, 1, 1, 2, 2, 2, 3, 2, }, 3),
                            new Ice('B', new int[]{4, 2, 4, 1, 3, 1, 2, 0, }, 0),
                    }),
            new PenguinsPoolParty("EEIPEIPIIIEIPIIEEEEE",
                    new Ice[]{new Ice('B', new int[]{4, 2, 4, 1, 3, 1, 2, 0, }, 0),
                            new Ice('C', new int[]{3, 2, 2, 1, 1, 2, 0, 1, }, 5),
                    }),
            new PenguinsPoolParty("IIEPEEIPEPEIEEIEIIIE",
                    new Ice[]{new Ice('B', new int[]{1, 3, 2, 3, 3, 3, 4, 2, }, 2),
                            new Ice('D', new int[]{1, 2, 1, 1, 1, 0, 0, 0, }, 0),
                    }),
            new PenguinsPoolParty("EIIIIEIIPEIIEPEPEEEE",
                    new Ice[]{new Ice('A', new int[]{4, 0, 3, 0, 2, 0, 2, 1, }, 5),
                            new Ice('B', new int[]{0, 2, 1, 2, 1, 1, 1, 0, }, 1),
                    }),
    };

    private final static String[][] EXPERT_EXPECTED_PLACEMENTS = new String[][]{
            new String[]{"A121", "A210", "A225", },
            new String[]{"A114", "A215", "B013", "B114", "B132", "D002", "D013", "D215", "D235", },
            new String[]{"B003", "B122", "B132", "B230", "B331", "B335", "B425", "C122", "C130", "C132", "C231", "D031", "D122", "D222", "D231", "D324", "D335", "D414", "D430", "D435", },
            new String[]{"A021", "A315", "B021", "C002", "C021", },
            new String[]{"D235", },
            new String[]{"A114", },
            new String[]{"A013", "A114", "B013", },
            new String[]{"D435", },
            new String[]{"A003", "A012", "A013", "A022", "A031", "A104", "A121", "A205", "A210", "A225", "A415", "B003", "B012", "B104", "B131", "B415", "C031", "C130", "C131", },
            new String[]{"A221", "A222", "A320", "A321", "A331", "A404", "A410", "A414", "A425", "A430", "B221", "B231", "B320", "B331", "B404", "B405", "B420", "C102", "C221", "C231", "C420", "C430", },
            new String[]{"A410", },
            new String[]{"A003", "A013", "A022", "A031", "A214", "A225", "A230", "B003", "B013", "B230", "C031", "C130", },
            new String[]{"D013", "D235", },
            new String[]{"D235", },
            new String[]{"A221", "C221", },
            new String[]{"C132", "D222", "D435", },
    };

    private final static String[][] EXPERT_EXPECTED_PLACEMENTS_EXTENDED = new String[][]{
            new String[]{"A121", "A210", "A225", },
            new String[]{"A114", "A215", "B013", "B114", "B132", "D002", "D013", "D215", "D235", },
            new String[]{"B003", "B122", "B132", "B230", "B331", "B335", "B425", "C122", "C425", "C130", "C003", "C132", "C435", "C231", "C414", "D031", "D122", "D222", "D231", "D324", "D335", "D414", "D430", "D435", },
            new String[]{"A021", "A315", "B021", "C002", "C315", "C021", "C204"},
            new String[]{"D235", },
            new String[]{"A114", },
            new String[]{"A013", "A114", "B013", },
            new String[]{"D435", },
            new String[]{"A003", "A012", "A013", "A022", "A031", "A104", "A121", "A205", "A210", "A225", "A415", "B003", "B012", "B104", "B131", "B415", "C031", "C214", "C130", "C003", "C131", "C314"},
            new String[]{"A221", "A222", "A320", "A321", "A331", "A404", "A410", "A414", "A425", "A430", "B221", "B231", "B320", "B331", "B404", "B405", "B420", "C102", "C405", "C221", "C404", "C231", "C414", "C420", "C303", "C430", "C313"},
            new String[]{"A410", },
            new String[]{"A003", "A013", "A022", "A031", "A214", "A225", "A230", "B003", "B013", "B230", "C031", "C214", "C130", "C003"},
            new String[]{"D013", "D235", },
            new String[]{"D235", },
            new String[]{"A221", "C221", "C404"},
            new String[]{"C132", "C435", "D222", "D435", },
    };

    private final static PenguinsPoolParty[] MASTER_BOARDS = new PenguinsPoolParty[]{
            new PenguinsPoolParty("PEEEPEIEEEEIIEEPEEIE",
                    new Ice[]{new Ice('B', new int[]{1, 1, 1, 2, 2, 2, 3, 3, }, 3),
                    }),
            new PenguinsPoolParty("EIIIIEEEEEPEIIIPPEEI",
                    new Ice[]{new Ice('A', new int[]{4, 3, 4, 2, 3, 2, 2, 2, }, 0),
                            new Ice('C', new int[]{1, 0, 2, 0, 3, 0, 4, 0, }, 2),
                    }),
            new PenguinsPoolParty("PEEIIIEPEIIEEIPEIIEE",
                    new Ice[]{new Ice('A', new int[]{3, 2, 4, 1, 4, 0, 3, 0, }, 1),
                            new Ice('B', new int[]{0, 1, 0, 2, 1, 3, 2, 3, }, 3),
                    }),
            new PenguinsPoolParty("IIIPEEEIEPEEIIEEIIPE",
                    new Ice[]{new Ice('A', new int[]{2, 1, 2, 0, 1, 0, 0, 0, }, 0),
                            new Ice('D', new int[]{3, 2, 2, 2, 1, 3, 2, 3, }, 4),
                    }),
            new PenguinsPoolParty("EIIIIEPPPEEEIEIEEEII",
                    new Ice[]{new Ice('C', new int[]{4, 0, 3, 0, 2, 0, 1, 0, }, 5),
                            new Ice('D', new int[]{2, 2, 3, 3, 4, 3, 4, 2, }, 2),
                    }),
            new PenguinsPoolParty("EPEEPIIEEPEIEEEEIEEE",
                    new Ice[]{new Ice('D', new int[]{1, 3, 1, 2, 1, 1, 0, 1, }, 0),
                    }),
            new PenguinsPoolParty("IEIEEEIIIEEEPIIEEPEI",
                    new Ice[]{new Ice('B', new int[]{4, 3, 4, 2, 3, 2, 2, 1, }, 0),
                            new Ice('C', new int[]{3, 1, 2, 0, 1, 1, 0, 0, }, 5),
                    }),
            new PenguinsPoolParty("EEEPIEIIIEPIIIIEEEEP",
                    new Ice[]{new Ice('B', new int[]{1, 1, 2, 1, 3, 1, 4, 0, }, 2),
                            new Ice('C', new int[]{1, 2, 2, 2, 3, 2, 4, 2, }, 2),
                    }),
            new PenguinsPoolParty("EEIEEPEIEEEEPIEEEEIE",
                    new Ice[]{new Ice('C', new int[]{2, 0, 2, 1, 3, 2, 3, 3, }, 3),
                    }),
            new PenguinsPoolParty("EIIEEEEIEEEIIIPEIIPE",
                    new Ice[]{new Ice('A', new int[]{1, 2, 2, 1, 2, 0, 1, 0, }, 1),
                            new Ice('D', new int[]{3, 2, 2, 2, 1, 3, 2, 3, }, 4),
                    }),
            new PenguinsPoolParty("IIPEEIIIIEEIEPEEIEEE",
                    new Ice[]{new Ice('A', new int[]{1, 0, 1, 1, 2, 1, 3, 1, }, 3),
                            new Ice('C', new int[]{1, 3, 1, 2, 0, 1, 0, 0, }, 0),
                    }),
            new PenguinsPoolParty("EEPEEEIIIIIEPEEIIIEE",
                    new Ice[]{new Ice('C', new int[]{1, 1, 2, 1, 3, 1, 4, 1, }, 2),
                            new Ice('D', new int[]{2, 3, 1, 3, 0, 2, 0, 3, }, 5),
                    }),
    };

    private final static String[][] MASTER_EXPECTED_PLACEMENTS = new String[][]{
            new String[]{"A203", "A304", "A420", "C420", "C430", "D102", "D212", "D303", "D320", "D415", "D430", },
            new String[]{"B012", "B415", "D215", },
            new String[]{"D315", },
            new String[]{"B114", },
            new String[]{"A031", "B013", },
            new String[]{"A213", "A221", "A231", "A304", "A314", "A425", "A430", "B203", "B213", "B221", "B231", "B304", "B314", "B430", "C330", "C430", },
            new String[]{"A031", "D013", },
            new String[]{"A205", },
            new String[]{"A021", "B021", "B031", "D120", "D235", "D420", },
            new String[]{"B114", },
            new String[]{"B331", "D222", "D435", },
            new String[]{"A104", },
    };

    private final static String[][] MASTER_EXPECTED_PLACEMENTS_EXTENDED = new String[][]{
            new String[]{"A203", "A304", "A420", "C420", "C303", "C430", "C313", "D102", "D212", "D303", "D320", "D415", "D430", },
            new String[]{"B012", "B415", "D215", },
            new String[]{"D315", },
            new String[]{"B114", },
            new String[]{"A031", "B013", },
            new String[]{"A213", "A221", "A231", "A304", "A314", "A425", "A430", "B203", "B213", "B221", "B231", "B304", "B314", "B430", "C330", "C203", "C430", "C313"},
            new String[]{"A031", "D013", },
            new String[]{"A205", },
            new String[]{"A021", "B021", "B031", "D120", "D235", "D420", },
            new String[]{"B114", },
            new String[]{"B331", "D222", "D435", },
            new String[]{"A104", },
    };

    private void test(PenguinsPoolParty[] boards, String[][] expected, String[][] expectedExtended) {
        for (int i = 0; i < boards.length; i++) {
            PenguinsPoolParty penguinsPoolParty = boards[i];
            String[] actual = penguinsPoolParty.getAllValidPlacements();
            boolean equalsReduced = Arrays.equals(expected[i], actual);
            boolean equalsExtended = Arrays.equals(expectedExtended[i], actual);
            assertTrue(equalsReduced || equalsExtended, "For board state " + boards[i].boardToString() +
                    ", expected either valid placements " + Arrays.toString(expected[i]) +
                    " or valid placements " + Arrays.toString(expectedExtended[i]) + " but actually got " + Arrays.toString(actual) + "!");
        }
    }

    @Test
    public void testStarter() {
        test(STARTER_BOARDS, STARTER_EXPECTED_PLACEMENTS, STARTER_EXPECTED_PLACEMENTS_EXTENDED);
    }

    @Test
    public void testJunior() {
        test(JUNIOR_BOARDS, JUNIOR_EXPECTED_PLACEMENTS, JUNIOR_EXPECTED_PLACEMENTS_EXTENDED);
    }

    @Test
    public void testExpert() {
        test(EXPERT_BOARDS, EXPERT_EXPECTED_PLACEMENTS, EXPERT_EXPECTED_PLACEMENTS_EXTENDED);
    }

    @Test
    public void testMaster() {
        test(MASTER_BOARDS, MASTER_EXPECTED_PLACEMENTS, MASTER_EXPECTED_PLACEMENTS_EXTENDED);
    }

}
