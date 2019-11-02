package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileProcessorTest {

    FileProcessor fp;

    @BeforeEach
    public void runBefore() {
        fp = new FileProcessor("JournalLog");
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
        assertEquals(splitString, fp.splitOnSpace(testString));
    }

    @Test
    public void testPutAtInFront() {
        String testString = "Hello my name is Bob.";
        assertEquals("@Hello @my @name @is @Bob.", fp.putAtInFront(testString));
    }

    @Test
    public void testLoadAppointment() {
        String log = "$% Doctor's 8:00 @238 @Middlefield @Road";
        ArrayList<String> testString = fp.splitOnSpace(log);
        fp.loadAppointment(testString);
        assertEquals("Doctor's", fp.getAppointmentList().getTasks().get(0).getAction());
        assertEquals("8:00", fp.getAppointmentList().getTasks().get(0).getTime());
        assertEquals("238 Middlefield Road", fp.getAppointmentList().getTasks().get(0).getLocation());
    }

    @Test
    public void testLoadAchievement() {
        String log = "$* Had a wonderful birthday";
        ArrayList<String> testString = fp.splitOnSpace(log);
        fp.loadAccomplishment(testString);
        assertEquals("Had a wonderful birthday", fp.getAccomplishmentList().getList().getTasks().get(0).getAction());
    }

    @Test
    public void testLoadToDo() {
        String log = "$% Wake up 8:00 @The @Birdcoop";
        ArrayList<String> testString = fp.splitOnSpace(log);
        fp.loadToDo(testString);
        assertEquals("Wake up", fp.getToDoList().getTasks().get(0).getAction());
        assertEquals("8:00", fp.getToDoList().getTasks().get(0).getTime());
        assertEquals("The Birdcoop", fp.getToDoList().getTasks().get(0).getLocation());
    }

    @Test
    public void testLoadAndSave() throws IOException {
        fp.load();
        String log = "$% Doctor's 8:00 @238 @Middlefield @Road";
        ArrayList<String> testString = fp.splitOnSpace(log);
        fp.loadAppointment(testString);
        fp.save();
        assertEquals("Doctor's", fp.getAppointmentList().getTasks().get(1).getAction());
        assertEquals("8:00", fp.getAppointmentList().getTasks().get(1).getTime());
        assertEquals("238 Middlefield Road", fp.getAppointmentList().getTasks().get(1).getLocation());
        assertEquals("Physiotherapy", fp.getAppointmentList().getTasks().get(0).getAction());
        assertEquals("15:00", fp.getAppointmentList().getTasks().get(0).getTime());
        assertEquals("Physio Office", fp.getAppointmentList().getTasks().get(0).getLocation());
        assertEquals("Had a wonderful birthday", fp.getAccomplishmentList().getList().getTasks().get(0).getAction());
        assertEquals("Wake up", fp.getToDoList().getTasks().get(0).getAction());
        assertEquals("8:00", fp.getToDoList().getTasks().get(0).getTime());
        assertEquals("The Birdcoop", fp.getToDoList().getTasks().get(0).getLocation());
    }
}
