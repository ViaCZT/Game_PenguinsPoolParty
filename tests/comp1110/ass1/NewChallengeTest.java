package comp1110.ass1;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashSet;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 1000, unit = MILLISECONDS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class NewChallengeTest {

    private int getDifficulty(Challenge challenge) {
        for (int i = 0; i < Challenge.CHALLENGES.length; i++) {
            if (challenge == Challenge.CHALLENGES[i]) {
                return i / 16;
            }
        }
        return -1;
    }

    private int countChallenges(Challenge[] challenges) {
        HashSet<Challenge> challengeSet = new HashSet<>(Arrays.asList(challenges));
        return challengeSet.size();

    }

    private void test(int difficulty, int numChallenges) {
        Challenge[] out = new Challenge[numChallenges];
        for (int i = 0; i < numChallenges; i++) {
            out[i] = Challenge.newChallenge(difficulty);
            assertNotNull(out[i], "The output of newChallenge() is null. Have you implemented this method yet?");
            int diff = getDifficulty(out[i]);
            assertEquals(diff, difficulty, "Expected difficulty " + difficulty + ", but " + (diff == -1 ? "did not get one from the prepared objectives" : "got one of difficulty " + diff) + ": problem number " + out[i].getProblemNumber() + ".");
        }
        int unique = countChallenges(out);
        assertTrue(unique >= 3, "Expected at least 3 different objectives after calling newObjective() 12 times, but only got " + unique + ".");
    }

    @Test
    public void testStarter() {
        test(0, 16);
    }

    @Test
    public void testJunior() {
        test(1, 16);
    }

    @Test
    public void testExpert() {
        test(2, 16);
    }

    @Test
    public void testMaster() {
        test(3, 12);
    }

}
