package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccomplishmentTest {

    private Accomplishment t;

    @BeforeEach
    public void runBefore() {
        SingleElementList sl = new SingleElementList();
        t = new Accomplishment("Doctor's appointment", sl);
    }

    @Test
    public void testReturnAction() {
        assertEquals("Doctor's appointment", t.getTask().getAction());
    }

    @Test
    public void testReturnTime() {
        assertEquals("", t.getTask().getTime());
    }

    @Test
    public void testReturnLocation() {
        assertEquals("", t.getTask().getLocation());
    }
}
