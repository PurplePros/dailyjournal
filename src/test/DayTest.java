import elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.java2d.loops.FillRect;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {

    private Day day;

    @BeforeEach
    public void runBefore() throws IOException {
        day = new Day("JournalLog");
    }

    @Test
    public void testLoadAndSave() {

    }

    @Test
    public void testSplitOnSpace() {
        String testString = "Hello my name is Bob.";
        ArrayList<String> splitString = new ArrayList<>();
        splitString.add("Hello");
        splitString.add("my");
        splitString.add("name");
        splitString.add("is");
        splitString.add("Bob.");
        assertEquals(splitString, Day.splitOnSpace(testString));
    }

    @Test
    public void testPutAtInFront() {
        String testString = "Hello my name is Bob.";
        assertEquals("@Hello @my @name @is @Bob.", Day.putAtInFront(testString));
    }

    @Test
    public void testGetAchievementList() {
        ArrayList<GeneralTask> list = new ArrayList<GeneralTask>();
        GeneralTask a1 = new Accomplishment("Became vegetarian", "", "");
        GeneralTask a2 = new Accomplishment("Passed all my courses", "", "");
        GeneralTask a3 = new Accomplishment("Climbed Mount Everest", "", "");
        list.add(a1);
        list.add(a2);
        list.add(a3);
        day.addAchievement("Became vegetarian");
        day.addAchievement("Passed all my courses");
        day.addAchievement("Climbed Mount Everest");
        assertEquals(list.get(0).getAction(), day.getAchievementList().get(0).getAction());
        assertEquals(list.get(1).getAction(), day.getAchievementList().get(1).getAction());
        assertEquals(list.get(2).getAction(), day.getAchievementList().get(2).getAction());
    }

    @Test
    public void testGetAppointmentList() {
        ArrayList<GeneralTask> list = new ArrayList<GeneralTask>();
        GeneralTask a1 = new Appointment("Doctor's", "8:00", "213 Infield Dr");
        GeneralTask a2 = new Appointment("Physiotherapy", "10:00", "34 Lake St");
        GeneralTask a3 = new Appointment("Meeting with prof", "14:00", "");
        list.add(a1);
        list.add(a2);
        list.add(a3);
        day.addAppointment("Doctor's", "8:00", "213 Infield Dr");
        day.addAppointment("Physiotherapy", "10:00", "34 Lake St");
        day.addAppointment("Meeting with prof", "14:00", "");
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
        ArrayList<GeneralTask> list = new ArrayList<GeneralTask>();
        GeneralTask a1 = new Task("Dinner with mom", "8:00", "213 Infield Dr");
        GeneralTask a2 = new Task("Physiotherapy", "10:00", "34 Lake St");
        GeneralTask a3 = new Task("Meeting with prof", "14:00", "");
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
    public void testDeleteToDo() {
        GeneralTask a1 = new Task("Dinner with mom", "8:00", "213 Infield Dr");
        GeneralTask a2 = new Task("Physiotherapy", "10:00", "34 Lake St");
        GeneralTask a3 = new Task("Meeting with prof", "14:00", "");
        day.addToDo("Dinner with mom", "8:00", "213 Infield Dr");
        day.addToDo("Physiotherapy", "10:00", "34 Lake St");
        day.addToDo("Meeting with prof", "14:00", "");
        day.deleteToDo(1);
        assertEquals(2, day.getToDoList().size());
        assertEquals("34 Lake St", day.getToDoList().get(0).getLocation());
        assertEquals("", day.getToDoList().get(1).getLocation());
    }

    @Test
    public void testDeleteAppointment() {
        GeneralTask a1 = new Appointment("Doctor's", "8:00", "213 Infield Dr");
        GeneralTask a2 = new Appointment("Physiotherapy", "10:00", "34 Lake St");
        GeneralTask a3 = new Appointment("Meeting with prof", "14:00", "");
        day.addAppointment("Doctor's", "8:00", "213 Infield Dr");
        day.addAppointment("Physiotherapy", "10:00", "34 Lake St");
        day.addAppointment("Meeting with prof", "14:00", "");
        day.deleteAppointment(1);
        assertEquals(2, day.getAppointmentList().size());
        assertEquals("34 Lake St", day.getAppointmentList().get(0).getLocation());
        assertEquals("", day.getAppointmentList().get(1).getLocation());
    }

    @Test
    public void testDeleteAccomplishment() {
        GeneralTask a1 = new Accomplishment("Became vegetarian", "", "");
        GeneralTask a2 = new Accomplishment("Passed all my courses", "", "");
        GeneralTask a3 = new Accomplishment("Climbed Mount Everest", "", "");
        day.addAchievement("Became vegetarian");
        day.addAchievement("Passed all my courses");
        day.addAchievement("Climbed Mount Everest");
        day.deleteAchievement(1);
        assertEquals(2, day.getAchievementList().size());
        assertEquals("Passed all my courses", day.getAchievementList().get(0).getAction());
        assertEquals("Climbed Mount Everest", day.getAchievementList().get(1).getAction());
    }

    @Test
    public void testLoadAppointment() {
        String log = "$% Doctor's 8:00 @238 @Middlefield @Road";
        ArrayList<String> testString = day.splitOnSpace(log);
        day.loadAppointment(testString);
        assertEquals("Doctor's", day.getAppointmentList().get(0).getAction());
        assertEquals("8:00", day.getAppointmentList().get(0).getTime());
        assertEquals("238 Middlefield Road", day.getAppointmentList().get(0).getLocation());
    }

    @Test
    public void testLoadAchievement() {
        String log = "$* Had a wonderful birthday";
        ArrayList<String> testString = day.splitOnSpace(log);
        day.loadAccomplishment(testString);
        assertEquals("Had a wonderful birthday", day.getAchievementList().get(0).getAction());
    }

    @Test
    public void testLoadToDo() {
        String log = "$% Wake up 8:00 @The @Birdcoop";
        ArrayList<String> testString = day.splitOnSpace(log);
        day.loadToDo(testString);
        assertEquals("Wake up", day.getToDoList().get(0).getAction());
        assertEquals("8:00", day.getToDoList().get(0).getTime());
        assertEquals("The Birdcoop", day.getToDoList().get(0).getLocation());
    }

    @Test
    public void testLoadandSave() throws IOException {
        day.load();
        day.addAppointment("Doctor's", "8:00", "238 Middlefield Road");
        day.save();
        assertEquals("Doctor's", day.getAppointmentList().get(0).getAction());
        assertEquals("8:00", day.getAppointmentList().get(0).getTime());
        assertEquals("238 Middlefield Road", day.getAppointmentList().get(0).getLocation());
        assertEquals("Had a wonderful birthday", day.getAchievementList().get(0).getAction());
        assertEquals("Wake up", day.getToDoList().get(0).getAction());
        assertEquals("8:00", day.getToDoList().get(0).getTime());
        assertEquals("The Birdcoop", day.getToDoList().get(0).getLocation());
    }
}
