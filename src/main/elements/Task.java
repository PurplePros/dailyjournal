package elements;

public class Task extends GeneralTask {

    private String time;

    // EFFECT: creates a new task
    public Task(String action, String time, String location) {
        super(action, time, location);
    }

}
