package model;

import model.exception.InvalidTaskNumberException;

public class ToDoList extends GeneralList {

    public ToDoList() {
        super();
    }

    // MODIFY: this
    // EFFECT: adds a task to to-do list and prints success message
    public void addTask(GeneralTask a) {
        if (!list.contains(a)) {
            list.add(a);
            a.addList(this);
            System.out.println("Task #" + list.size() + " added!");
        }
    }

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index and prints success message
    // REQUIRE: index < list length
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= list.size()) {
            throw new InvalidTaskNumberException();
        } else {
            GeneralTask item = list.get(index);
            if (list.contains(item)) {
                list.remove(index);
                item.removeList(null, index);
                System.out.println("Task removed! Now you have " + list.size() + "tasks!");
            }
        }
    }
}