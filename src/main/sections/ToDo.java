package sections;

import elements.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ToDo {

    private ArrayList<Task> toDoList;

    // EFFECT: empty to-do list
    public ToDo() throws IOException {
        toDoList = new ArrayList<Task>();
        load();
    }

    // EFFECT: Prints out the to-do list menu
    public static void printWelcomeMessage() {
        System.out.println("TO-DO LIST");
        System.out.println("What would you like to do? Choose an option.");
        System.out.println("(a) View my to-do list");
        System.out.println("(b) Add a new task to my to-do list");
        System.out.println("      -> Enter in what you need to do and timeframe");
        System.out.println("(c) Delete a task from my to-do list");
        System.out.println("      -> Enter the task number you want to delete:");
        System.out.println("(d) Go back to main menu");
    }

    // MODIFY: this
    // EFFECT: Prompts user about what they want to do and executes the next steps
    public void runToDoList() throws IOException {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printWelcomeMessage();
            input = scanner.nextLine();
            if (input.equals("a")) {
                printToDoList();
            } else if (input.equals("b")) {
                addToList();
            } else if (input.equals("c")) {
                deleteFromList();
            } else {
                break;
            }
        }
    }

    // MODIFY: this
    // EFFECT: prompts and adds task to to-do list
    public void addToList() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        String time = scanner.nextLine();
        addTask(action, time);
        System.out.println("Task #" + toDoList.size() + " added!");
        save(toDoList);
    }

    // MODIFY: this
    // EFFECT: prompts and deletes task from to-do list
    public void deleteFromList() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        deleteTask(input - 1);
        System.out.println("Task deleted! Now you have " + toDoList.size() + " tasks.");
        save(toDoList);
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
            System.out.print(t.getAction() + " @ " + t.getTime());
            System.out.println("");
            i++;
        }
    }

    //  EFFECT: returns list
    public ArrayList getList() {
        return toDoList;
    }

    // MODIFY:
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("ToDoLog.txt"));
        for (String line : lines) {
            ArrayList<String> words = splitOnSpace(line);
            String taskBuilder = "";
            String taskTime = "";
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
                    taskTime = words.get(i);
                } else {
                    taskBuilder = taskBuilder + words.get(i) + " ";
                }
            }
            Task t = new Task(taskBuilder.trim(), taskTime);
            toDoList.add(t);
        }
    }

    // EFFECT:
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void save(ArrayList<Task> tasks) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("ToDoLog.txt", "UTF-8");
        for (Task t : tasks) {
            writer.println(t.getAction() + " " + t.getTime());
        }
        writer.close();
    }
}
