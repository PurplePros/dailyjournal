package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    private Task t;

    @BeforeEach
    public void runBefore() {
        t = new Task("Help mom with dishes", "8:30", "28 Bridlepath Drive", null);
    }

    @Test
    public void testReturnAction() {
        assertEquals("Help mom with dishes", t.getAction());
    }

    @Test
    public void testReturnTime() {
        assertEquals("8:30", t.getTime());
    }

    @Test
    public void testReturnLocation() {
        assertEquals("28 Bridlepath Drive", t.getLocation());
    }
}
