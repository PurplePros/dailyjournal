package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileProcessor {

    private ToDoList toDoList;
    private AccomplishmentList accomplishedList;
    private AppointmentList appointmentList;

    private String date;

    public FileProcessor(String date) {
        this.date = date;
        toDoList = new ToDoList();
        accomplishedList = new AccomplishmentList();
        appointmentList = new AppointmentList();
    }

    // MODIFY: this
    // EFFECT: prints current appointments, to-dos, and achievements into text file
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/" + date + ".txt", "UTF-8");
        //ArrayList<MultipleElementsTask> appointments = toDoList;
        //ArrayList<MultipleElementsTask> todos = multipleList;
        //ArrayList<SingleElementTask> accomplishments = singleList;
        for (MultipleElementsTask t : appointmentList.getTasks()) {
            writer.println("$^ " + t.getAction() + " " + t.getTime() + " " + putAtInFront(t.getLocation()));
        }
        for (MultipleElementsTask t : toDoList.getTasks()) {
            writer.println("$% " + t.getAction() + " " + t.getTime() + " " + putAtInFront(t.getLocation()));
        }
        for (MultipleElementsTask t : accomplishedList.getList().getTasks()) {
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
        Appointment t = new Appointment(taskBuilder[0].trim(), taskBuilder[1].trim(), taskBuilder[2].trim(),
                appointmentList);
        appointmentList.addTask(t);
    }

    // MODIFY: this
    // EFFECT: extracts accomplishment information from text file line and adds a new accomplishment to list
    public void loadAccomplishment(ArrayList<String> line) {
        String [] taskBuilder = {"", "", ""};
        for (int i = 1; i < line.size(); i++) {
            taskBuilder[0] = taskBuilder[0] + line.get(i) + " ";
        }
        Accomplishment t = new Accomplishment(taskBuilder[0].trim(), accomplishedList);
        accomplishedList.addTask(t.getTask());
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
        ToDo t = new ToDo(taskBuilder[0].trim(), taskBuilder[1].trim(), taskBuilder[2].trim(),
                toDoList);
        toDoList.addTask(t);
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

    public ToDoList getToDoList() {
        return toDoList;
    }

    public AppointmentList getAppointmentList() {
        return appointmentList;
    }

    public AccomplishmentList getAccomplishmentList() {
        return accomplishedList;
    }
}
