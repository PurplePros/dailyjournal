package model;

import model.exception.InvalidTaskNumberException;

public class AppointmentList extends GeneralList {

    // EFFECT: creates an empty appointment list
    public AppointmentList() {
        super();
    }

    // EFFECT: adds an appointment to the existing list and prints a success message
    public void addTask(GeneralTask appointment) {
        list.add(appointment);
        System.out.println("Appointment #" + list.size() + " added!");
    }

    // EFFECT: deletes an appointment from existing list and prints a success message
    // REQUIRE: index < size of list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= list.size()) {
            throw new InvalidTaskNumberException();
        } else {
            list.remove(index);
            System.out.println("Appointment deleted! Now you have " + list.size() + " appointments.");
        }
    }
}
