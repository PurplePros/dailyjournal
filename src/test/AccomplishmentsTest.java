import elements.AchievedTask;
import sections.Accomplishments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccomplishmentsTest {

    private Accomplishments accomplishedList;

    @BeforeEach
    public void runBefore() throws IOException {
        accomplishedList = new Accomplishments();
    }

    @Test
    public void testAddSomeAchievements() {
        AchievedTask action1 = new AchievedTask("Ran a marathon");
        accomplishedList.addAchievedTask(action1);
        AchievedTask action2 = new AchievedTask("Finished all my homework");
        accomplishedList.addAchievedTask(action2);
        assertEquals(4, accomplishedList.getList().size());
    }

    @Test
    public void testAddLotsAchievements() {
        AchievedTask action = new AchievedTask("Passed all my courses");
        for (int i = 0; i < 50; i++) {
            accomplishedList.addAchievedTask(action);
        }
        assertEquals(52, accomplishedList.getList().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAchievementRemovedEmpty() {
        AchievedTask action = new AchievedTask("Ran a marathon");
        accomplishedList.addAchievedTask(action);
        accomplishedList.deleteAchievedTask(0);
        assertEquals(0, accomplishedList.getList().size());
    }

    @Test
    // test case: all achievements removed
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
    // test case: some achievements removed
    public void testSomeAchievementsRemoved() {
        AchievedTask action = new AchievedTask("Became a meat eater again");
        for (int i = 0; i < 100; i++) {
            accomplishedList.addAchievedTask(action);
        }
        for (int i = 50; i > 0; i--) {
            accomplishedList.deleteAchievedTask(i);
        }
        assertEquals(52, accomplishedList.getList().size());
    }

    @Test
    // test case: loading no achievements
    public void testLoadNoAchievements() throws IOException {
        accomplishedList.load();
        assertEquals(4, accomplishedList.getList().size());
    }

    @Test
    // test case: loading some achievements
    public void testLoadSaveSomeAchievements() throws IOException {
        AchievedTask t1 = new AchievedTask("Ate chocolate");
        AchievedTask t2 = new AchievedTask("Ate bananas");
        accomplishedList.addAchievedTask(t1);
        accomplishedList.addAchievedTask(t2);
        accomplishedList.save(accomplishedList.getList());
        accomplishedList.load();
        assertEquals(4, accomplishedList.getList().size());
    }

}
