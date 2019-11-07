package model;

import model.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class MultipleElementsList extends Subject {

    protected ArrayList<MultipleElementsTask> tasks;

    // EFFECT: creates a new empty list that takes in general tasks
    public MultipleElementsList() {
        super();
        tasks = new ArrayList<>();
    }

    // EFFECT: adds an appointment to the existing list and prints a success message
    public void addTask(MultipleElementsTask task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
            notifyObserver(true);
            task.addList(this);
        }
    }

    // EFFECT: deletes an appointment from existing list and prints a success message
    // REQUIRE: index < size of list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            MultipleElementsTask item = tasks.get(index);
            if (tasks.contains(item)) {
                tasks.remove(index);
                notifyObserver(false);
                item.removeList(null, index);
            }
        }
    }

    // EFFECT: returns list
    public ArrayList<MultipleElementsTask> getTasks() {
        return tasks;
    }

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

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }

}
