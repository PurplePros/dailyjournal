package elements;

import java.util.*;

public class AchievedTask implements GeneralTask {

    private String statement;

    public AchievedTask(String statement) {
        this.statement = statement;
    }

    // EFFECT: returns the achievement
    public String getAction() {
        return statement;
    }

}
