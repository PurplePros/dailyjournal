package elements;

public class Task {

    // Fields
    private String action;
    private String time;

    // Constructor
    public Task(String action, String time) {
        this.action = action;
        this.time = time;
    }

    // EFFECT: returns task action
    public String getAction() {
        return action;
    }

    // EFFECT: returns the time of task
    public String getTime() {
        return time;
    }
}
