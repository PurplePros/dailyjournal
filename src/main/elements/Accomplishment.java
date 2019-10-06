package elements;

public class Accomplishment extends GeneralTask {

    private String achievedTask;

    // EFFECTS: creates a new accomplishment
    public Accomplishment(String achievedTask, String time, String location) {
        super(achievedTask, "", "");
    }

}
