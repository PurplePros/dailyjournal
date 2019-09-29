import sections.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    private ToDo list;

    @BeforeEach
    public void runBefore() throws IOException {
        list = new ToDo();
    }

    // addAchievement
    @Test
    public void testAddSomeTasks() {
        list.addTask("Run a marathon", "1:00");
        list.addTask("Finished all my homework", "10:30");
        assertEquals(4, list.getList().size());
    }

    @Test
    public void testAddLotsTasks() {
        for (int i = 0; i < 50; i++) {
            list.addTask("Eat dinner", "8:00");
        }
        assertEquals(50, list.getList().size());
    }

    // deleteAchievement
    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneTaskRemovedEmpty() {
        for (int i = 0; i < 10; i++) {
            list.addTask("Eat breakfast", "8:00");
        }
        list.deleteTask(0);
        assertEquals(11, list.getList().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllTasksRemoved() {
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
    public void testSomeTasksRemoved() {
        for (int i = 0; i < 100; i++) {
            list.addTask("Do homework", "8:00");
        }
        for (int i = 50; i > 0; i--) {
            list.deleteTask(i);
        }
        assertEquals(52, list.getList().size());
    }

    @Test
    // test case: loading no achievements
    public void testLoadNoTasks() throws IOException {
        list.load();
        assertEquals(4, list.getList().size());
    }

    @Test
    // test case: loading some achievements
    public void T() throws IOException {
        list.addTask("Wake up", "8:00");
        list.addTask("Breakfast", "9:00");
        list.save(list.getList());
        list.load();
        assertEquals(4, list.getList().size());
    }
}
