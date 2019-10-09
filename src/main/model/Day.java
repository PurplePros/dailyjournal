package model;

import model.exception.InvalidFormatException;
import model.exception.InvalidTaskDescriptionException;
import model.exception.InvalidTaskNumberException;
import model.exception.InvalidTimeFormatException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day {

    private ToDoList toDoList;
    private AccomplishmentList accomplishedList;
    private AppointmentList appointmentList;
    private String date;

    // EFFECT: creates a new day with empty lists
    public Day(String date) throws IOException {
        toDoList = new ToDoList();
        accomplishedList = new AccomplishmentList();
        appointmentList = new AppointmentList();
        this.date = date;
    }

    // MODIFY: this
    // EFFECT: prints current appointments, to-dos, and achievements into text file
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/" + date + ".txt", "UTF-8");
        ArrayList<GeneralTask> appointments = appointmentList.getList();
        ArrayList<GeneralTask> todos = toDoList.getList();
        ArrayList<GeneralTask> accomplishments = accomplishedList.getList();
        for (GeneralTask t : appointments) {
            writer.println("$^ " + t.getAction() + " " + t.getTime() + " " + putAtInFront(t.getLocation()));
        }
        for (GeneralTask t : todos) {
            writer.println("$% " + t.getAction() + " " + t.getTime() + " " + putAtInFront(t.getLocation()));
        }
        for (GeneralTask t : accomplishments) {
            writer.println("$* " + t.getAction());
        }
        writer.close();
    }

    // MODIFY: this
    // EFFECT: loads appointments, accomplishments, and to-dos from text file into the lists
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("data/" + date + ".txt"));
        for (String line : lines) {
            ArrayList<String> words = splitOnSpace(line);
            if (words.get(0).equals("$^")) {
                loadAppointment(words);
            } else if (words.get(0).equals("$*")) {
                loadAccomplishment(words);
            } else {
                loadToDo(words);
            }
        }
    }

    // MODIFY: this
    // EFFECT: extracts appointment information from text file line and adds a new appointment to list
    public void loadAppointment(ArrayList<String> line) {
        String [] taskBuilder = {"", "", ""};
        for (int i = 1; i < line.size(); i++) {
            if (line.get(i).matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
                taskBuilder[1] = line.get(i);
            } else if (line.get(i).contains("@")) {
                taskBuilder[2] += line.get(i).substring(1) + " ";
            } else {
                taskBuilder[0] += line.get(i) + " ";
            }
        }
        Appointment t = new Appointment(taskBuilder[0].trim(), taskBuilder[1].trim(), taskBuilder[2].trim());
        appointmentList.getList().add(t);
    }

    // MODIFY: this
    // EFFECT: extracts accomplishment information from text file line and adds a new accomplishment to list
    public void loadAccomplishment(ArrayList<String> line) {
        String [] taskBuilder = {"", "", ""};
        for (int i = 1; i < line.size(); i++) {
            taskBuilder[0] = taskBuilder[0] + line.get(i) + " ";
        }
        Accomplishment t = new Accomplishment(taskBuilder[0].trim(), "", "");
        accomplishedList.getList().add(t);
    }

    // MODIFY: this
    // EFFECT: extracts to-do information from text file line and adds a new to-do task to list
    public void loadToDo(ArrayList<String> line) {
        String [] taskBuilder = {"", "", ""};
        for (int i = 1; i < line.size(); i++) {
            if (line.get(i).matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
                taskBuilder[1] = line.get(i);
            } else if (line.get(i).contains("@")) {
                taskBuilder[2] += line.get(i).substring(1) + " ";
            } else {
                taskBuilder[0] += line.get(i) + " ";
            }
        }
        Task t = new Task(taskBuilder[0].trim(), taskBuilder[1].trim(), taskBuilder[2].trim());
        toDoList.getList().add(t);
    }

    // MODIFY: splits the given string into separate words
    // EFFECT: returns split up words
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECT: puts a @ in front of every single word of given string
    public static String putAtInFront(String location) {
        ArrayList<String> words = splitOnSpace(location);
        String output = "";
        for (String w : words) {
            output += "@" + w + " ";
        }
        return output.trim();
    }

    // EFFECT: returns accomplishments
    public ArrayList<GeneralTask> getAchievementList() {
        return accomplishedList.getList();
    }

    // EFFECT: returns to-do list
    public ArrayList<GeneralTask> getToDoList() {
        return toDoList.getList();
    }

    // EFFECT: returns appointments
    public ArrayList<GeneralTask> getAppointmentList() {
        return appointmentList.getList();
    }

    // MODIFY: this
    // EFFECT: takes user's information about new achievement and adds to list
    // REQUIRE: accomplishment must be a non-empty string
    //          accomplishment must not start with the string $% or $^
    public void addAchievement(String accomplishment) {
        try {
            if (accomplishment.length() > 0 && !accomplishment.startsWith("$%") && !accomplishment.startsWith("$^")) {
                GeneralTask action = new Accomplishment(accomplishment, "", "");
                accomplishedList.addTask(action);
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
            accomplishedList.deleteTask(index - 1);
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
                GeneralTask todo = new Task(action, time, location);
                toDoList.addTask(todo);
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
            toDoList.deleteTask(index - 1);
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
                GeneralTask todo = new Appointment(action, time, location);
                appointmentList.addTask(todo);
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
            appointmentList.deleteTask(index - 1);
        } catch (InvalidTaskNumberException e) {
            System.out.println("Invalid task number!");
            System.out.println("Please enter a number between 1 and " + getAppointmentList().size());
        }

    }

    // EFFECT: returns true if time is in valid 24-hr format
    protected boolean isRightTimeFormat(String time) {
        return time.matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
    }
}
