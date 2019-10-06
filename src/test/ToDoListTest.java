import elements.Task;
import elements.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {

    private ToDoList toDoList;

    @BeforeEach
    public void runBefore() throws IOException {
        toDoList = new ToDoList();
    }

    @Test
    public void testAddOneToDo() {
        Task t1 = new Task("Run a marathon", "1:00", "");
        toDoList.addTask(t1);
        assertEquals(1, toDoList.getList().size());
    }

    @Test
    public void testAddLotsToDos() {
        Task t = new Task("Finished all my homework", "10:30", "");
        for (int i = 0; i < 50; i++) {
            toDoList.addTask(t);
        }
        assertEquals(50, toDoList.getList().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneToDoRemovedEmpty() {
        Task t = new Task("Breakfast", "8:00", "Home");
        toDoList.addTask(t);
        toDoList.deleteTask(0);
        assertEquals(0, toDoList.getList().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllToDosRemoved() {
        Task t = new Task("Gym", "1:00", "The Arc");
        for (int i = 0; i < 100; i++) {
            toDoList.addTask(t);
        }
        for (int i = 99; i >= 0; i--) {
            toDoList.deleteTask(i);
        }
        assertEquals(0, toDoList.getList().size());
    }

    @Test
    // test case: some test cases removed
    public void testSomeToDosRemoved() {
        Task t1 = new Task("Run a marathon", "1:00", "");
        for (int i = 0; i < 100; i++) {
            toDoList.addTask(t1);
        }
        for (int i = 50; i > 0; i--) {
            toDoList.deleteTask(i);
        }
        assertEquals(50, toDoList.getList().size());
    }
}
