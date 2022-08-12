package comp1110.ass1;

import java.util.Random;

public class Challenge {

    // The problem number of the challenge in the game booklet
    private final int problemNumber;

    // The initial placement of penguins in the challenge
    private final String initialState;

    // Each challenge in the game booklet
    public static final Challenge[] CHALLENGES = new Challenge[]{
            // Starter
            new Challenge(1, "20113123"),
            new Challenge(2, "21412233"),
            new Challenge(3, "11313242"),
            new Challenge(4, "20113142"),
            new Challenge(5, "10114103"),
            new Challenge(6, "30213203"),
            new Challenge(7, "00113343"),
            new Challenge(8, "40122243"),
            new Challenge(9, "31010323"),
            new Challenge(10, "00022343"),
            new Challenge(11, "111213"),
            new Challenge(12, "00103040"),
            new Challenge(13, "204142"),
            new Challenge(14, "03133343"),
            new Challenge(15, "102042"),
            new Challenge(16, "12322343"),
            // Junior
            new Challenge(17, "303143"),
            new Challenge(18, "00311223"),
            new Challenge(19, "31032343"),
            new Challenge(20, "10110203"),
            new Challenge(21, "321343"),
            new Challenge(22, "122232"),
            new Challenge(23, "301242"),
            new Challenge(24, "2021"),
            new Challenge(25, "112132"),
            new Challenge(26, "0212"),
            new Challenge(27, "402102"),
            new Challenge(28, "103112"),
            new Challenge(29, "014123"),
            new Challenge(30, "203011"),
            new Challenge(31, "2013"),
            new Challenge(32, "310242"),
            // Expert
            new Challenge(33, "0111"),
            new Challenge(34, "103012"),
            new Challenge(35, "2002"),
            new Challenge(36, "300142"),
            new Challenge(37, "113122"),
            new Challenge(38, "120333"),
            new Challenge(39, "211203"),
            new Challenge(40, "2001"),
            new Challenge(41, "301143"),
            new Challenge(42, "2113"),
            new Challenge(43, "123223"),
            new Challenge(44, "3233"),
            new Challenge(45, "102133"),
            new Challenge(46, "301122"),
            new Challenge(47, "302141"),
            new Challenge(48, "313203"),
            // Master
            new Challenge(49, "004003"),
            new Challenge(50, "020313"),
            new Challenge(51, "002142"),
            new Challenge(52, "304133"),
            new Challenge(53, "112131"),
            new Challenge(54, "104041"),
            new Challenge(55, "2223"),
            new Challenge(56, "300243"),
            new Challenge(57, "0122"),
            new Challenge(58, "4233"),
            new Challenge(59, "2032"),
            new Challenge(60, "2022")
    };

    /**
     * Constructor for a challenge. It takes a problem number (used to later
     * identify the challenge) and an initial state, denoting where the
     * penguins are placed on the game board.
     *
     * @param problemNumber the problem number of this challenge as per the
     *                      game booklet
     * @param initialState  the initial placement of penguins in this challenge
     */
    public Challenge(int problemNumber, String initialState) {
        assert problemNumber >= 1 && problemNumber <= 60;
        //断言。assert关键字用于判断某一条件是否达成，如果达成不进行任何操作，如没有达成会抛出异常
        this.initialState = initialState;
        this.problemNumber = problemNumber;
    }

    /**
     * @return the problem number of this challenge
     */
    public int getProblemNumber() {
        return this.problemNumber;
    }

    /**
     * @return the initial state of this challenge
     */
    public String getInitialState() {
        return this.initialState;
    }

    /**
     * @param difficulty a number within the range [0, 3], with higher numbers
     *                   denoting a harder difficulty
     * @return a random challenge of the given difficulty
     */
    public static Challenge newChallenge(int difficulty) {
        assert difficulty >= 0 && difficulty <= 3;
        // FIXME: Task 5
        Random r = new Random();
        int i;
        if (difficulty == 0){
            i = r.nextInt(16); //[0,16)
        } else if (difficulty == 1) {
            i = 16 + r.nextInt(16);
        } else if (difficulty == 2) {
            i = 32 + r.nextInt(16);
        }else {
            i = 48 + r.nextInt(12);
        }
        return CHALLENGES[i];
    }

}
