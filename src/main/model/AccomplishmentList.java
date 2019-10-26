package model;

import model.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class AccomplishmentList extends GeneralList {

    // EFFECT: creates an empty accomplishment list
    public AccomplishmentList() {
        super();
    }

    // MODIFY: this
    // EFFECT: adds an accomplishment to the accomplishment list and prints a success message
    public void addTask(GeneralTask a) {
        if (!list.contains(a)) {
            list.add(a);
            a.addList(this);
            System.out.println("Achievement added!");
        }
    }

    // MODIFY: this
    // EFFECT: deletes an accomplishment from the list and prints success message
    // REQUIRE: index < size of accomplishment list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= list.size()) {
            throw new InvalidTaskNumberException();
        } else {
            GeneralTask item = list.get(index);
            if (list.contains(item)) {
                list.remove(index);
                item.removeList(null, index);
                System.out.println("Achievement removed!");
            }
        }
    }
}
