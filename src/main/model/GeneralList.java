package model;

import model.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public abstract class GeneralList {

    protected ArrayList<GeneralTask> list;

    // EFFECT: creates a new empty list that takes in general tasks
    public GeneralList() {
        list = new ArrayList<>();
    }

    // MODIFY: this
    // EFFECT: adds a task to the list
    public void addTask(GeneralTask g) {
        list.add(g);
    }

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index
    // REQUIRE: index > size of list
    abstract void deleteTask(int index) throws InvalidTaskNumberException;

    // EFFECT: returns list
    public ArrayList<GeneralTask> getList() {
        return list;
    }
}
