package sections;

import java.util.*;
import elements.AchievedTask;

public class Accomplishments {

    private ArrayList<AchievedTask> achievements;

    public Accomplishments() {
        achievements = new ArrayList<AchievedTask>();
    }

    // EFFECT: prints the accomplishments menu
    public void printMenu() {
        System.out.println("ACCOMPLISHMENTS");
        System.out.println("Today's achievements are:");
        printAchievements();
        System.out.println("How would you like to continue?");
        System.out.println("a) Add an achievement");
        System.out.println("b) Delete an achievement");
        System.out.println("c) Go back to previous menu");
    }

    // MODIFY: this
    // EFFECT: determine next steps
    public void runList() {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            input = scanner.nextLine();
            if (input.equals("a")) {
                addAchievement();
            } else if (input.equals("b")) {
                deleteAchievement();
            } else if (input.equals("c")) {
                break;
            } else {
                System.out.println("Request invalid. Please re-enter input.");
            }
        }
    }

    // MODIFY: this
    // EFFECT: add an accomplishment
    public void addAchievedTask(AchievedTask action) {
        achievements.add(action);
    }

    // MODIFY: this
    // EFFECT: remove an accomplishment
    public void deleteAchievedTask(int index) {
        achievements.remove(index);
    }

    // MODIFY: this
    // EFFECT: ask for new achievement information
    public void addAchievement() {
        Scanner scanner = new Scanner(System.in);
        String accomplishment = scanner.nextLine();
        AchievedTask action = new AchievedTask(accomplishment);
        addAchievedTask(action);
        System.out.println("Achievement added!");
    }

    // MODIFY: this
    // EFFECT: ask which achievement to delete
    public void deleteAchievement() {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        deleteAchievedTask(index - 1);
        System.out.println("Achievement removed!");
    }

    // EFFECT: prints all the achievements
    public void printAchievements() {
        int i = 1;
        for (AchievedTask s : achievements) {
            System.out.println(i + ". " + s.getAchievement());
            i++;
        }
    }

    // EFFECT: returns list of achievements
    public ArrayList getList() {
        return achievements;
    }
}
