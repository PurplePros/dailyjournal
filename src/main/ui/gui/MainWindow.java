package ui.gui;

import model.exception.InvalidDateException;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static network.Weather.extractWeather;
import static network.Weather.getWeather;

public class MainWindow implements ActionListener {

    static final String NEWDAYOPTION = "New Day";
    static final String PREVIOUSDAYOPTION = "Previous Day";
    static final float FONTSIZE = 20;

    private MainMenu menu;
    private JFrame frame;
    private JPanel newDayOption;
    private JPanel oldDayOption;
    private JTextField dateFieldNew;
    private JTextField dateFieldOld;


    public MainWindow() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException,
            IllegalAccessException, IOException {
        menu = new MainMenu();
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        displayGUI();
    }

    public void addToPane(Container pane) throws IOException {
        JTabbedPane tabbedPane = new JTabbedPane();

        dateFieldNew = new JTextField(20);
        dateFieldOld = new JTextField(20);

        dateFieldNew.setMaximumSize(dateFieldNew.getPreferredSize());
        dateFieldOld.setMaximumSize(dateFieldOld.getPreferredSize());

        // New Day Option
        displayNewDayPanel();

        // Old Day Option
        displayOldDayPanel();

        tabbedPane.addTab(NEWDAYOPTION, newDayOption);
        tabbedPane.addTab(PREVIOUSDAYOPTION, oldDayOption);
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(FONTSIZE));
        pane.add(tabbedPane, BorderLayout.CENTER);

    }

    public void displayGUI() throws IOException {
        frame = new JFrame("MyJournal");

        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addToPane(frame.getContentPane());


        frame.pack();
        frame.setSize(new Dimension(700, 400));
        frame.setVisible(true);

    }

    public void displayNewDayPanel() throws IOException {
        JLabel newWelcomeMessage = new JLabel("Hello. " + extractWeather(getWeather()));
        JLabel newFormatMessage = new JLabel("Please enter in a date in the format YYYYMMDD");
        newWelcomeMessage.setFont(newWelcomeMessage.getFont().deriveFont(FONTSIZE));

        newDayOption = new JPanel(new BorderLayout());
        newDayOption.setLayout(new BoxLayout(newDayOption, BoxLayout.Y_AXIS));
        JButton enterNew = new JButton("Enter");
        enterNew.addActionListener(this);
        enterNew.setActionCommand("enterNew");
        newDayOption.add(newWelcomeMessage);
        newDayOption.add(newFormatMessage);
        newDayOption.add(dateFieldNew);
        newDayOption.add(enterNew);
        newWelcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        newFormatMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterNew.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    public void displayOldDayPanel() throws IOException {
        JLabel oldWelcomeMessage = new JLabel("Hello. " + extractWeather(getWeather()));
        JLabel oldFormatMessage = new JLabel("Please enter in a date in the format YYYYMMDD");
        oldWelcomeMessage.setFont(oldWelcomeMessage.getFont().deriveFont(FONTSIZE));

        JButton enterOld = new JButton("Enter");
        enterOld.addActionListener(this);
        enterOld.setActionCommand("enterOld");
        oldDayOption = new JPanel(new BorderLayout());
        oldDayOption.setLayout(new BoxLayout(oldDayOption, BoxLayout.Y_AXIS));
        oldDayOption.add(oldWelcomeMessage);
        oldWelcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        oldFormatMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        oldDayOption.add(oldFormatMessage);
        oldDayOption.add(dateFieldOld);
        oldDayOption.add(enterOld);
        enterOld.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("enterNew")) {
                menu.takeInputMenu("a", dateFieldNew.getText());
                new JournalWindow(dateFieldNew.getText());
            } else if (e.getActionCommand().equals("enterOld")) {
                menu.takeInputMenu("b", dateFieldOld.getText());
                new JournalWindow(dateFieldOld.getText());
            }
        } catch (InvalidDateException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date. Try again.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to find file!");
        }
    }
}

