package ui;

import java.util.*;
import sections.ToDo;

public class Journal {

    public static void printWelcomeMessage() {
        System.out.println("----------Welcome to Your Journal----------");
        System.out.println("What would you like to do? First choose a number option.");
        System.out.println("(a) Add a new task to my to-do list");
        System.out.println("      -> Enter in what you need to do and timeframe");
        System.out.println("(b) Delete a task from my to-do list");
        System.out.println("      -> Enter the task number you want to delete:");
        System.out.println("(c) Print out my to-do list");
        System.out.println("(quit) Exit");
    }

    public static void addTask(ToDo toDoList) {
        Scanner scanner = new Scanner(System.in);
        String input1;
        String input2;
        input1 = scanner.nextLine();
        input2 = scanner.nextLine();
        toDoList.addTask(input1, input2);
    }

    public static void deleteTask(ToDo toDoList) {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        toDoList.deleteTask(Integer.parseInt(input) - 1);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        String input;
        Scanner scanner = new Scanner(System.in);
        ToDo schedule = new ToDo();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("a")) {
                addTask(schedule);
            } else if (input.equals("b")) {
                deleteTask(schedule);
            } else if (input.equals("c")) {
                schedule.printToDoList();
            } else {
                break;
            }
        }
    }
}
