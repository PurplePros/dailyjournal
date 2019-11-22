package ui;

import model.*;
import model.exception.InvalidFormatException;
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
    }

    // EFFECT: returns file processor
    public FileProcessor getProcessor() {
        return processor;
    }

    // EFFECT: returns journalDay
    public Day getDay() {
        return journalDay;
    }

    // EFFECT: adds an achievement to current day's achievement list with given description
    // MODIFY: this
    public void takeInputAccomplishment(String accomplishment) throws InvalidFormatException {
        journalDay.addAchievement(accomplishment);
    }

    // EFFECT: adds to-do to current day's to-do list with given description, time, and location
    // MODIFY: this
    public void takeInputToDo(String action, String time, String location) throws InvalidFormatException {
        journalDay.addToDo(action, time, location);
    }

    // EFFECT: adds appointment to current day's appointment list with given description, time, and location
    // MODIFY: this
    public void takeInputAppointments(String action, String time, String location) throws InvalidFormatException {
        journalDay.addAppointment(action, time, location);
    }

    // EFFECT: removes to-do from current day's to-do list at given position
    // MODIFY: this
    public void removeToDo(int index) throws InvalidTaskNumberException {
        journalDay.deleteToDo(index);
    }

    // EFFECT: removes appointment from current day's appointment list at given position
    // MODIFY: this
    public void removeAppointment(int index) throws InvalidTaskNumberException {
        journalDay.deleteAppointment(index);
    }

    // EFFECT: removes accomplishment from current day's accomplishment list at given position
    // MODIFY: this
    public void removeAccomplishment(int index) throws InvalidTaskNumberException {
        journalDay.deleteAchievement(index);
    }

    // EFFECT: returns list as a string in a readable format
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

    // EFFECT: returns list as a string in a readable format
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