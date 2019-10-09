package model;

public class Accomplishment extends GeneralTask {

    // EFFECTS: creates a new accomplishment where time and location are null
    // REQUIRE: accomplishment description must not start with the string $*
    //          description length > 0
    public Accomplishment(String achievedTask, String time, String location) {
        super(achievedTask, "", "");
    }

}
