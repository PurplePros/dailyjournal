package model;

public class Appointment extends GeneralTask {

    // EFFECT: creates a new appointment given the description, time, and location
    // REQUIRE: appointment description must not start with string $^,
    //          description length > 0
    //          time must be in the 24-hour time format
    public Appointment(String description, String time, String location, GeneralList gl) {
        super(description, time, location, gl);
    }

}
