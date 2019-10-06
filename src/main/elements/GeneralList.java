package elements;

import java.util.ArrayList;

public abstract class GeneralList {

    protected ArrayList<GeneralTask> list;

    // creates a new general section
    public GeneralList() {
        list = new ArrayList<>();
    }

    // MODIFY: this
    // EFFECT: takes user input about new achievement and adds to list
    abstract void addTask(GeneralTask a);

    // MODIFY: this
    // EFFECT: delete a task from to-do list at given index
    abstract void deleteTask(int index);

    // EFFECT: returns list
    public ArrayList<GeneralTask> getList() {
        return list;
    }
}
