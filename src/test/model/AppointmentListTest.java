package model;

import model.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppointmentListTest {

    private AppointmentList appointmentList;

    @BeforeEach
    public void runBefore() {
        appointmentList = new AppointmentList();
    }

    @Test
    public void testAddOneAppointment() {
        Appointment t = new Appointment("Physiotherapy", "1:00", "45 Potato St", appointmentList);
        appointmentList.addTask(t);
        assertEquals(1, appointmentList.getTasks().size());
    }

    @Test
    public void testAddLotsSameAppointment() {
        Appointment t = new Appointment("Doctor's", "10:30", "Doctor's Office", appointmentList);
        for (int i = 0; i < 50; i++) {
            appointmentList.addTask(t);
        }
        assertEquals(1, appointmentList.getTasks().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAppointmentRemovedEmpty() throws InvalidTaskNumberException {
        Appointment t = new Appointment("Doctor's", "10:30", "Doctor's Office", appointmentList);
        appointmentList.addTask(t);
        appointmentList.deleteTask(0);
        assertEquals(0, appointmentList.getTasks().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllAppointmentsRemoved() throws InvalidTaskNumberException {
        Appointment t1 = new Appointment("Physiotherapy", "1:00", "45 Potato St", appointmentList);
        Appointment t2 = new Appointment("Doctor's", "10:30", "Doctor's Office", appointmentList);
        Appointment t3 = new Appointment("Meeting with boss", "1:00", "", appointmentList);

        appointmentList.addTask(t1);
        appointmentList.addTask(t2);
        appointmentList.addTask(t3);

        assertEquals(3, appointmentList.getTasks().size());
        appointmentList.deleteTask(0);
        appointmentList.deleteTask(0);
        appointmentList.deleteTask(0);
        assertEquals(0, appointmentList.getTasks().size());
    }

    @Test
    // test case: given an index that's not valid
    public void testInvalidTaskIndex() {
        Appointment appointment = new Appointment("Work interview", "5:00", "Office", appointmentList);
        appointmentList.addTask(appointment);
        try {
            appointmentList.deleteTask(2);
            fail("Wrong index!");
        } catch (InvalidTaskNumberException e) {

        }
        assertEquals(1, appointmentList.getTasks().size());
    }

    @Test
    // test case: given an index that's valid
    public void testValidTaskIndex() {
        Appointment appointment = new Appointment("Work interview", "5:00", "Office", appointmentList);
        appointmentList.addTask(appointment);
        try {
            appointmentList.deleteTask(0);
        } catch (InvalidTaskNumberException e) {
            fail("Right index!");
        }
        assertEquals(0, appointmentList.getTasks().size());
    }

}
