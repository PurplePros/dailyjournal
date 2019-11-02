package model;

public class SingleElementTask {

    protected MultipleElementsTask task;

    // EFFECT: creates a new general task given the action, time, and location
    // REQUIRE: task action length > 0
    public SingleElementTask(String action, SingleElementList sl) {
        task = new MultipleElementsTask(action, "", "", sl.getList());
    }

    public void setList(SingleElementList sl) {
        task.setList(sl.getList());
    }

    public MultipleElementsTask getTask() {
        return task;
    }

    @Override
    public boolean equals(Object obj) {
        return task.equals(obj);
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

}
