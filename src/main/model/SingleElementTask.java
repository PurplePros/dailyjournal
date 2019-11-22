package model;

public class SingleElementTask {

    protected MultipleElementsTask task;

    // EFFECT: creates a new general task given the action, time, and location
    // REQUIRE: task action length > 0
    public SingleElementTask(String action, SingleElementList sl) {
        task = new MultipleElementsTask(action, "", "", sl.getList());
    }

    // EFFECT: sets this' list to sl
    // MODIFIES: this
    public void setList(SingleElementList sl) {
        task.setList(sl.getList());
    }

    // EFFECT: returns the task
    public MultipleElementsTask getTask() {
        return task;
    }

    // EFFECT: returns true if this is equal to obj
    @Override
    public boolean equals(Object obj) {
        return task.equals(obj);
    }

    // EFFECT: returns the hashCode of this
    @Override
    public int hashCode() {
        return task.hashCode();
    }

}
