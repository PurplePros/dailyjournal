package ui;

import java.util.*;
import sections.*;

public class Menu {

    private ToDo toDoList;
    private Accomplishments accomplisedList;

    public Menu() {
        toDoList = new ToDo();
        accomplisedList = new Accomplishments();
        runMenu();
    }

    // EFFECT: outputs the main menu
    public void printMenu() {
        System.out.println("-------------Welcome to Your Journal-------------");
        System.out.println("Please choose which section to access:");
        System.out.println("a) To-do List");
        System.out.println("b) Daily Accomplishments");
        System.out.println("c) Exit");
    }

    // EFFECT: takes in user input to determine next steps
    public void runMenu() {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            input = scanner.nextLine();
            if (input.equals("a")) {
                toDoList.runToDoList();
            } else if (input.equals("b")) {
                accomplisedList.runList();
            } else if (input.equals("c")) {
                break;
            } else {
                System.out.println("Request invalid. Please re-enter input.");
            }
        }
    }

}
