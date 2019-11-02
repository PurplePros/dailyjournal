package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {

    private Appointment t;

    @BeforeEach
    public void runBefore() {
        t = new Appointment("Doctor's appointment", "8:30", "", null);
    }

    @Test
    public void testReturnAction() {
        assertEquals("Doctor's appointment", t.getAction());
    }

    @Test
    public void testReturnTime() {
        assertEquals("8:30", t.getTime());
    }

    @Test
    public void testReturnLocation() {
        assertEquals("", t.getLocation());
    }

}
