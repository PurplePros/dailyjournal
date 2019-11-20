package ui;

import model.exception.InvalidDateException;

import java.io.File;
import java.io.IOException;

public class MainMenu {

    // EFFECT: sets up the main menu
    public MainMenu() throws IOException {
        //runMainMenu();
    }

    /*// EFFECT: prints options to either access a previous day, start a new day, or exit
    private void printMainMenu() throws IOException {
        System.out.println("-------------Welcome-------------");
        //System.out.println(extractWeather(getWeather()));
        System.out.println("a) Start a new day");
        System.out.println("    -> Enter date in the format YYYYMMDD");
        System.out.println("b) Access previous days");
        System.out.println("c) Exit");
    }

    // EFFECT: prints main menu and gets user input to determine next program steps
    // REQUIRE: user input must be either a,b, or c
    //          if creating new entry, new journal date must be in valid format of YYYYMMDD
    //          if accessing old entry, must be a valid previous date
    public void runMainMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                printMainMenu();
                String input = scanner.nextLine();
                if (input.toLowerCase().equals("c")) {
                    break;
                } else {
                    takeInputMenu(input, scanner);
                }
            } catch (InvalidUserInputException e) {
                System.out.println("Invalid user input! Try again.");

            } catch (InvalidDateException e) {
                System.out.println("Invalid date! Must be in the format YYYYMMDD.");
            }
        }
    }*/

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
