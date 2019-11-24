package model;

import model.exception.InvalidTaskDescriptionException;
import model.exception.InvalidTaskNumberException;
import model.exception.InvalidTimeFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day {

    private HashMap<String, SingleElementList> singleElementList;
    private HashMap<String, MultipleElementsList> multipleElementsList;

    private String date;

    // EFFECT: creates a new day with empty lists
    public Day(String date, ToDoList todos, AccomplishmentList accs, AppointmentList apps) throws IOException {
        singleElementList = new HashMap<>();
        multipleElementsList = new HashMap<>();
        this.date = date;
        multipleElementsList.put("todos", todos);
        multipleElementsList.put("apps", apps);
        singleElementList.put("accs", accs);
    }


    // EFFECT: returns accomplishment list
    public ArrayList<MultipleElementsTask> getAchievementList() {
        return singleElementList.get("accs").getList().getTasks();
    }

    // EFFECT: returns current list of entries that are of a single element type
    public HashMap<String, SingleElementList> getSingleElementList() {
        return singleElementList;
    }

    // EFFECT: returns current list of entries that are of a multiple elements type
    public HashMap<String, MultipleElementsList> getMultipleElementsList() {
        return multipleElementsList;
    }

    // EFFECT: returns to-do list
    public ArrayList<MultipleElementsTask> getToDoList() {
        return multipleElementsList.get("todos").getTasks();
    }

    // EFFECT: returns appointment list
    public ArrayList<MultipleElementsTask> getAppointmentList() {
        return multipleElementsList.get("apps").getTasks();
    }

    // MODIFY: this
    // EFFECT: creates a new accomplishment with given description and adds it to accomplishment list
    // REQUIRE: accomplishment must be a non-empty string
    //          accomplishment must not start with the string $% or $^
    public void addAchievement(String accomplishment) throws InvalidTaskDescriptionException {
        if (accomplishment.length() > 0 && !accomplishment.startsWith("$%") && !accomplishment.startsWith("$^")) {
            SingleElementTask action = new Accomplishment(accomplishment, singleElementList.get("accs"));
            singleElementList.get("accs").addTask(action.getTask());
        } else {
            throw new InvalidTaskDescriptionException();
        }
    }

    // MODIFY: this
    // EFFECT: deletes achievement at given index number from achievement list
    public void deleteAchievement(int index) throws InvalidTaskNumberException {
        singleElementList.get("accs").deleteTask(index - 1);
    }

    // MODIFY: this
    // EFFECT: creates new to-do with given description, time, and location and adds to to-do list
    // REQUIRE: task description must be a non-empty string
    //          description must not start with the strings $^ or $*
    //          if time is non-empty, time must be in valid 24-hour format
    public void addToDo(String action, String time, String location) throws InvalidTaskDescriptionException,
            InvalidTimeFormatException {
        if (action.length() == 0 || action.startsWith("$*") || action.startsWith("$^")) {
            throw new InvalidTaskDescriptionException();
        } else if (time.length() > 0 && !isRightTimeFormat(time)) {
            throw new InvalidTimeFormatException();
        } else {
            MultipleElementsTask todo = new ToDo(action, time, location, multipleElementsList.get("todos"));
            multipleElementsList.get("todos").addTask(todo);
        }
    }

    // MODIFY: this
    // EFFECT: deletes achievement at given index number from achievement list
    public void deleteToDo(int index) throws InvalidTaskNumberException {
        multipleElementsList.get("todos").deleteTask(index - 1);
    }

    // MODIFY: this
    // EFFECT: creates a new appointment with given description, time, and location and adds to appointment list
    // REQUIRE: appointment description must be a non-empty string
    //          description must not start with $% or $*
    //          appointment time must be a non-empty string and in the 24-hour format
    public void addAppointment(String action, String time, String location) throws InvalidTaskDescriptionException,
            InvalidTimeFormatException {
        if (action.length() == 0 || action.startsWith("$%") || action.startsWith("$*")) {
            throw new InvalidTaskDescriptionException();
        } else if (time.length() == 0 || !isRightTimeFormat(time)) {
            throw new InvalidTimeFormatException();
        } else {
            MultipleElementsTask todo = new Appointment(action, time, location, multipleElementsList.get("apps"));
            multipleElementsList.get("apps").addTask(todo);
        }
    }

    // MODIFY: this
    // EFFECT: takes user's information about which appointment to delete and deletes from list
    // REQUIRE: user input must be a valid appointment number
    public void deleteAppointment(int index) throws InvalidTaskNumberException {
        multipleElementsList.get("apps").deleteTask(index - 1);
    }

    // shamelessly quoting from:
    // https://stackoverflow.com/questions/7536755/regular-expression-for-matching-hhmm-time-format
    // EFFECT: returns true if time is in valid 24-hr format
    protected boolean isRightTimeFormat(String time) {
        return time.matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
    }
}
