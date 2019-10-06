import elements.Appointment;
import elements.AppointmentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentListTest {

    private AppointmentList appointmentList;

    @BeforeEach
    public void runBefore() {
        appointmentList = new AppointmentList();
    }

    @Test
    public void testAddOneAppointment() {
        Appointment t = new Appointment("Physiotherapy", "1:00", "45 Potato St");
        appointmentList.addTask(t);
        assertEquals(1, appointmentList.getList().size());
    }

    @Test
    public void testAddLotsAppointment() {
        Appointment t = new Appointment("Doctor's", "10:30", "Doctor's Office");
        for (int i = 0; i < 50; i++) {
            appointmentList.addTask(t);
        }
        assertEquals(50, appointmentList.getList().size());
    }

    @Test
    // test case: one achievement deleted resulting in empty list
    public void testOneAppointmentRemovedEmpty() {
        Appointment t = new Appointment("Doctor's", "10:30", "Doctor's Office");
        appointmentList.addTask(t);
        appointmentList.deleteTask(0);
        assertEquals(0, appointmentList.getList().size());
    }

    @Test
    // test case: all test cases removed
    public void testAllAppointmentsRemoved() {
        Appointment t = new Appointment("Physiotherapy", "1:00", "45 Potato St");
        for (int i = 0; i < 100; i++) {
            appointmentList.addTask(t);
        }
        for (int i = 99; i >= 0; i--) {
            appointmentList.deleteTask(i);
        }
        assertEquals(0, appointmentList.getList().size());
    }

    @Test
    // test case: some test cases removed
    public void testSomeAppointmentsRemoved() {
        Appointment t = new Appointment("Meeting with boss", "1:00", "");
        for (int i = 0; i < 100; i++) {
            appointmentList.addTask(t);
        }
        for (int i = 50; i > 0; i--) {
            appointmentList.deleteTask(i);
        }
        assertEquals(50, appointmentList.getList().size());
    }

}
