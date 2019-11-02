package model;

import model.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {

    private Day day;

    @BeforeEach
    public void runBefore() throws IOException {
        ToDoList td = new ToDoList();
        AccomplishmentList al = new AccomplishmentList();
        AppointmentList apl = new AppointmentList();
        day = new Day("JournalLog", td, al, apl);
    }

    @Test
    public void testGetAchievementList() {
        ArrayList<SingleElementTask> list = new ArrayList<SingleElementTask>();
        SingleElementTask a1 = new Accomplishment("Became vegetarian", day.getSingleElementList().get("accs"));
        SingleElementTask a2 = new Accomplishment("Passed all my courses", day.getSingleElementList().get("accs"));
        SingleElementTask a3 = new Accomplishment("Climbed Mount Everest", day.getSingleElementList().get("accs"));
        day.addAchievement("Became vegetarian");
        day.addAchievement("Passed all my courses");
        day.addAchievement("Climbed Mount Everest");
        list.add(a1);
        list.add(a2);
        list.add(a3);
        assertEquals(list.get(0).getTask().getAction(), day.getAchievementList().get(0).getAction());
        assertEquals(list.get(1).getTask().getAction(), day.getAchievementList().get(1).getAction());
        assertEquals(list.get(2).getTask().getAction(), day.getAchievementList().get(2).getAction());
    }

    @Test
    public void testGetAppointmentList() {
        ArrayList<MultipleElementsTask> list = new ArrayList<MultipleElementsTask>();
        MultipleElementsTask a1 = new Appointment("Doctor's", "8:00", "213 Infield Dr", day.getMultipleElementsList().get("apps"));
        MultipleElementsTask a2 = new Appointment("Physiotherapy", "10:00", "34 Lake St", day.getMultipleElementsList().get("apps"));
        MultipleElementsTask a3 = new Appointment("Meeting with prof", "14:00", "", day.getMultipleElementsList().get("apps"));
        day.addAppointment("Doctor's", "8:00", "213 Infield Dr");
        day.addAppointment("Physiotherapy", "10:00", "34 Lake St");
        day.addAppointment("Meeting with prof", "14:00", "");
        list.add(a1);
        list.add(a2);
        list.add(a3);
        assertEquals(list.get(0).getAction(), day.getAppointmentList().get(0).getAction());
        assertEquals(list.get(1).getAction(), day.getAppointmentList().get(1).getAction());
        assertEquals(list.get(2).getAction(), day.getAppointmentList().get(2).getAction());
        assertEquals(list.get(0).getTime(), day.getAppointmentList().get(0).getTime());
        assertEquals(list.get(1).getTime(), day.getAppointmentList().get(1).getTime());
        assertEquals(list.get(2).getTime(), day.getAppointmentList().get(2).getTime());
        assertEquals(list.get(0).getLocation(), day.getAppointmentList().get(0).getLocation());
        assertEquals(list.get(1).getLocation(), day.getAppointmentList().get(1).getLocation());
        assertEquals(list.get(2).getLocation(), day.getAppointmentList().get(2).getLocation());
    }

    @Test
    public void testGetToDoList() {
        ArrayList<MultipleElementsTask> list = new ArrayList<MultipleElementsTask>();
        MultipleElementsTask a1 = new ToDo ("Dinner with mom", "8:00", "213 Infield Dr", day.getMultipleElementsList().get("todos"));
        MultipleElementsTask a2 = new ToDo("Physiotherapy", "10:00", "34 Lake St", day.getMultipleElementsList().get("todos"));
        MultipleElementsTask a3 = new ToDo("Meeting with prof", "14:00", "", day.getMultipleElementsList().get("todos"));
        list.add(a1);
        list.add(a2);
        list.add(a3);
        day.addToDo("Dinner with mom", "8:00", "213 Infield Dr");
        day.addToDo("Physiotherapy", "10:00", "34 Lake St");
        day.addToDo("Meeting with prof", "14:00", "");
        assertEquals(list.get(0).getAction(), day.getToDoList().get(0).getAction());
        assertEquals(list.get(1).getAction(), day.getToDoList().get(1).getAction());
        assertEquals(list.get(2).getAction(), day.getToDoList().get(2).getAction());
        assertEquals(list.get(0).getTime(), day.getToDoList().get(0).getTime());
        assertEquals(list.get(1).getTime(), day.getToDoList().get(1).getTime());
        assertEquals(list.get(2).getTime(), day.getToDoList().get(2).getTime());
        assertEquals(list.get(0).getLocation(), day.getToDoList().get(0).getLocation());
        assertEquals(list.get(1).getLocation(), day.getToDoList().get(1).getLocation());
        assertEquals(list.get(2).getLocation(), day.getToDoList().get(2).getLocation());
    }

    @Test
    public void testDeleteToDo() throws InvalidTaskNumberException {
        MultipleElementsTask a1 = new ToDo("Dinner with mom", "8:00", "213 Infield Dr", day.getMultipleElementsList().get("todos"));
        MultipleElementsTask a2 = new ToDo("Physiotherapy", "10:00", "34 Lake St", day.getMultipleElementsList().get("todos"));
        MultipleElementsTask a3 = new ToDo("Meeting with prof", "14:00", "", day.getMultipleElementsList().get("todos"));
        day.addToDo("Dinner with mom", "8:00", "213 Infield Dr");
        day.addToDo("Physiotherapy", "10:00", "34 Lake St");
        day.addToDo("Meeting with prof", "14:00", "");
        day.deleteToDo(1);
        assertEquals(2, day.getToDoList().size());
        assertEquals("34 Lake St", day.getToDoList().get(0).getLocation());
        assertEquals("", day.getToDoList().get(1).getLocation());
    }

    @Test
    public void testDeleteAppointment() throws InvalidTaskNumberException {
        MultipleElementsTask a1 = new Appointment("Doctor's", "8:00", "213 Infield Dr", day.getMultipleElementsList().get("apps"));
        MultipleElementsTask a2 = new Appointment("Physiotherapy", "10:00", "34 Lake St", day.getMultipleElementsList().get("apps"));
        MultipleElementsTask a3 = new Appointment("Meeting with prof", "14:00", "", day.getMultipleElementsList().get("apps"));
        day.addAppointment("Doctor's", "8:00", "213 Infield Dr");
        day.addAppointment("Physiotherapy", "10:00", "34 Lake St");
        day.addAppointment("Meeting with prof", "14:00", "");
        day.deleteAppointment(1);
        assertEquals(2, day.getAppointmentList().size());
        assertEquals("34 Lake St", day.getAppointmentList().get(0).getLocation());
        assertEquals("", day.getAppointmentList().get(1).getLocation());
    }

    @Test
    public void testDeleteAccomplishment() throws InvalidTaskNumberException {
        SingleElementTask a1 = new Accomplishment("Became vegetarian",  day.getSingleElementList().get("accs"));
        SingleElementTask a2 = new Accomplishment("Passed all my courses", day.getSingleElementList().get("accs"));
        SingleElementTask a3 = new Accomplishment("Climbed Mount Everest", day.getSingleElementList().get("accs"));
        day.addAchievement("Became vegetarian");
        day.addAchievement("Passed all my courses");
        day.addAchievement("Climbed Mount Everest");
        day.deleteAchievement(1);
        assertEquals(2, day.getAchievementList().size());
        assertEquals("Passed all my courses", day.getAchievementList().get(0).getAction());
        assertEquals("Climbed Mount Everest", day.getAchievementList().get(1).getAction());
    }

    @Test
    public void testAddEmptyAchievement() {
        day.addAchievement("");
        assertEquals(0, day.getAchievementList().size());
    }

    @Test
    public void testAddEmptyToDo() {
        day.addToDo("", "8:00", "23 Infield Dr");
        assertEquals(0, day.getToDoList().size());
    }

    @Test
    public void testAddEmptyAppointment() {
        day.addAppointment("", "8:00", "23 Infield Dr");
        assertEquals(0, day.getToDoList().size());
    }

    @Test
    public void testAddAchievementStartWrong() {
        day.addAchievement("$% Ate sauerkraut");
        day.addAchievement("$^ Ate pickles");
        assertEquals(0, day.getAchievementList().size());
    }

    @Test
    public void testAddAppointmentStartWrong() {
        day.addAppointment("$% Meeting with Laura", "4:00", "Home");
        day.addAppointment("$* Dinner with mom", "8:00", "The Eatery");
        assertEquals(0, day.getAppointmentList().size());
    }

    @Test
    public void testAddToDoStartWrong() {
        day.addToDo("$^ Meeting with Laura", "4:00", "Home");
        day.addToDo("$* Dinner with mom", "8:00", "The Eatery");
        assertEquals(0, day.getToDoList().size());
    }

    @Test
    public void testAddToDoEmptyTime() {
        day.addToDo("Meeting with Laura", "", "Home");
        assertEquals(1, day.getToDoList().size());
    }

    @Test
    public void testAddToDoTimeInvalidFormat() {
        day.addToDo("Meeting with Laura", "32:00", "Home");
        assertEquals(0, day.getToDoList().size());
    }

    @Test
    public void testAddAppointmentEmptyTime() {
        day.addToDo("Meeting with Laura", "", "Home");
        assertEquals(0, day.getAppointmentList().size());
    }

    @Test
    public void testAddAppointmentTimeInvalidFormat() {
        day.addToDo("Meeting with Laura", "32:00", "Home");
        assertEquals(0, day.getAppointmentList().size());
    }

    @Test
    public void testDeleteWrongAppointmentNumber() {
        day.addAppointment("Doctor's", "8:00", "23 Infield DR");
        day.deleteAchievement(2);
        assertEquals(1, day.getAppointmentList().size());
    }

    @Test
    public void testDeleteWrongTaskNumber() {
        day.addToDo("Doctor's", "8:00", "23 Infield DR");
        day.deleteAchievement(2);
        assertEquals(1, day.getToDoList().size());
    }


    @Test
    public void testDeleteWrongAchievementNumber() {
        day.addAchievement("Finished my first CPSC210 project");
        day.deleteAchievement(2);
        assertEquals(1, day.getAchievementList().size());
    }
}
