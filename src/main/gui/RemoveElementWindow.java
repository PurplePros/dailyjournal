package gui;

import ui.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveElementWindow extends JFrame implements ActionListener {

    private Menu menu;
    private JournalWindow journalWindow;
    private JTextField index;
    private JRadioButton rdbtnTask;
    private JRadioButton rdbtnAccomplishment;
    private JRadioButton rdbtnAppointment;

    public RemoveElementWindow(Menu menu, JournalWindow journalWindow) {
        super();
        this.menu = menu;
        this.journalWindow = journalWindow;
        displayGui();
    }

    public void displayGui() {
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        addToPane(getContentPane());
        setVisible(true);
    }

    public void addToPane(Container pane) {
        JLabel lblAddAValid = new JLabel("Add a valid number from 1 to");
        lblAddAValid.setBounds(15, 16, 286, 27);
        lblAddAValid.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblAddAValid.setVerticalAlignment(SwingConstants.BOTTOM);
        pane.add(lblAddAValid);

        index = new JTextField();
        index.setBounds(15, 108, 136, 26);
        pane.add(index);
        index.setColumns(10);

        JButton btnRemoveItem = new JButton("Remove Item");
        btnRemoveItem.setBounds(15, 169, 158, 29);
        pane.add(btnRemoveItem);
        btnRemoveItem.addActionListener(this);
        btnRemoveItem.setActionCommand("remove");

        rdbtnTask = new JRadioButton("Task");
        rdbtnTask.setBounds(11, 55, 76, 29);
        pane.add(rdbtnTask);
        rdbtnTask.setSelected(true);
        rdbtnTask.addActionListener(this);

        rdbtnAccomplishment = new JRadioButton("Accomplishment");
        rdbtnAccomplishment.setBounds(245, 55, 155, 29);
        pane.add(rdbtnAccomplishment);
        rdbtnAccomplishment.addActionListener(this);

        rdbtnAppointment = new JRadioButton("Appointment");
        rdbtnAppointment.setBounds(98, 55, 155, 29);
        pane.add(rdbtnAppointment);
        rdbtnAppointment.addActionListener(this);

        ButtonGroup options = new ButtonGroup();
        options.add(rdbtnAccomplishment);
        options.add(rdbtnAppointment);
        options.add(rdbtnTask);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("remove")) {
            if (rdbtnAppointment.isSelected()) {
                menu.removeAppointment(Integer.parseInt(index.getText()));
            } else if (rdbtnAccomplishment.isSelected()) {
                menu.removeAccomplishment(Integer.parseInt(index.getText()));
            } else if (rdbtnTask.isSelected()) {
                menu.removeToDo(Integer.parseInt(index.getText()));
            }
            journalWindow.displayAchievements();
            journalWindow.displayToDos();
            journalWindow.displayAppointments();
        }
    }
}
