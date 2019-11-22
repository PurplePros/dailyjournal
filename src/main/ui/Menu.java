package ui;

import model.*;
import model.exception.InvalidFormatException;
import model.exception.InvalidTaskDescriptionException;
import model.exception.InvalidTaskNumberException;

import java.io.IOException;
import java.util.*;

public class Menu {

    private FileProcessor processor;
    private String date;
    private Day journalDay;

    // EFFECT: runs user-specified menu with this date
    public Menu(String date) throws IOException {
        this.date = date;
        processor = new FileProcessor(date);
        processor.load();
        ToDoList td = processor.getToDoList();
        AccomplishmentList ac = processor.getAccomplishmentList();
        AppointmentList ap = processor.getAppointmentList();
        journalDay = new Day(date, td, ac, ap);
        td.setObserver(journalDay);
        ac.setObserver(journalDay);
        ap.setObserver(journalDay);
        //runSelectionMenu();
    }

    public FileProcessor getProcessor() {
        return processor;
    }

    public Day getDay() {
        return journalDay;
    }

    public void takeInputAccomplishment(String accomplishment) throws InvalidTaskDescriptionException {
        journalDay.addAchievement(accomplishment);
    }

    public void takeInputToDo(String action, String time, String location) throws InvalidFormatException {
        journalDay.addToDo(action, time, location);
    }

    public void removeToDo(int index) throws InvalidTaskNumberException {
        journalDay.deleteToDo(index);
    }

    public void removeAppointment(int index) throws InvalidTaskNumberException {
        journalDay.deleteAppointment(index);
    }

    public void removeAccomplishment(int index) throws InvalidTaskNumberException {
        journalDay.deleteAchievement(index);
    }

    // MODIFY: this
    // EFFECT: takes in user input and executes next steps
    // REQUIRE: user input must match one of the listed options
    public void takeInputAppointments(String action, String time, String location) throws InvalidFormatException {
        journalDay.addAppointment(action, time, location);
    }

    public ArrayList<String> toStringMultipleList(ArrayList<MultipleElementsTask> list) {
        int i = 1;
        ArrayList<String> printedList = new ArrayList<>();
        for (MultipleElementsTask t : list) {
            printedList.add("(" + i + ") ");
            printedList.add(t.getAction() + " @ " + t.getTime());
            printedList.add("Location: " + t.getLocation());
            i++;
        }
        return printedList;
    }

    public ArrayList<String> toStringSingleList(ArrayList<MultipleElementsTask> list) {
        int i = 1;
        ArrayList<String> printedList = new ArrayList<>();
        for (MultipleElementsTask t : list) {
            printedList.add("(" + i + ") ");
            printedList.add(t.getAction());
            i++;
        }
        return printedList;
    }
}