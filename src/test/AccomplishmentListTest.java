import elements.Accomplishment;
import elements.AccomplishmentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccomplishmentListTest {

    private AccomplishmentList accomplishedList;

    @BeforeEach
    public void runBefore() throws IOException {
        accomplishedList = new AccomplishmentList();
    }

    @Test
    public void testAddOneAchievement() {
        Accomplishment accomplishment = new Accomplishment("Ran a marathon", "", "");
        accomplishedList.addTask(accomplishment);
        assertEquals(1, accomplishedList.getList().size());
    }

    @Test
    public void testAddLotsAchievements() {
        Accomplishment action = new Accomplishment("Passed all my courses", "", "");
        for (int i = 0; i < 50; i++) {
            accomplishedList.addTask(action);
        }
        assertEquals(50, accomplishedList.getList().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAchievementRemovedEmpty() {
        Accomplishment action = new Accomplishment("Achieved world peace", "", "");
        accomplishedList.addTask(action);
        accomplishedList.deleteTask(0);
        assertEquals(0, accomplishedList.getList().size());
    }

    @Test
    // test case: all achievements removed
    public void testAllAchievementsRemoved() {
        Accomplishment action = new Accomplishment("Became vegetarian", "", "");
        for (int i = 0; i < 100; i++) {
            accomplishedList.addTask(action);
        }
        for (int i = 99; i >= 0; i--) {
            accomplishedList.deleteTask(i);
        }
        assertEquals(0, accomplishedList.getList().size());
    }

    @Test
    // test case: some achievements removed
    public void testSomeAchievementsRemoved() {
        Accomplishment action = new Accomplishment("Became a meat eater again", "", "");
        for (int i = 0; i < 100; i++) {
            accomplishedList.addTask(action);
        }
        for (int i = 50; i > 0; i--) {
            accomplishedList.deleteTask(i);
        }
        assertEquals(50, accomplishedList.getList().size());
    }
}
