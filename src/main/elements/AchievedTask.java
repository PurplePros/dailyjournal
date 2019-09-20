package elements;

import java.util.*;

public class AchievedTask {

    private String statement;

    public AchievedTask(String statement) {
        this.statement = statement;
    }

    // EFFECT: returns the achievement
    public String getAchievement() {
        return statement;
    }

}
