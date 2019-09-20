package sections;

import elements.Task;
import java.util.ArrayList;

public class ToDo {

    ArrayList<Task> toDoList = new ArrayList<Task>();

    public ToDo() {

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

    // EFFECT: get the task at given index
    public String getTask(int index) {
        return toDoList.get(index).getAction();
    }

    // EFFECT: delete a task at given index
    public void deleteTask(int index) {
        toDoList.remove(index);
        System.out.println("Task sucessfully deleted! Now you have " + toDoList.size() + " tasks.");
    }

    // EFFECT: add a task
    public void addTask(String action, String time) {
        Task newTask = new Task(action, time);
        toDoList.add(newTask);
        System.out.println("Task #" + toDoList.size() + " successfully added!");
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

}
