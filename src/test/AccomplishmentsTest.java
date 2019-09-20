import elements.AchievedTask;
import sections.Accomplishments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccomplishmentsTest {

    private Accomplishments accomplishedList;

    @BeforeEach
    public void runBefore() {
        accomplishedList = new Accomplishments();
    }

    // addAchievement
    @Test
    public void testAddSomeAchievement() {
        AchievedTask action1 = new AchievedTask("Ran a marathon");
        accomplishedList.addAchievedTask(action1);
        AchievedTask action2 = new AchievedTask("Finished all my homework");
        accomplishedList.addAchievedTask(action2);
        assertEquals(2, accomplishedList.getList().size());
    }

    @Test
    public void testAddLotsAchievements() {
        AchievedTask action = new AchievedTask("Passed all my courses");
        for (int i = 0; i < 50; i++) {
            accomplishedList.addAchievedTask(action);
        }
        assertEquals(50, accomplishedList.getList().size());
    }

    // deleteAchievement
    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAchievementRemovedEmpty() {
        AchievedTask action = new AchievedTask("Ran a marathon");
        accomplishedList.addAchievedTask(action);
        accomplishedList.deleteAchievedTask(0);
        assertEquals(0, accomplishedList.getList().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllAchievementsRemoved() {
        AchievedTask action = new AchievedTask("Became vegetarian");
        for (int i = 0; i < 100; i++) {
            accomplishedList.addAchievedTask(action);
        }
        for (int i = 99; i >= 0; i--) {
            accomplishedList.deleteAchievedTask(i);
        }
        assertEquals(0, accomplishedList.getList().size());
    }

    @Test
    // test case: some test cases removed
    public void testSomeAchievementsRemoved() {
        AchievedTask action = new AchievedTask("Became a meat eater again");
        for (int i = 0; i < 100; i++) {
            accomplishedList.addAchievedTask(action);
        }
        for (int i = 50; i > 0; i--) {
            accomplishedList.deleteAchievedTask(i);
        }
        assertEquals(50, accomplishedList.getList().size());
    }
}
