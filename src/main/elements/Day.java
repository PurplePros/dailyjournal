package elements;

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

    // creates a new day with empty lists and loads information from file
    public Day(String date) throws IOException {
        toDoList = new ToDoList();
        accomplishedList = new AccomplishmentList();
        appointmentList = new AppointmentList();
        this.date = date;
    }

    // MODIFY: this
    // EFFECT: print current appointments, to-dos, and achievements into text file
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(date + ".txt", "UTF-8");
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
        List<String> lines = Files.readAllLines(Paths.get(date + ".txt"));
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
    // EFFECT: extracts information from text file line and adds a new appointment to the list with information
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
    // EFFECT: extracts information from text file line and adds a new accomplishment to the list with information
    public void loadAccomplishment(ArrayList<String> line) {
        String [] taskBuilder = {"", "", ""};
        for (int i = 1; i < line.size(); i++) {
            taskBuilder[0] = taskBuilder[0] + line.get(i) + " ";
        }
        Accomplishment t = new Accomplishment(taskBuilder[0].trim(), "", "");
        accomplishedList.getList().add(t);
    }

    // MODIFY: this
    // EFFECT: extracts information from text file line and adds a new to-do to the list with information
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
    public void addAchievement(String accomplishment) {
        GeneralTask action = new Accomplishment(accomplishment, "", "");
        accomplishedList.addTask(action);
    }

    // MODIFY: this
    // EFFECT: takes user's information about which achievement to delete and deletes from list
    public void deleteAchievement(int index)  {
        accomplishedList.deleteTask(index - 1);
    }

    // MODIFY: this
    // EFFECT: takes user's information about new to-do and adds to list
    public void addToDo(String action, String time, String location) {
        GeneralTask todo = new Task(action, time, location);
        toDoList.addTask(todo);
    }

    // MODIFY: this
    // EFFECT: takes user's information about which to-do to delete and deletes from list
    public void deleteToDo(int index)  {
        toDoList.deleteTask(index - 1);
    }

    // MODIFY: this
    // EFFECT: takes user's information about new appointment and adds to list
    public void addAppointment(String action, String time, String location) {
        GeneralTask todo = new Appointment(action, time, location);
        appointmentList.addTask(todo);
    }

    // MODIFY: this
    // EFFECT: takes user's information about which appointment to delete and deletes from list
    public void deleteAppointment(int index)  {
        appointmentList.deleteTask(index - 1);
    }


}
