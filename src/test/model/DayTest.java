package model;

import model.exception.InvalidFormatException;
import model.exception.InvalidTaskDescriptionException;
import model.exception.InvalidTimeFormatException;
import org.omg.CORBA.DynAnyPackage.Invalid;
import ui.Menu;

import model.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DayTest {

    private Day day;

    @BeforeEach
    public void runBefore() throws IOException {
        ToDoList td = new ToDoList();
        AccomplishmentList al = new AccomplishmentList();
        AppointmentList apl = new AppointmentList();
        day = new Day("JournalLog", td, al, apl);
        td.setObserver(day);
        al.setObserver(day);
        apl.setObserver(day);
    }


    @Test
    public void testGetAchievementList() {
        try {
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
        } catch(InvalidTaskDescriptionException e) {
            fail();
        }

    }

    @Test
    public void testGetAppointmentList() {
        try {
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
        } catch(InvalidTaskDescriptionException e) {
            fail();
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testGetToDoList() throws InvalidFormatException {
        try {
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
        } catch(InvalidFormatException e) {
            fail();
        }
    }

    @Test
    public void testDeleteToDo() {
        try {
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
        } catch(InvalidTaskDescriptionException e) {
            fail();
        } catch(InvalidTimeFormatException e) {
            fail();
        } catch(InvalidTaskNumberException e) {
            fail();
        }
    }

    @Test
    public void testDeleteAppointment() {
        try {
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
        } catch(InvalidTaskNumberException e) {
            fail();
        } catch(InvalidTaskDescriptionException e) {
            fail();
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testDeleteAccomplishment() {
        try {
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
        } catch(InvalidTaskDescriptionException e) {
            fail();
        } catch(InvalidTaskNumberException e) {
            fail();
        }
    }

    @Test
    public void testAddEmptyAchievement() {
        try {
            day.addAchievement("");
            fail();
        } catch(InvalidTaskDescriptionException e) {
            assertEquals(0, day.getAchievementList().size());
        }
    }

    @Test
    public void testAddEmptyToDo() {
        try {
            day.addToDo("", "8:00", "23 Infield Dr");
            fail();
        } catch(InvalidTaskDescriptionException e) {
            assertEquals(0, day.getToDoList().size());
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testAddEmptyAppointment() {
        try {
            day.addAppointment("", "8:00", "23 Infield Dr");
            fail();
        } catch(InvalidTaskDescriptionException e) {
            assertEquals(0, day.getToDoList().size());
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testAddAchievementStartWrong() {
        try {
            day.addAchievement("$% Ate sauerkraut");
            day.addAchievement("$^ Ate pickles");
            fail();
        } catch(InvalidTaskDescriptionException e) {
            assertEquals(0, day.getAchievementList().size());
        }
    }

    @Test
    public void testAddAppointmentStartWrong() {
        try {
            day.addAppointment("$% Meeting with Laura", "4:00", "Home");
            day.addAppointment("$* Dinner with mom", "8:00", "The Eatery");
        } catch(InvalidTaskDescriptionException exception) {
            assertEquals(0, day.getAppointmentList().size());
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testAddToDoStartWrong() {
        try {
            day.addToDo("$^ Meeting with Laura", "4:00", "Home");
            day.addToDo("$* Dinner with mom", "8:00", "The Eatery");
            fail();
        } catch(InvalidTaskDescriptionException e) {
            assertEquals(0, day.getToDoList().size());
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testAddToDoEmptyTime() {
        try {
            day.addToDo("Meeting with Laura", "", "Home");
            assertEquals(1, day.getToDoList().size());
        } catch(InvalidTaskDescriptionException e) {
            fail();
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }

    @Test
    public void testAddToDoTimeInvalidFormat() {
        try {
            day.addToDo("Meeting with Laura", "32:00", "Home");
            fail();
        } catch(InvalidTimeFormatException e) {
            assertEquals(0, day.getToDoList().size());
        } catch(InvalidTaskDescriptionException e) {
            fail();
        }
    }

    @Test
    public void testAddAppointmentEmptyTime() {
        try {
            day.addToDo("Meeting with Laura", "", "Home");
            assertEquals(0, day.getAppointmentList().size());
        } catch(InvalidFormatException e) {
            fail();
        }
    }

    @Test
    public void testAddAppointmentTimeInvalidFormat() {
        try {
            day.addToDo("Meeting with Laura", "32:00", "Home");
            fail();
        } catch(InvalidTimeFormatException e) {
            assertEquals(0, day.getAppointmentList().size());
        } catch(InvalidTaskDescriptionException e) {
            fail();
        }
    }

    @Test
    public void testDeleteWrongAppointmentNumber() {
        try {
            day.addAppointment("Doctor's", "8:00", "23 Infield DR");
            day.deleteAchievement(2);
        } catch(InvalidTaskNumberException e) {
            assertEquals(1, day.getAppointmentList().size());
        } catch(InvalidFormatException e) {
            fail();
        }
    }

    @Test
    public void testDeleteWrongTaskNumber() {
        try {
            day.addToDo("Doctor's", "8:00", "23 Infield DR");
            day.deleteAchievement(2);
            fail();
        } catch(InvalidTaskNumberException e) {
            assertEquals(1, day.getToDoList().size());
        } catch(InvalidTaskDescriptionException e) {
            fail();
        } catch(InvalidTimeFormatException e) {
            fail();
        }
    }


    @Test
    public void testDeleteWrongAchievementNumber() {
        try {
            day.addAchievement("Finished my first CPSC210 project");
            day.deleteAchievement(2);
            fail();
        } catch(InvalidTaskNumberException e) {
            assertEquals(1, day.getAchievementList().size());
        } catch(InvalidTaskDescriptionException e) {
            fail();
        }
    }
}
