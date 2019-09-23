package sections;

import elements.Task;
import java.util.*;

public class ToDo {

    private ArrayList<Task> toDoList;

    // EFFECT: empty to-do list
    public ToDo() {
        toDoList = new ArrayList<Task>();
    }

    // EFFECT: Prints out the to-do list menu
    public static void printWelcomeMessage() {
        System.out.println("TO-DO LIST");
        System.out.println("What would you like to do? First choose a number option.");
        System.out.println("(a) Add a new task to my to-do list");
        System.out.println("      -> Enter in what you need to do and timeframe");
        System.out.println("(b) Delete a task from my to-do list");
        System.out.println("      -> Enter the task number you want to delete:");
        System.out.println("(c) Print out my to-do list");
        System.out.println("(d) Go back to main menu");
    }

    // MODIFY: this
    // EFFECT: Prompts user about what they want to do and executes the next steps
    public void runToDoList() {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printWelcomeMessage();
            input = scanner.nextLine();
            if (input.equals("a")) {
                addToList();
            } else if (input.equals("b")) {
                deleteFromList();
            } else if (input.equals("c")) {
                printToDoList();
            } else if (input.equals("d")) {
                break;
            } else {
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    // MODIFY: this
    // EFFECT: prompts and adds task to to-do list
    public void addToList() {
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        String time = scanner.nextLine();
        addTask(action, time);
        System.out.println("Task #" + toDoList.size() + " successfully added!");
    }

    // MODIFY: this
    // EFFECT: prompts and deletes task from to-do list
    public void deleteFromList() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        deleteTask(input - 1);
        System.out.println("Task sucessfully deleted! Now you have " + toDoList.size() + " tasks.");
    }

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index
    public void deleteTask(int index) {
        toDoList.remove(index);
    }

    // MODIFY: this
    // EFFECT: add a task to the to-do list
    public void addTask(String action, String time) {
        Task newTask = new Task(action, time);
        toDoList.add(newTask);
    }

    // EFFECT: print entire to-do list
    public void printToDoList() {
        System.out.println("Your to-do list for today:");
        int i = 1;
        for (Task t : toDoList) {
            System.out.print("(" + i + ") ");
            System.out.print(t.getAction() + " @" + t.getTime());
            System.out.println("");
            i++;
        }
    }

    //  EFFECT: returns list
    public ArrayList getList() {
        return toDoList;
    }
}
