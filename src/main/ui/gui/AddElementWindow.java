package ui.gui;

import model.exception.InvalidFormatException;
import ui.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddElementWindow extends JFrame implements ActionListener {

    private Menu menu;
    private JEditorPane descriptionPaneTask;
    private JEditorPane timePane;
    private JEditorPane locationPane;
    private JEditorPane descriptionPaneApp;
    private JEditorPane timePaneApp;
    private JEditorPane locationPaneApp;
    private JEditorPane descriptionPaneAcc;
    private JournalWindow journalWindow;

    // EFFECT: creates a new AddElementWindow
    public AddElementWindow(Menu menu, JournalWindow journalWindow) {
        super();
        this.menu = menu;
        this.journalWindow = journalWindow;
        displayGui();
    }

    // EFFECT: sets settings of this
    // MODIFY: this
    public void displayGui() {
        setBounds(100, 100, 681, 473);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        addToPane(getContentPane());

        setVisible(true);
    }

    // EFFECT: adds gui components to this
    // MODIFY: this
    public void addToPane(Container pane) {
        //Main pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        pane.add(tabbedPane);

        //Task panel
        JPanel taskPanel = new JPanel();
        tabbedPane.addTab("Tasks", null, taskPanel, null);
        taskPanel.setLayout(null);
        addLabelsToTaskPanel(taskPanel);
        addUserFunctionToTaskPanel(taskPanel);

        //Appointment panel
        JPanel appPanel = new JPanel();
        tabbedPane.addTab("Appointments", null, appPanel, null);
        appPanel.setLayout(null);
        addLabelsToAppPanel(appPanel);
        addUserFunctionToAppPanel(appPanel);

        //Accomplishment panel
        JPanel accPanel = new JPanel();
        tabbedPane.addTab("Accomplishments", null, accPanel, null);
        accPanel.setLayout(null);
        addAccPanelElements(accPanel);
    }

    // EFFECTS: adds labels to taskPanel
    // MODIFY: taskPanel
    public void addLabelsToTaskPanel(JPanel taskPanel) {
        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(15, 33, 136, 22);
        taskPanel.add(lblDescription);

        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(15, 179, 136, 22);
        taskPanel.add(lblTime);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(15, 257, 136, 22);
        taskPanel.add(lblLocation);
    }

    // EFFECTS: adds user input boxes and button to panel
    // MODIFY: taskPanel
    public void addUserFunctionToTaskPanel(JPanel taskPanel) {
        JButton btnEnter = new JButton("Enter");
        btnEnter.setBounds(524, 338, 115, 29);
        taskPanel.add(btnEnter);
        btnEnter.addActionListener(this);
        btnEnter.setActionCommand("enterTask");

        descriptionPaneTask = new JEditorPane();
        descriptionPaneTask.setBounds(15, 66, 624, 97);
        taskPanel.add(descriptionPaneTask);

        timePane = new JEditorPane();
        timePane.setBounds(15, 215, 106, 26);
        taskPanel.add(timePane);

        locationPane = new JEditorPane();
        locationPane.setBounds(15, 292, 257, 26);
        taskPanel.add(locationPane);
    }

    // EFFECTS: adds labels to appPanel
    // MODIFY: appPanel
    public void addLabelsToAppPanel(JPanel appPanel) {
        JLabel descriptionApp = new JLabel("Description");
        descriptionApp.setBounds(15, 34, 79, 20);
        appPanel.add(descriptionApp);

        JLabel timeApp = new JLabel("Time");
        timeApp.setBounds(15, 183, 34, 20);
        appPanel.add(timeApp);

        JLabel locationApp = new JLabel("Location");
        locationApp.setBounds(15, 258, 59, 20);
        appPanel.add(locationApp);
    }

    // EFFECTS: adds user input boxes and button to appPanel
    // MODIFY: appPanel
    public void addUserFunctionToAppPanel(JPanel appPanel) {
        JButton buttonApp = new JButton("Enter");
        buttonApp.setBounds(533, 341, 106, 26);
        appPanel.add(buttonApp);
        buttonApp.addActionListener(this);
        buttonApp.setActionCommand("enterApp");

        descriptionPaneApp = new JEditorPane();
        descriptionPaneApp.setBounds(15, 63, 624, 104);
        appPanel.add(descriptionPaneApp);

        timePaneApp = new JEditorPane();
        timePaneApp.setBounds(15, 216, 106, 26);
        appPanel.add(timePaneApp);

        locationPaneApp = new JEditorPane();
        locationPaneApp.setBounds(15, 294, 261, 26);
        appPanel.add(locationPaneApp);
    }

    // EFFECTS: adds GUI components to panel
    // MODIFY: accPanel
    public void addAccPanelElements(JPanel accPanel) {
        descriptionPaneAcc = new JEditorPane();
        descriptionPaneAcc.setBounds(15, 86, 624, 162);
        accPanel.add(descriptionPaneAcc);

        JLabel descriptionAcc = new JLabel("What did you accomplish today?");
        descriptionAcc.setBounds(15, 50, 248, 20);
        accPanel.add(descriptionAcc);

        JButton buttonAcc = new JButton("Enter");
        buttonAcc.setBounds(524, 338, 115, 29);
        accPanel.add(buttonAcc);
        buttonAcc.addActionListener(this);
        buttonAcc.setActionCommand("enterAcc");
    }

    // EFFECT: calls me
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("enterTask")) {
                menu.takeInputToDo(descriptionPaneTask.getText(), timePane.getText(), locationPane.getText());
                resetTaskFields();
            } else if (e.getActionCommand().equals("enterApp")) {
                menu.takeInputAppointments(descriptionPaneApp.getText(),
                        timePaneApp.getText(), locationPaneApp.getText());
                resetAppointmentFields();
            } else if (e.getActionCommand().equals("enterAcc")) {
                menu.takeInputAccomplishment(descriptionPaneAcc.getText());
                resetAccomplishmentFields();
            }
            update();
        } catch (InvalidFormatException exception) {
            JOptionPane.showMessageDialog(null,
                    "Invalid format, check your description or time for errors");
        }
    }

    // EFFECT: displays current list of appointments, achievements, and to-dos
    public void update() {
        journalWindow.displayAppointments();
        journalWindow.displayToDos();
        journalWindow.displayAchievements();
    }

    // EFFECT: clears out text fields in the task panel
    public void resetTaskFields() {
        descriptionPaneTask.setText("");
        timePane.setText("");
        locationPane.setText("");
    }

    // EFFECT: clears out text fields in the appointment panel
    public void resetAppointmentFields() {
        descriptionPaneApp.setText("");
        timePaneApp.setText("");
        locationPaneApp.setText("");
    }

    // EFFECT: clears out text field in the accomplishment panel
    public void resetAccomplishmentFields() {
        descriptionPaneAcc.setText("");
    }
}
