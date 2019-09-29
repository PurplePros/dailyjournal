package elements;

public class Task implements GeneralTask {

    private String action;
    private String time;

    // EFFECT: task created with given action and time
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
