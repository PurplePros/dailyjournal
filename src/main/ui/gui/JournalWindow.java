package ui.gui;

import ui.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class JournalWindow extends JFrame implements ActionListener {

    private Menu selectionMenu;
    private JTextArea todo;
    private JTextArea apps;
    private JTextArea accs;

    // EFFECT: creates a new JournalWindow with given date
    public JournalWindow(String date) throws IOException {
        super(date);
        selectionMenu = new Menu(date);
        displayGUI();
        displayAppointments();
        displayToDos();
        displayAchievements();
    }

    // EFFECT: sets other GUI components to this
    // MODIFY: this
    public void displayGUI() {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitProgram();
            }
        });
        setBounds(100, 100, 748, 1012);
        addToPane(getContentPane());

        setVisible(true);
    }

    // EFFECT: adds GUI components to pane
    // MODIFY: this
    public void addToPane(Container pane) {
        displayToDoGui(pane);
        displayAppGui(pane);
        displayAccGui(pane);

        displayLabels(pane);
        displayButtons(pane);
    }

    // EFFECT: adds to-do GUI components to pane
    // MODIFY: this
    public void displayToDoGui(Container pane) {
        todo = new JTextArea();
        todo.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        JScrollPane todos = new JScrollPane(todo);
        todos.setBounds(15, 52, 357, 808);
        pane.add(todos);
        todo.setLineWrap(true);
        todo.setEditable(false);
        todo.setWrapStyleWord(true);
    }

    // EFFECT: adds appointment GUI components to pane
    // MODIFY: this
    public void displayAppGui(Container pane) {
        apps = new JTextArea();
        apps.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        JScrollPane appointments = new JScrollPane(apps);
        appointments.setBounds(379, 52, 332, 495);
        pane.add(appointments);
        apps.setLineWrap(true);
        apps.setEditable(false);
        apps.setWrapStyleWord(true);
    }

    // EFFECT: adds labels to pane
    // MODIFY: this
    public void displayLabels(Container pane) {
        JLabel lblTodoList = new JLabel("To-Do List");
        lblTodoList.setFont(new Font("SimHei", Font.BOLD, 24));
        lblTodoList.setBounds(33, 16, 146, 38);
        pane.add(lblTodoList);

        JLabel lblAppointments = new JLabel("Appointments");
        lblAppointments.setFont(new Font("SimHei", Font.BOLD, 24));
        lblAppointments.setBounds(406, 16, 218, 38);
        pane.add(lblAppointments);

        JLabel lblAchievements = new JLabel("Achievements");
        lblAchievements.setFont(new Font("SimHei", Font.BOLD, 24));
        lblAchievements.setBounds(406, 556, 218, 38);
        pane.add(lblAchievements);
    }

    // EFFECT: display accomplishment GUI components to pane
    // MODIFY: this
    public void displayAccGui(Container pane) {
        accs = new JTextArea();
        accs.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        JScrollPane achievements = new JScrollPane(accs);
        achievements.setBounds(379, 593, 332, 267);
        pane.add(achievements);
        accs.setLineWrap(true);
        accs.setEditable(false);
        accs.setWrapStyleWord(true);
    }

    // EFFECT: adds buttons to pane
    // MODIFY: this
    public void displayButtons(Container pane) {
        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(82, 876, 193, 67);
        pane.add(btnAdd);
        btnAdd.addActionListener(this);
        btnAdd.setActionCommand("add");

        JButton btnRemove = new JButton("REMOVE");
        btnRemove.setBounds(431, 876, 193, 67);
        pane.add(btnRemove);
        btnRemove.addActionListener(this);
        btnRemove.setActionCommand("remove");
    }

    // EFFECT: display appointment entries to the appointment panel
    // MODIFY: this
    public void displayAppointments() {
        ArrayList<String> printedList = selectionMenu.toStringMultipleList(selectionMenu.getDay().getAppointmentList());
        String toPrint = "";
        int i = 0;
        for (String printedLine : printedList) {
            toPrint += printedLine;
            i++;
            toPrint += "\n";
        }
        apps.setText("");
        apps.append(toPrint);
    }

    // EFFECT: display achievement entries to the appointment panel
    // MODIFY: this
    public void displayAchievements() {
        ArrayList<String> printedList = selectionMenu.toStringSingleList(selectionMenu.getDay().getAchievementList());
        String toPrint = "";
        int i = 0;
        for (String printedLine : printedList) {
            toPrint += printedLine;
            i++;
            toPrint += "\n";
        }
        accs.setText("");
        accs.append(toPrint);
    }

    // EFFECT: display to-do entries to the to-do panel
    // MODIFY: this
    public void displayToDos() {
        ArrayList<String> printedList = selectionMenu.toStringMultipleList(selectionMenu.getDay().getToDoList());
        String toPrint = "";
        int i = 0;
        for (String printedLine : printedList) {
            toPrint += printedLine;
            i++;
            toPrint += "\n";
        }
        todo.setText("");
        todo.append(toPrint);
    }

    // EFFECT: creates new AddElementWindow or RemoveElementWindow depending on what button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            new AddElementWindow(selectionMenu, this);
        } else if (e.getActionCommand().equals("remove")) {
            new RemoveElementWindow(selectionMenu, this);
        }
    }

    // EFFECT: saves data to text file when this is closed
    public void exitProgram() {
        try {
            selectionMenu.getProcessor().save();
            dispose();
            System.exit(0);
        } catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(null,"File not saved. Try again.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }
    }
}