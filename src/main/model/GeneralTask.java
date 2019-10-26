package model;

import model.exception.InvalidTaskDescriptionException;
import model.exception.InvalidTaskNumberException;

import java.util.Objects;

public abstract class GeneralTask {

    protected String action;
    protected String time;
    protected String location;
    protected GeneralList gl;

    // EFFECT: creates a new general task given the action, time, and location
    // REQUIRE: task action length > 0
    public GeneralTask(String action, String time, String location, GeneralList gl) {
        this.action = action;
        this.time = time;
        this.location = location;
        this.gl = gl;
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

    public void setGeneralList(GeneralList gl) {
        this.gl = gl;
    }

    public void addList(GeneralList gl) {
        if (!this.gl.equals(gl)) {
            setGeneralList(gl);
            gl.addTask(this);
        }
    }

    public void removeList(GeneralList gl, int index) throws InvalidTaskNumberException {
        if (this.gl.equals(gl)) {
            setGeneralList(null);
            gl.deleteTask(index);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GeneralTask other = (GeneralTask) obj;

        return this.action.equals(other.action) && this.time.equals(other.time) && this.location.equals(other.location);
    }

    @Override
    public int hashCode() {
        String hash = action + time + location;
        return hash.hashCode();
    }

}
