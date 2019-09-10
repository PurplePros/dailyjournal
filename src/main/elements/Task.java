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

    // Methods

    // EFFECT: changes the to-do action of a task
    public void changeTask(String newAction) {
        action = newAction;
    }

    // EFFECT: changes the time of a task
    public void changeTime(String newTime) {
        time = newTime;
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
