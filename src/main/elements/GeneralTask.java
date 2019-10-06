package elements;

public abstract class GeneralTask {

    protected String action;
    protected String time;
    protected String location;

    // EFFECT: creates a new general task
    public GeneralTask(String action, String time, String location) {
        this.action = action;
        this.time = time;
        this.location = location;
    }

    // EFFECT: returns the task action
    public String getAction() {
        return action;
    }

    // EFFECT: returns the time
    public String getTime() {
        return time;
    }

    // EFFECT: returns the location
    public String getLocation() {
        return location;
    }

}
