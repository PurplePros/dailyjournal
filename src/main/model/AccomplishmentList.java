package model;

import model.exception.InvalidTaskNumberException;

public class AccomplishmentList extends SingleElementList {

    // EFFECT: creates an empty accomplishment list
    public AccomplishmentList() {
        super();
    }
/*
    // MODIFY: this
    // EFFECT: adds an accomplishment to the accomplishment list and prints a success message
    public void addTask(SingleElementTask a) {
        if (!multipleList.contains(a)) {
            multipleList.add(a);
            a.addList(this);
            System.out.println("Achievement added!");
        }
    }

    // MODIFY: this
    // EFFECT: deletes an accomplishment from the list and prints success message
    // REQUIRE: index < size of accomplishment list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= singleElementList.size()) {
            throw new InvalidTaskNumberException();
        } else {
            SingleElementTask item = singleElementList.get(index);
            if (singleElementList.contains(item)) {
                singleElementList.remove(index);
                item.removeList(null, index);
                System.out.println("Achievement removed!");
            }
        }
    }*/
}
