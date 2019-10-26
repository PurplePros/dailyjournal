package model;

import model.exception.InvalidTaskNumberException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class GeneralList {

    protected ArrayList<GeneralTask> list;

    // EFFECT: creates a new empty list that takes in general tasks
    public GeneralList() {
        list = new ArrayList<>();
    }

    // MODIFY: this
    // EFFECT: adds a task to the list
    abstract void addTask(GeneralTask g);

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index
    // REQUIRE: index > size of list
    abstract void deleteTask(int index) throws InvalidTaskNumberException;

    // EFFECT: returns list
    public ArrayList<GeneralTask> getList() {
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GeneralList fc = (GeneralList) obj;

        return list.equals(fc.list);

    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
