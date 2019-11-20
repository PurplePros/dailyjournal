package gui;

import ui.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class JournalWindow extends JFrame implements ActionListener {

    private Menu selectionMenu;
    private JTextArea todo;
    private JTextArea apps;
    private JTextArea accs;

    public JournalWindow(String date) throws IOException {
        super(date);
        selectionMenu = new Menu(date);
        displayGUI();
        displayAppointments();
        displayToDos();
        displayAchievements();
    }

    public void displayGUI() {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 748, 1012);
        addToPane(getContentPane());

        setVisible(true);
    }

    public void addToPane(Container pane) {
        todo = new JTextArea();
        todo.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        JScrollPane todos = new JScrollPane(todo);
        todos.setBounds(15, 52, 357, 808);
        pane.add(todos);
        todo.setLineWrap(true);
        todo.setEditable(false);
        todo.setWrapStyleWord(true);

        apps = new JTextArea();
        apps.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        JScrollPane appointments = new JScrollPane(apps);
        appointments.setBounds(379, 52, 332, 495);
        pane.add(appointments);
        apps.setLineWrap(true);
        apps.setEditable(false);
        apps.setWrapStyleWord(true);

        accs = new JTextArea();
        accs.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        JScrollPane achievements = new JScrollPane(accs);
        achievements.setBounds(379, 593, 332, 267);
        pane.add(achievements);
        accs.setLineWrap(true);
        accs.setEditable(false);
        accs.setWrapStyleWord(true);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            new AddElementWindow(selectionMenu, this);
        } else if (e.getActionCommand().equals("remove")) {
            new RemoveElementWindow(selectionMenu, this);
        }
    }
}