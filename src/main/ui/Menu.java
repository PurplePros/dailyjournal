package ui;

import model.*;
import model.exception.InvalidUserInputException;

import java.io.IOException;
import java.util.*;

public class Menu {

    private FileProcessor processor;
    private String date;
    private Day journalDay;

    // EFFECT: runs user-specified menu with this date
    public Menu(String date) throws IOException, InvalidUserInputException {
        this.date = date;
        processor = new FileProcessor(date);
        processor.load();
        ToDoList td = processor.getToDoList();
        AccomplishmentList ac = processor.getAccomplishmentList();
        AppointmentList ap = processor.getAppointmentList();
        journalDay = new Day(date, td, ac, ap);
        runSelectionMenu();
    }

    // EFFECT: prints the options to either access the to-do, appointment, or accomplishment lists
    private void printSelectionMenu(String date) {
        System.out.println("-------------" + date + "-------------");
        System.out.println("Please choose which section to access:");
        System.out.println("a) To-do List");
        System.out.println("b) Daily Accomplishments");
        System.out.println("c) Appointments");
        System.out.println("d) Go back");
    }

    // EFFECT: prints the accomplishments menu
    private void printAccomplishmentMenu() {
        System.out.println("ACCOMPLISHMENTS");
        System.out.println("How would you like to continue?");
        System.out.println("a) View my achievements");
        System.out.println("b) Add an achievement");
        System.out.println("c) Delete an achievement");
        System.out.println("e) Go back to previous menu");
    }

    // EFFECT: prints out the to-do list menu
    private void printToDoMenu() {
        System.out.println("TO-DO LIST");
        System.out.println("What would you like to do? Choose an option.");
        System.out.println("(a) View my to-do list");
        System.out.println("(b) Add a new task to my to-do list");
        System.out.println("      -> Enter in what you need to do, timeframe, and location");
        System.out.println("(c) Delete a task from my to-do list");
        System.out.println("      -> Enter the task number you want to delete:");
        System.out.println("(d) Go back to main menu");
    }

    // EFFECT: prints out the to-do list menu
    private void printAppointmentMenu() {
        System.out.println("APPOINTMENTS");
        System.out.println("(a) View my appointments");
        System.out.println("(b) Add a new appointment");
        System.out.println("      -> Enter in what you need to do, timeframe, and location");
        System.out.println("(c) Complete an appointment");
        System.out.println("      -> Enter the appointment number");
        System.out.println("(d) Go back to main menu");
    }

    // EFFECT: prompts user input on which menu to access next and accesses the menu, otherwise save and exit
    // REQUIRE: user input must match one of the listed options
    public void runSelectionMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                printSelectionMenu(date);
                String input = scanner.nextLine();
                if (input.toLowerCase().equals("d")) {
                    processor.save();
                    break;
                } else {
                    takeInputSelection(input);
                }
            } catch (InvalidUserInputException e) {
                System.out.println("Not an option! Try again.");
            }
        }
    }

    // MODIFY: this
    // EFFECT: takes in user input and executes next steps
    // REQUIRE: user input must match one of the listed options
    public void takeInputSelection(String input) throws InvalidUserInputException {
        if (input.toLowerCase().equals("a")) {
            runToDoMenu();
        } else if (input.toLowerCase().equals("b")) {
            runAccomplishmentMenu();
        } else if (input.toLowerCase().equals("c")) {
            runAppointmentMenu();
        } else {
            throw new InvalidUserInputException();
        }
    }

    // MODIFY: this
    // EFFECT: prompts user and determines next steps
    // REQUIRE: user input must match one of the listed options
    public void runAccomplishmentMenu() throws InvalidUserInputException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printAccomplishmentMenu();
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("a")) {
                printSingleList(journalDay.getAchievementList());
            } else if (input.toLowerCase().equals("b")) {
                String accomplishment = scanner.nextLine();
                journalDay.addAchievement(accomplishment);
            } else if (input.toLowerCase().equals("c")) {
                int index = scanner.nextInt();
                journalDay.deleteAchievement(index);
            } else if (input.toLowerCase().equals("d")) {
                break;
            } else {
                throw new InvalidUserInputException();
            }
        }
    }

    // MODIFY: this
    // EFFECT: prompts user and determines next steps
    // REQUIRE: user input must match one of the listed options
    public void runToDoMenu() throws InvalidUserInputException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printToDoMenu();
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("d")) {
                break;
            } else {
                takeInputToDo(input);
            }
        }
    }

    // MODIFY: this
    // EFFECT: takes in user input and executes next steps
    // REQUIRE: user input must match one of the listed options
    public void takeInputToDo(String input) throws InvalidUserInputException {
        Scanner scanner = new Scanner(System.in);
        if (input.toLowerCase().equals("a")) {
            printMultipleList(journalDay.getToDoList());
        } else if (input.toLowerCase().equals("b")) {
            String action = scanner.nextLine();
            String time = scanner.nextLine();
            String location = scanner.nextLine();
            journalDay.addToDo(action, time, location);
        } else if (input.toLowerCase().equals("c")) {
            int index = scanner.nextInt();
            journalDay.deleteToDo(index);
        } else {
            throw new InvalidUserInputException();
        }
    }

    // MODIFY: this
    // EFFECT: prompts user about what they want to do and executes the next steps
    // REQUIRE: user input must match one of the listed options
    public void runAppointmentMenu() throws InvalidUserInputException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printAppointmentMenu();
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("d")) {
                break;
            } else {
                takeInputAppointments(input);
            }
        }
    }

    // MODIFY: this
    // EFFECT: takes in user input and executes next steps
    // REQUIRE: user input must match one of the listed options
    public void takeInputAppointments(String input) throws InvalidUserInputException {
        Scanner scanner = new Scanner(System.in);
        if (input.toLowerCase().equals("a")) {
            printMultipleList(journalDay.getAppointmentList());
        } else if (input.toLowerCase().equals("b")) {
            String action = scanner.nextLine();
            String time = scanner.nextLine();
            String location = scanner.nextLine();
            journalDay.addAppointment(action, time, location);
        } else if (input.toLowerCase().equals("c")) {
            int index = scanner.nextInt();
            journalDay.deleteAppointment(index);
        } else {
            throw new InvalidUserInputException();
        }
    }

    public void printMultipleList(ArrayList<MultipleElementsTask> list) {
        int i = 1;
        for (MultipleElementsTask t : list) {
            System.out.print("(" + i + ") ");
            System.out.println(t.getAction() + " @ " + t.getTime());
            if (!t.getAction().equals("")) {
                System.out.println("Location: " + t.getLocation());
            }
            System.out.println("");
            i++;
        }
    }

    // EFFECT: prints all the current achievements
    public void printSingleList(ArrayList<MultipleElementsTask> list) {
        int i = 1;
        for (MultipleElementsTask s : list) {
            System.out.println(i + ". " + s.getAction());
            i++;
        }
    }
}