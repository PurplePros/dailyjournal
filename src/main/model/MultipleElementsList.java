package model;

import model.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class MultipleElementsList {

    protected ArrayList<MultipleElementsTask> tasks;

    // EFFECT: creates a new empty list that takes in general tasks
    public MultipleElementsList() {
        super();
        tasks = new ArrayList<>();
    }

    // EFFECT: adds an entry to the existing list if a duplicate does not already exist
    // MODIFIES: this
    public void addTask(MultipleElementsTask task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
            task.addList(this);
        }
    }

    // EFFECT: deletes an entry from existing list and exception is thrown if the index number is invalid
    // MODIFIES: this
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            MultipleElementsTask item = tasks.get(index);
            if (tasks.contains(item)) {
                tasks.remove(index);
                item.removeList(null, index);
            }
        }
    }

    // EFFECT: returns multiple elements list
    public ArrayList<MultipleElementsTask> getTasks() {
        return tasks;
    }

    // EFFECT: returns true if this is equivalent to obj
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MultipleElementsList fc = (MultipleElementsList) obj;

        return tasks.equals(fc.tasks);

    }

    // EFFECT: returns the hashCode of the list of multiple elements
    @Override
    public int hashCode() {
        return tasks.hashCode();
    }

}
