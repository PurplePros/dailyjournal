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
    public void runBefore() {
        toDoList = new ToDoList();
    }

    @Test
    public void testAddOneToDo() {
        ToDo t1 = new ToDo("Run a marathon", "1:00", "", toDoList);
        toDoList.addTask(t1);
        assertEquals(1, toDoList.getTasks().size());
    }

    @Test
    public void testAddLotsSameToDos() {
        ToDo t = new ToDo("Finished all my homework", "10:30", "", toDoList);
        for (int i = 0; i < 50; i++) {
            toDoList.addTask(t);
        }
        assertEquals(1, toDoList.getTasks().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneToDoRemovedEmpty() throws InvalidTaskNumberException {
        ToDo t = new ToDo("Breakfast", "8:00", "Home", toDoList);
        toDoList.addTask(t);
        toDoList.deleteTask(0);
        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllToDosRemoved() throws InvalidTaskNumberException {
        ToDo t1 = new ToDo("Gym", "1:00", "The Arc", toDoList);
        ToDo t2 = new ToDo("Breakfast", "8:00", "Home", toDoList);
        ToDo t3 = new ToDo("Work on CPSC210", "4:00", "Koerner's Library", toDoList);

        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);

        toDoList.deleteTask(0);
        toDoList.deleteTask(0);
        toDoList.deleteTask(0);

        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    // test case: given an index that's not valid
    public void testInvalidTaskIndex() {
        ToDo action = new ToDo("Work on CPSC210", "4:00", "Koerner's Library", toDoList);
        toDoList.addTask(action);
        try {
            toDoList.deleteTask(2);
            fail("Wrong index!");
        } catch (InvalidTaskNumberException e) {

        }
        assertEquals(1, toDoList.getTasks().size());
    }

    @Test
    // test case: given an index that's valid
    public void testValidTaskIndex() {
        ToDo action = new ToDo("Work on CPSC210", "4:00", "Koerner's Library", toDoList);
        toDoList.addTask(action);
        try {
            toDoList.deleteTask(0);
        } catch (InvalidTaskNumberException e) {
            fail("Right index!");
        }
        assertEquals(0, toDoList.getTasks().size());
    }
}
