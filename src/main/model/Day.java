package model;

import model.exception.InvalidFormatException;
import model.exception.InvalidTaskDescriptionException;
import model.exception.InvalidTaskNumberException;
import model.exception.InvalidTimeFormatException;

import java.io.FileNotFoundException;
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


    // EFFECT: returns accomplishments
    public ArrayList<MultipleElementsTask> getAchievementList() {
        return singleElementList.get("accs").getList().getTasks();
    }

    public HashMap<String, SingleElementList> getSingleElementList() {
        return singleElementList;
    }

    public HashMap<String, MultipleElementsList> getMultipleElementsList() {
        return multipleElementsList;
    }

    // EFFECT: returns to-do list
    public ArrayList<MultipleElementsTask> getToDoList() {
        return multipleElementsList.get("todos").getTasks();
    }

    // EFFECT: returns appointments
    public ArrayList<MultipleElementsTask> getAppointmentList() {
        return multipleElementsList.get("apps").getTasks();
    }

    // MODIFY: this
    // EFFECT: takes user's information about new achievement and adds to list
    // REQUIRE: accomplishment must be a non-empty string
    //          accomplishment must not start with the string $% or $^
    public void addAchievement(String accomplishment) {
        try {
            if (accomplishment.length() > 0 && !accomplishment.startsWith("$%") && !accomplishment.startsWith("$^")) {
                SingleElementTask action = new Accomplishment(accomplishment, singleElementList.get("accs"));
                singleElementList.get("accs").addTask(action.getTask());
            } else {
                throw new InvalidTaskDescriptionException();
            }
        } catch (InvalidFormatException e) {
            System.out.println("Invalid format!");
        }
    }

    // MODIFY: this
    // EFFECT: takes user's information about which achievement to delete and deletes from list
    // REQUIRE: user input must be a valid achievement number
    public void deleteAchievement(int index) {
        try {
            singleElementList.get("accs").deleteTask(index - 1);
        } catch (InvalidTaskNumberException e) {
            System.out.println("Invalid task number!");
            System.out.println("Please enter a number between 1 and " + getAchievementList().size());
        }
    }

    // MODIFY: this
    // EFFECT: takes user's information about new to-do and adds to list
    // REQUIRE: task description must be a non-empty string
    //          description must not start with the strings $^ or $*
    //          if time is non-empty, time must be in valid 24-hour format
    public void addToDo(String action, String time, String location) {
        try {
            if (action.length() == 0 || action.startsWith("$*") || action.startsWith("$^")) {
                throw new InvalidTaskDescriptionException();
            } else if (time.length() > 0 && !isRightTimeFormat(time)) {
                throw new InvalidTimeFormatException();
            } else {
                MultipleElementsTask todo = new ToDo(action, time, location, multipleElementsList.get("todos"));
                multipleElementsList.get("todos").addTask(todo);
            }
        } catch (InvalidFormatException e) {
            System.out.println("Invalid Format!");
        }
    }

    // MODIFY: this
    // EFFECT: takes user's information about which to-do to delete and deletes from list
    // REQUIRE: input must be a valid task number
    public void deleteToDo(int index) {
        try {
            multipleElementsList.get("todos").deleteTask(index - 1);
        } catch (InvalidTaskNumberException e) {
            System.out.println("Invalid task number!");
            System.out.println("Please enter a number between 1 and " + getToDoList().size());
        }
    }

    // MODIFY: this
    // EFFECT: takes user's information about new appointment and adds to list
    // REQUIRE: appointment description must be a non-empty string
    //          description must not start with $% or $*
    //          appointment time must be a non-empty string and in the 24-hour format
    public void addAppointment(String action, String time, String location) {
        try {
            if (action.length() == 0 || action.startsWith("$%") || action.startsWith("$*")) {
                throw new InvalidTaskDescriptionException();
            } else if (time.length() == 0 || !isRightTimeFormat(time)) {
                throw new InvalidTimeFormatException();
            } else {
                MultipleElementsTask todo = new Appointment(action, time, location, multipleElementsList.get("apps"));
                multipleElementsList.get("apps").addTask(todo);
            }
        } catch (InvalidTaskDescriptionException e) {
            System.out.println("Invalid format!");
        } catch (InvalidTimeFormatException e) {
            System.out.println("Need a valid time.");
        }
    }

    // MODIFY: this
    // EFFECT: takes user's information about which appointment to delete and deletes from list
    // REQUIRE: user input must be a valid appointment number
    public void deleteAppointment(int index) {
        try {
            multipleElementsList.get("apps").deleteTask(index - 1);
        } catch (InvalidTaskNumberException e) {
            System.out.println("Invalid task number!");
            System.out.println("Please enter a number between 1 and " + getAppointmentList().size());
        }

    }

    // EFFECT: returns true if time is in valid 24-hr format
    protected boolean isRightTimeFormat(String time) {
        return time.matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
    }}
