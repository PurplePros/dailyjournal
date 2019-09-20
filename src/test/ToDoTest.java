import elements.Task;
import sections.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sections.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {

    private ToDo list;

    @BeforeEach
    public void runBefore() {
        list = new ToDo();
    }

    // addAchievement
    @Test
    public void testAddSomeAchievement() {
        list.addTask("Run a marathon", "1:00");
        list.addTask("Finished all my homework", "10:30");
        assertEquals(2, list.getList().size());
    }

    @Test
    public void testAddLotsAchievements() {
        for (int i = 0; i < 50; i++) {
            list.addTask("Eat dinner", "8:00");
        }
        assertEquals(50, list.getList().size());
    }

    // deleteAchievement
    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAchievementRemovedEmpty() {
        for (int i = 0; i < 10; i++) {
            list.addTask("Eat breakfast", "8:00");
        }
        list.deleteTask(0);
        assertEquals(9, list.getList().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllAchievementsRemoved() {
        for (int i = 0; i < 100; i++) {
            list.addTask("Eat string beans", "3:00");
        }
        for (int i = 99; i >= 0; i--) {
            list.deleteTask(i);
        }
        assertEquals(0, list.getList().size());
    }

    @Test
    // test case: some test cases removed
    public void testSomeAchievementsRemoved() {
        for (int i = 0; i < 100; i++) {
            list.addTask("Do homework", "8:00");
        }
        for (int i = 50; i > 0; i--) {
            list.deleteTask(i);
        }
        assertEquals(50, list.getList().size());
    }
}