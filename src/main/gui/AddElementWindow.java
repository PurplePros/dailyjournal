package gui;

import ui.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddElementWindow extends JFrame implements ActionListener {

    private Menu menu;
    private JEditorPane descriptionPane;
    private JEditorPane timePane;
    private JEditorPane locationPane;
    private JEditorPane descriptionPaneApp;
    private JEditorPane timePaneApp;
    private JEditorPane locationPaneApp;
    private JEditorPane descriptionPaneAcc;
    private JournalWindow journalWindow;

    public AddElementWindow(Menu menu, JournalWindow journalWindow) {
        super();
        this.menu = menu;
        this.journalWindow = journalWindow;
        displayGui();
    }

    public void displayGui() {
        setBounds(100, 100, 681, 473);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        addToPane(getContentPane());

        setVisible(true);
    }

    public void addToPane(Container pane) {

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        pane.add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Tasks", null, panel, null);
        panel.setLayout(null);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(15, 33, 136, 22);
        panel.add(lblDescription);

        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(15, 179, 136, 22);
        panel.add(lblTime);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(15, 257, 136, 22);
        panel.add(lblLocation);

        JButton btnEnter = new JButton("Enter");
        btnEnter.setBounds(524, 338, 115, 29);
        panel.add(btnEnter);
        btnEnter.addActionListener(this);
        btnEnter.setActionCommand("enterTask");

        descriptionPane = new JEditorPane();
        descriptionPane.setBounds(15, 66, 624, 97);
        panel.add(descriptionPane);

        timePane = new JEditorPane();
        timePane.setBounds(15, 215, 106, 26);
        panel.add(timePane);

        locationPane = new JEditorPane();
        locationPane.setBounds(15, 292, 257, 26);
        panel.add(locationPane);




        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Appointments", null, panel_2, null);
        panel_2.setLayout(null);

        JLabel label = new JLabel("Description");
        label.setBounds(15, 34, 79, 20);
        panel_2.add(label);

        JLabel label_1 = new JLabel("Time");
        label_1.setBounds(15, 183, 34, 20);
        panel_2.add(label_1);

        JLabel label_2 = new JLabel("Location");
        label_2.setBounds(15, 258, 59, 20);
        panel_2.add(label_2);

        JButton button = new JButton("Enter");
        button.setBounds(533, 341, 106, 26);
        panel_2.add(button);
        button.addActionListener(this);
        button.setActionCommand("enterApp");

        descriptionPaneApp = new JEditorPane();
        descriptionPaneApp.setBounds(15, 63, 624, 104);
        panel_2.add(descriptionPaneApp);

        timePaneApp = new JEditorPane();
        timePaneApp.setBounds(15, 216, 106, 26);
        panel_2.add(timePaneApp);

        locationPaneApp = new JEditorPane();
        locationPaneApp.setBounds(15, 294, 261, 26);
        panel_2.add(locationPaneApp);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Accomplishments", null, panel_1, null);
        panel_1.setLayout(null);

        descriptionPaneAcc = new JEditorPane();
        descriptionPaneAcc.setBounds(15, 86, 624, 162);
        panel_1.add(descriptionPaneAcc);

        JLabel lblWhatDidYou = new JLabel("What did you accomplish today?");
        lblWhatDidYou.setBounds(15, 50, 248, 20);
        panel_1.add(lblWhatDidYou);

        JButton btnEnter_1 = new JButton("Enter");
        btnEnter_1.setBounds(524, 338, 115, 29);
        panel_1.add(btnEnter_1);
        btnEnter_1.addActionListener(this);
        btnEnter_1.setActionCommand("enterAcc");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enterTask")) {
            menu.takeInputToDo(descriptionPane.getText(), timePane.getText(), locationPane.getText());
        } else if (e.getActionCommand().equals("enterApp")) {
            menu.takeInputAppointments(descriptionPaneApp.getText(), timePaneApp.getText(), locationPane.getText());
        } else if (e.getActionCommand().equals("enterAcc")) {
            menu.takeInputAccomplishment(descriptionPaneAcc.getText());
        }
        update();
    }

    public void update() {
        journalWindow.displayAppointments();
        journalWindow.displayToDos();
        journalWindow.displayAchievements();
    }
}
