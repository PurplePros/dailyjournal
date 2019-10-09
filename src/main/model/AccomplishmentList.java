package model;

import model.exception.InvalidTaskNumberException;

public class AccomplishmentList extends GeneralList {

    // EFFECT: creates an empty accomplishment list
    public AccomplishmentList() {
        super();
    }

    // MODIFY: this
    // EFFECT: adds an accomplishment to the accomplishment list and prints a success message
    public void addTask(GeneralTask a) {
        list.add(a);
        System.out.println("Achievement added!");
    }

    // MODIFY: this
    // EFFECT: deletes an accomplishment from the list and prints success message
    // REQUIRE: index < size of accomplishment list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= list.size()) {
            throw new InvalidTaskNumberException();
        } else {
            list.remove(index);
            System.out.println("Achievement removed!");
        }
    }
}
