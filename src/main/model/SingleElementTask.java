package model;

import model.exception.InvalidTaskNumberException;

public class SingleElementTask {

    protected MultipleElementsTask task;

    // EFFECT: creates a new general task given the action, time, and location
    // REQUIRE: task action length > 0
    public SingleElementTask(String action, SingleElementList sl) {
        task = new MultipleElementsTask(action, "", "", sl.getList());
    }

   /* // EFFECT: returns the task action
    public String getAction() {
        return task.getAction();
    }*/

    public void setList(SingleElementList sl) {
        task.setList(sl.getList());
    }

    public MultipleElementsTask getTask() {
        return task;
    }

    /*
    public void addList(SingleElementList sl) {
        task.addList(sl.getList());
    }

    public void removeList(SingleElementList sl, int index) throws InvalidTaskNumberException {
        task.removeList(sl.getList(),index);
    }*/

    @Override
    public boolean equals(Object obj) {
        return task.equals(obj);
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

}
