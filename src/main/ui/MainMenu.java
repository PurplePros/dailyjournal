package ui;

import model.exception.InvalidDateException;

import java.io.File;
import java.io.IOException;

public class MainMenu {

    // EFFECT: if input is "a" then a new file with date as name is created, if input is "b" then create a new menu
    //         with specified date
    public void takeInputMenu(String input, String date)
            throws InvalidDateException, IOException {
        if (input.toLowerCase().equals("a")) {
            if (!isRightFormat(date)) {
                throw new InvalidDateException();
            } else {
                makeFile(date);
                new Menu(date);
            }
        } else if (input.toLowerCase().equals("b")) {
            if (!isRightFormat(date)) {
                throw new InvalidDateException();
            } else {
                new Menu(date);
            }
        }
    }

    // EFFECT: returns true if data is in the format YYYYMMDD, false otherwise
    public boolean isRightFormat(String date) {
        String dateRegex = "^(20\\d\\d)(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])$";
        return date.matches(dateRegex);
    }

    // EFFECT: creates a new text file in the data folder with given date as name
    public void makeFile(String fileName) throws IOException {
        String name = "data/" + fileName + ".txt";
        File file = new File(name);
        file.createNewFile();
    }
}
