package ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

    public MainMenu() throws IOException {
        runMainMenu();
    }

    // EFFECT: outputs the start menu that entails the options
    public void printMainMenu() {
        System.out.println("-------------Welcome to Your Journal-------------");
        System.out.println("Enter date in the format YYYY-MMM-DD");
        System.out.println("a) Start a new day");
        System.out.println("b) Access previous days");
        System.out.println("c) Exit");
    }

    // EFFECT: asks user and determines the next menu of their choice
    public void runMainMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMainMenu();
            String input = scanner.nextLine();
            if (input.equals("a")) {
                input = scanner.nextLine();
                makeFile(input);
                Menu day = new Menu(input);
            } else if (input.equals("b")) {
                input = scanner.nextLine();
                Menu day = new Menu(input);
            } else {
                break;
            }
        }
    }

    public void makeFile(String fileName) throws IOException {
        String name = fileName + ".txt";
        File file = new File(name);
        file.createNewFile();
    }
}
