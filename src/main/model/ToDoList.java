package model;

import model.exception.InvalidTaskNumberException;

public class ToDoList extends GeneralList {

    public ToDoList() {
        super();
    }

    // MODIFY: this
    // EFFECT: adds a task to to-do list and prints success message
    public void addTask(GeneralTask a) {
        list.add(a);
        System.out.println("Task #" + list.size() + "added!");
    }

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index and prints success message
    // REQUIRE: index < list length
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= list.size()) {
            throw new InvalidTaskNumberException();
        } else {
            list.remove(index);
            System.out.println("Task removed! Now you have " + list.size() + "tasks!");
        }
    }

}