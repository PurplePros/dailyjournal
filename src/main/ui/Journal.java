package ui;

import gui.JournalWindow;

import javax.swing.*;
import java.io.IOException;

public class Journal {

    // EFFECT: loads the main menu
    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Menu menu = new Menu("20191024");
        new JournalWindow("20191024");
    }
}