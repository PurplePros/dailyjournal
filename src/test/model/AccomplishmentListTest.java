package model;

import model.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AccomplishmentListTest {

    private AccomplishmentList accomplishedList;

    @BeforeEach
    public void runBefore() throws IOException {
        accomplishedList = new AccomplishmentList();
    }

    @Test
    public void testAddOneAchievement() {
        Accomplishment accomplishment = new Accomplishment("Ran a marathon", accomplishedList);
        accomplishedList.addTask(accomplishment.getTask());
        assertEquals(1, accomplishedList.getList().getTasks().size());
    }

    @Test
    public void testAddLotsSameAchievements() {
        Accomplishment action = new Accomplishment("Passed all my courses", accomplishedList);
        for (int i = 0; i < 50; i++) {
            accomplishedList.addTask(action.getTask());
        }
        assertEquals(1, accomplishedList.getList().getTasks().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAchievementRemovedEmpty() throws InvalidTaskNumberException {
        Accomplishment action = new Accomplishment("Achieved world peace", accomplishedList);
        accomplishedList.addTask(action.getTask());
        accomplishedList.deleteTask(0);
        assertEquals(0, accomplishedList.getList().getTasks().size());
    }

    @Test
    // test case: all achievements removed
    public void testAllAchievementsRemoved() throws InvalidTaskNumberException {
        Accomplishment action1 = new Accomplishment("Became vegetarian", accomplishedList);
        Accomplishment action2 = new Accomplishment("Achieved world peace", accomplishedList);
        Accomplishment action3 = new Accomplishment("Became a meat eater again", accomplishedList);

        accomplishedList.addTask(action1.getTask());
        accomplishedList.addTask(action2.getTask());
        accomplishedList.addTask(action3.getTask());

        accomplishedList.deleteTask(1);
        accomplishedList.deleteTask(1);
        accomplishedList.deleteTask(0);

        assertEquals(0, accomplishedList.getList().getTasks().size());
    }

    @Test
    // test case: given an index that's not valid
    public void testInvalidTaskIndex() {
        Accomplishment action = new Accomplishment("Became a meat eater again", accomplishedList);
        accomplishedList.addTask(action.getTask());
        try {
            accomplishedList.deleteTask(2);
            fail("Wrong index!");
        } catch (InvalidTaskNumberException e) {

        }
        assertEquals(1, accomplishedList.getList().getTasks().size());
    }

    @Test
    // test case: given an index that's valid
    public void testValidTaskIndex() {
        Accomplishment action = new Accomplishment("Became a meat eater again", accomplishedList);
        accomplishedList.addTask(action.getTask());
        try {
            accomplishedList.deleteTask(0);
        } catch (InvalidTaskNumberException e) {
            fail("Right index!");
        }
        assertEquals(0, accomplishedList.getList().getTasks().size());
    }

}
