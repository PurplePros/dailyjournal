package model;

import java.util.*;

public abstract class Subject {

    Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void notifyObserver(Boolean added) {
        if (observer != null) {
            observer.update(added);
        }
    }
}
