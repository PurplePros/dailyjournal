package model;

import model.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class SingleElementList {

    protected MultipleElementsList multipleList;

    // EFFECT: creates a new empty list that takes in general tasks
    public SingleElementList() {
        multipleList = new MultipleElementsList();
    }

    // MODIFY: this
    // EFFECT: adds a task to the list
    public void addTask(MultipleElementsTask action) {
        multipleList.addTask(action);
    }

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index
    // REQUIRE: index > size of list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        multipleList.deleteTask(index);
    }

    // EFFECT: returns list
    public MultipleElementsList getList() {
        return multipleList;
    }

    @Override
    public boolean equals(Object obj) {
        return multipleList.equals(obj);
    }

    @Override
    public int hashCode() {
        return multipleList.hashCode();
    }

}
