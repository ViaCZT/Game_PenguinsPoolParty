package comp1110.ass1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FindSolutionTest {

    private static final String[] STARTER_SOLUTIONS = new String[]{
            "A321B104C031D222",
            "A104B405C430D235",
            "A410B104C031D435",
            "A321B104C031D435",
            "A404B304C132D003",
            "A204B104C132D420",
            "A425B415C102D013",
            "A205B330C420D235",
            "A410B430C022D120",
            "A031B331C220D320",
            "A410B104C430D213",
            "A225B315C132D212",
            "A113B104C132D211",
            "A304B104C230D420",
            "A321B114C132D314",
            "A331B220C102D013",
    };

    private static final String[] JUNIOR_SOLUTIONS = new String[]{
            "A210B331C230D013",
            "A215B335C102D430",
            "A104B405C022D212",
            "A304B003C132D420",
            "A331B114C102D230",
            "A210B114C132D420",
            "A210B114C132D221",
            "A113B104C132D420",
            "A331B003C102D235",
            "A410B335C430D215",
            "A205B031C420D435",
            "A410B114C132D425",
            "A405B335C430D120",
            "A404B104C031D435",
            "A414B104C230D211",
            "A410B103C130D435",
    };

    private static final String[] EXPERT_SOLUTIONS = new String[]{
            "A430B415C102D235",
            "A212B013C002D222",
            "A031B103C420D435",
            "A225B103C132D202",
            "A410B104C031D231",
            "A114B314C102D430",
            "A321B013C002D222",
            "A225B103C132D420",
            "A425B104C031D202",
            "A410B104C230D313",
            "A331B220C102D013",
            "A420B003C102D235",
            "A410B003C430D235",
            "A205B325C132D420",
            "A205B113C430D235",
            "A410B132C220D003",
    };

    private static final String[] MASTER_SOLUTIONS = new String[]{
            "A215B122C132D102",
            "A104B405C430D213",
            "A123B114C102D221",
            "A205B112C122D235",
            "A414B003C102D235",
            "A031B002C430D213",
            "A410B104C031D313",
            "A205B112C231D031",
            "A304B021C132D420",
            "A214B114C102D221",
            "A104B213C420D235",
            "A104B330C420D235",
    };

    @Test
    public void testStarter() {
        for (int i = 0; i < 16; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(Challenge.CHALLENGES[i]);
            String boardState = penguinsPoolParty.boardToString();
            String exp = STARTER_SOLUTIONS[i];
            String actual = penguinsPoolParty.findSolution();
            assertEquals(exp, actual, "For challenge number " + i + ", and board state " +
                    boardState + ", expected solution " + exp + ", but got " + actual + "!");
        }
    }

    @Test
    public void testJunior() {
        for (int i = 0; i < 16; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(Challenge.CHALLENGES[i + 16]);
            String boardState = penguinsPoolParty.boardToString();
            String exp = JUNIOR_SOLUTIONS[i];
            String actual = penguinsPoolParty.findSolution();
            assertEquals(exp, actual, "For challenge number " + i + ", and board state " +
                    boardState + ", expected solution " + exp + ", but got " + actual + "!");
        }
    }

    @Test
    public void testExpert() {
        for (int i = 0; i < 16; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(Challenge.CHALLENGES[i + 32]);
            String boardState = penguinsPoolParty.boardToString();
            String exp = EXPERT_SOLUTIONS[i];
            String actual = penguinsPoolParty.findSolution();
            assertEquals(exp, actual, "For challenge number " + i + ", and board state " +
                    boardState + ", expected solution " + exp + ", but got " + actual + "!");
        }
    }

    @Test
    public void testMaster() {
        for (int i = 0; i < 12; i++) {
            PenguinsPoolParty penguinsPoolParty = new PenguinsPoolParty(Challenge.CHALLENGES[i + 48]);
            String boardState = penguinsPoolParty.boardToString();
            String exp = MASTER_SOLUTIONS[i];
            String actual = penguinsPoolParty.findSolution();
            assertEquals(exp, actual, "For challenge number " + i + ", and board state " +
                    boardState + ", expected solution " + exp + ", but got " + actual + "!");
        }
    }

}
