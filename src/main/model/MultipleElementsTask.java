package model;

import model.exception.InvalidTaskNumberException;

public class MultipleElementsTask {

    protected String action;
    protected String time;
    protected String location;
    protected MultipleElementsList ml;

    // EFFECT: creates a new general task given the action, time, and location
    public MultipleElementsTask(String action, String time, String location, MultipleElementsList ml) {
        this.action = action;
        this.time = time;
        this.location = location;
        this.ml = ml;
    }

    // EFFECT: returns the task action
    public String getAction() {
        return action;
    }

    // EFFECT: returns the time
    public String getTime() {
        return time;
    }

    // EFFECT: returns the location
    public String getLocation() {
        return location;
    }

    // EFFECT: sets this' multiple elements list to ml
    // MODIFIES: this
    public void setList(MultipleElementsList ml) {
        this.ml = ml;
    }

    // EFFECT: adds to this' multiple elements list
    // MODIFIES: this and ml
    public void addList(MultipleElementsList ml) {
        if (!this.ml.equals(ml)) {
            setList(ml);
            ml.addTask(this);
        }
    }

    // EFFECT: removes this from multiple elements list
    // EFFECT: modifies this and ml
    public void removeList(MultipleElementsList ml, int index) throws InvalidTaskNumberException {
        if (this.ml.equals(ml)) {
            setList(null);
            ml.deleteTask(index);
        }
    }

    // EFFECT: return if this is equal to obj
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MultipleElementsTask other = (MultipleElementsTask) obj;

        return this.action.equals(other.action) && this.time.equals(other.time) && this.location.equals(other.location);
    }

    // EFFECT: returns the hash code of this
    @Override
    public int hashCode() {
        String hash = action + time + location;
        return hash.hashCode();
    }

}
