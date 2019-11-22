package ui;

import ui.gui.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Journal {

    // EFFECT: loads the main menu
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IOException, IllegalAccessException {
        new MainWindow();
    }
}