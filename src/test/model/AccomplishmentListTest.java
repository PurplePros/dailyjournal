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
        Accomplishment accomplishment = new Accomplishment("Ran a marathon", "", "", accomplishedList);
        accomplishedList.addTask(accomplishment);
        assertEquals(1, accomplishedList.getList().size());
    }

    @Test
    public void testAddLotsSameAchievements() {
        Accomplishment action = new Accomplishment("Passed all my courses", "", "", accomplishedList);
        for (int i = 0; i < 50; i++) {
            accomplishedList.addTask(action);
        }
        assertEquals(1, accomplishedList.getList().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAchievementRemovedEmpty() throws InvalidTaskNumberException {
        Accomplishment action = new Accomplishment("Achieved world peace", "", "", accomplishedList);
        accomplishedList.addTask(action);
        accomplishedList.deleteTask(0);
        assertEquals(0, accomplishedList.getList().size());
    }
/*
    @Test
    // test case: all achievements removed
    public void testAllAchievementsRemoved() throws InvalidTaskNumberException {
        Accomplishment action = new Accomplishment("Became vegetarian", "", "", accomplishedList);
        for (int i = 0; i < 100; i++) {
            accomplishedList.addTask(action);
        }
        for (int i = 99; i >= 0; i--) {
            accomplishedList.deleteTask(i);
        }
        assertEquals(0, accomplishedList.getList().size());
    }
*/
/*
    @Test
    // test case: some achievements removed
    public void testSomeAchievementsRemoved() throws InvalidTaskNumberException {
        Accomplishment action = new Accomplishment("Became a meat eater again", "", "", accomplishedList);
        for (int i = 0; i < 100; i++) {
            accomplishedList.addTask(action);
        }
        for (int i = 50; i > 0; i--) {
            accomplishedList.deleteTask(i);
        }
        assertEquals(50, accomplishedList.getList().size());
    }
*/
    @Test
    // test case: given an index that's not valid
    public void testInvalidTaskIndex() {
        Accomplishment action = new Accomplishment("Became a meat eater again", "", "", accomplishedList);
        accomplishedList.addTask(action);
        try {
            accomplishedList.deleteTask(2);
            fail("Wrong index!");
        } catch (InvalidTaskNumberException e) {

        }
        assertEquals(1, accomplishedList.getList().size());
    }

    @Test
    // test case: given an index that's valid
    public void testValidTaskIndex() {
        Accomplishment action = new Accomplishment("Became a meat eater again", "", "", accomplishedList);
        accomplishedList.addTask(action);
        try {
            accomplishedList.deleteTask(0);
        } catch (InvalidTaskNumberException e) {
            fail("Right index!");
        }
        assertEquals(0, accomplishedList.getList().size());
    }

}
