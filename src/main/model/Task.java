package model;

public class Task extends GeneralTask {

    // EFFECT: creates a new task with given description, time, and location
    // REQUIRE: task description must not start with the string $^ or $*
    //          description length > 0
    //          if time is not empty, must be in the 24-hour time format
    public Task(String action, String time, String location, GeneralList gl) {
        super(action, time, location, gl);
    }

}
