package elements;

import java.io.IOException;

public class AccomplishmentList extends GeneralList {

    // creates an empty accomplishment list
    public AccomplishmentList() {
        super();
    }

    // MODIFY: this
    // EFFECT: adds an accomplishment to list and prints success message
    public void addTask(GeneralTask a) {
        list.add(a);
        System.out.println("Achievement added!");
    }

    // MODIFY: this
    // EFFECT: deletes an accomplishment from list and prints success message
    public void deleteTask(int index) {
        list.remove(index);
        System.out.println("Achievement removed!");
    }


}
