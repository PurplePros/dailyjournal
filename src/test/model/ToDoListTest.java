package model;

import model.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {

    private ToDoList toDoList;

    @BeforeEach
    public void runBefore() throws IOException {
        toDoList = new ToDoList();
    }

    @Test
    public void testAddOneToDo() {
        Task t1 = new Task("Run a marathon", "1:00", "", toDoList);
        toDoList.addTask(t1);
        assertEquals(1, toDoList.getList().size());
    }

    @Test
    public void testAddLotsSameToDos() {
        Task t = new Task("Finished all my homework", "10:30", "", toDoList);
        for (int i = 0; i < 50; i++) {
            toDoList.addTask(t);
        }
        assertEquals(1, toDoList.getList().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneToDoRemovedEmpty() throws InvalidTaskNumberException {
        Task t = new Task("Breakfast", "8:00", "Home", toDoList);
        toDoList.addTask(t);
        toDoList.deleteTask(0);
        assertEquals(0, toDoList.getList().size());
    }
/*
    @Test
    // test case: all test cases removed
    public void testAllToDosRemoved() throws InvalidTaskNumberException {
        Task t = new Task("Gym", "1:00", "The Arc", toDoList);
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
    public void testSomeToDosRemoved() throws InvalidTaskNumberException {
        Task t1 = new Task("Run a marathon", "1:00", "", toDoList);
        for (int i = 0; i < 100; i++) {
            toDoList.addTask(t1);
        }
        for (int i = 50; i > 0; i--) {
            toDoList.deleteTask(i);
        }
        assertEquals(50, toDoList.getList().size());
    }
*/
    @Test
    // test case: given an index that's not valid
    public void testInvalidTaskIndex() {
        Task action = new Task("Work on CPSC210", "4:00", "Koerner's Library", toDoList);
        toDoList.addTask(action);
        try {
            toDoList.deleteTask(2);
            fail("Wrong index!");
        } catch (InvalidTaskNumberException e) {

        }
        assertEquals(1, toDoList.getList().size());
    }

    @Test
    // test case: given an index that's valid
    public void testValidTaskIndex() {
        Task action = new Task("Work on CPSC210", "4:00", "Koerner's Library", toDoList);
        toDoList.addTask(action);
        try {
            toDoList.deleteTask(0);
        } catch (InvalidTaskNumberException e) {
            fail("Right index!");
        }
        assertEquals(0, toDoList.getList().size());
    }
}
