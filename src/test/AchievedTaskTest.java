import elements.AchievedTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AchievedTaskTest {

    private AchievedTask at;

    @BeforeEach
    public void runBefore(){
        at = new AchievedTask("Ate sauerkraut for the first time.");
    }

    @Test
    public void testGetAchievement(){
        assertEquals("Ate sauerkraut for the first time.", at.getAction());
    }
}

