package ui;

import java.io.IOException;
import java.util.*;
import sections.*;

public class Menu {

    private ToDo toDoList;
    private Accomplishments accomplisedList;

    // EFFECT: empty to-do and accomplishment list for the duration of execution time
    public Menu() throws IOException {
        toDoList = new ToDo();
        accomplisedList = new Accomplishments();
        runMenu();
    }

    // EFFECT: outputs the start menu that entails the options
    public void printMenu() {
        System.out.println("-------------Welcome to Your Journal-------------");
        System.out.println("Please choose which section to access:");
        System.out.println("a) To-do List");
        System.out.println("b) Daily Accomplishments");
        System.out.println("c) Exit");
    }

    // EFFECT: asks user and determines the next menu of their choice
    public void runMenu() throws IOException {
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
