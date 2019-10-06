package elements;

public class AppointmentList extends GeneralList {

    public AppointmentList() {
        super();
    }

    // EFFECT: adds an appointment to the existing list and prints success message
    public void addTask(GeneralTask appointment) {
        list.add(appointment);
        System.out.println("Appointment #" + list.size() + " added!");
    }

    // EFFECT: deletes an appointment from existing list and prints success message
    public void deleteTask(int index) {
        list.remove(index);
        System.out.println("Appointment deleted! Now you have " + list.size() + " appointments.");
    }
}
