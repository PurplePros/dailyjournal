package elements;

import java.io.*;
import java.util.*;

public class ToDoList extends GeneralList {

    public ToDoList() {
        super();
    }

    // MODIFY: this
    // EFFECT: adds a task to list and prints success message
    public void addTask(GeneralTask a) {
        list.add(a);
        System.out.println("Task #" + list.size() + "added!");
    }

    // MODIFY: this
    // EFFECT: delete a task from list at given index and prints success message
    public void deleteTask(int index) {
        list.remove(index);
        System.out.println("Task removed! Now you have " + list.size() + "tasks!");
    }

}