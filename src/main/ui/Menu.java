package ui;

import elements.Day;
import elements.GeneralTask;
import java.io.IOException;
import java.util.*;

public class Menu {

    private Day journalDay;
    private String date;

    // EFFECT: empty to-do and accomplishment list for the duration of execution time
    public Menu(String date) throws IOException {
        this.date = date;
        journalDay = new Day(date);
        runSelectionMenu();
    }

    // EFFECT: prints the start menu
    public void printSelectionMenu(String date) {
        System.out.println("-------------" + date + "-------------");
        System.out.println("Please choose which section to access:");
        System.out.println("a) To-do List");
        System.out.println("b) Daily Accomplishments");
        System.out.println("c) Appointments");
        System.out.println("d) Go back");
    }

    // EFFECT: prints the accomplishments menu
    public void printAccomplishmentMenu() {
        System.out.println("ACCOMPLISHMENTS");
        System.out.println("How would you like to continue?");
        System.out.println("a) View my achievements");
        System.out.println("b) Add an achievement");
        System.out.println("c) Delete an achievement");
        System.out.println("e) Go back to previous menu");
    }

    // EFFECT: Prints out the to-do list menu
    public void printToDoMenu() {
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
    public void printAppointmentMenu() {
        System.out.println("APPOINTMENTS");
        System.out.println("(a) View my appointments");
        System.out.println("(b) Add a new appointment");
        System.out.println("      -> Enter in what you need to do, timeframe, and location");
        System.out.println("(c) Complete an appointment");
        System.out.println("      -> Enter the appointment number");
        System.out.println("(d) Go back to main menu");
    }

    // EFFECT: prompts user input on which menu to access next and accesses the menu, otherwise save and exit
    public void runSelectionMenu() throws IOException {
        journalDay.load();
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printSelectionMenu(date);
            input = scanner.nextLine();
            if (input.equals("a")) {
                runToDoMenu();
            } else if (input.equals("b")) {
                runAccomplishmentMenu();
            } else if (input.equals("c")) {
                runAppointmentMenu();
            } else {
                journalDay.save();
                break;
            }
        }
    }

    // MODIFY: this
    // EFFECT: prompts user and determines next steps
    public void runAccomplishmentMenu() throws IOException {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printAccomplishmentMenu();
            input = scanner.nextLine();
            if (input.equals("a")) {
                printAccomplishmentList();
            } else if (input.equals("b")) {
                String accomplishment = scanner.nextLine();
                journalDay.addAchievement(accomplishment);
            } else if (input.equals("c")) {
                int index = scanner.nextInt();
                journalDay.deleteAchievement(index);
            } else {
                break;
            }
        }
    }

    // MODIFY: this
    // EFFECT: Prompts user about what they want to do and executes the next steps
    public void runToDoMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printToDoMenu();
            String input = scanner.nextLine();
            if (input.equals("a")) {
                printToDoList();
            } else if (input.equals("b")) {
                String action = scanner.nextLine();
                String time = scanner.nextLine();
                String location = scanner.nextLine();
                journalDay.addToDo(action, time, location);
            } else if (input.equals("c")) {
                int index = scanner.nextInt();
                journalDay.deleteToDo(index);
            } else {
                break;
            }
        }
    }

    // MODIFY: this
    // EFFECT: prompts user about what they want to do and executes the next steps
    public void runAppointmentMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printAppointmentMenu();
            String input = scanner.nextLine();
            if (input.equals("a")) {
                printAppointmentList();
            } else if (input.equals("b")) {
                String action = scanner.nextLine();
                String time = scanner.nextLine();
                String location = scanner.nextLine();
                journalDay.addAppointment(action, time, location);
            } else if (input.equals("c")) {
                int index = scanner.nextInt();
                journalDay.deleteAppointment(index);
            } else {
                break;
            }
        }
    }

    // EFFECT: prints all the achievements
    public void printAccomplishmentList() {
        int i = 1;
        for (GeneralTask s : journalDay.getAchievementList()) {
            System.out.println(i + ". " + s.getAction());
            i++;
        }
    }

    // EFFECT: print entire to-do list
    public void printToDoList() {
        System.out.println("Your to-do list for today:");
        int i = 1;
        for (GeneralTask t : journalDay.getToDoList()) {
            System.out.print("(" + i + ") ");
            System.out.println(t.getAction() + " @ " + t.getTime());
            System.out.println("Location: " + t.getLocation());
            System.out.println("");
            i++;
        }
    }

    // EFFECT: print entire appointment list
    public void printAppointmentList() {
        System.out.println("Your appointments for today:");
        int i = 1;
        for (GeneralTask a : journalDay.getAppointmentList()) {
            System.out.print("(" + i + ") ");
            System.out.println(a.getAction() + " @ " + a.getTime());
            System.out.println("Location: " + a.getLocation());
            System.out.println("");
            i++;
        }
    }
}
