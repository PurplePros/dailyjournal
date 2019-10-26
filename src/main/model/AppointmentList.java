package model;

import model.exception.InvalidTaskNumberException;

public class AppointmentList extends GeneralList {

    // EFFECT: creates an empty appointment list
    public AppointmentList() {
        super();
    }

    // EFFECT: adds an appointment to the existing list and prints a success message
    public void addTask(GeneralTask appointment) {
        if (!list.contains(appointment)) {
            list.add(appointment);
            appointment.addList(this);
            System.out.println("Appointment #" + list.size() + " added!");
        }
    }

    // EFFECT: deletes an appointment from existing list and prints a success message
    // REQUIRE: index < size of list
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index >= list.size()) {
            throw new InvalidTaskNumberException();
        } else {
            GeneralTask item = list.get(index);
            if (list.contains(item)) {
                list.remove(index);
                item.removeList(null, index);
                System.out.println("Appointment deleted! Now you have " + list.size() + " appointments.");
            }
        }
    }
}
