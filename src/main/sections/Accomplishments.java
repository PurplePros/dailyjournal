package sections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import elements.AchievedTask;
import elements.GeneralTask;
import functions.Loadable;
import functions.Saveable;

public class Accomplishments implements Loadable, Saveable {

    private ArrayList<GeneralTask> achievements;

    // EFFECT: new empty list of achievements
    public Accomplishments() throws IOException {
        achievements = new ArrayList<GeneralTask>();
        load();
    }

    // EFFECT: prints the accomplishments menu
    public void printMenu() {
        System.out.println("ACCOMPLISHMENTS");
        System.out.println("How would you like to continue?");
        System.out.println("a) View my achievements");
        System.out.println("b) Add an achievement");
        System.out.println("c) Delete an achievement");
        System.out.println("e) Go back to previous menu");
    }

    // MODIFY: this
    // EFFECT: prompts user and determines next steps
    public void runList() throws IOException {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            input = scanner.nextLine();
            if (input.equals("a")) {
                printAchievements();
            } else if (input.equals("b")) {
                addAchievement();
            } else if (input.equals("c")) {
                deleteAchievement();
            } else {
                break;
            }
        }
    }

    // MODIFY: this
    // EFFECT: add an accomplishment
    public void addAchievedTask(AchievedTask action) {
        achievements.add(action);
    }

    // MODIFY: this
    // EFFECT: remove an accomplishment from given index
    public void deleteAchievedTask(int index) {
        achievements.remove(index);
    }

    // MODIFY: this
    // EFFECT: prompts and adds an accomplishment
    public void addAchievement() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        String accomplishment = scanner.nextLine();
        AchievedTask action = new AchievedTask(accomplishment);
        addAchievedTask(action);
        System.out.println("Achievement added!");
        save(achievements);
    }

    // MODIFY: this
    // EFFECT: prompts and deletes an achievement
    public void deleteAchievement() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        deleteAchievedTask(index - 1);
        System.out.println("Achievement removed!");
        save(achievements);
    }

    // EFFECT: prints all the achievements
    public void printAchievements() {
        int i = 1;
        for (GeneralTask s : achievements) {
            System.out.println(i + ". " + s.getAction());
            i++;
        }
    }

    // EFFECT: returns list of achievements
    public ArrayList getList() {
        return achievements;
    }

    // MODIFY:
    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("AchievementsLog.txt"));
        for (String line : lines) {
            ArrayList<String> words = splitOnSpace(line);
            String achievementBuilder = "";
            for (int i = 0; i < words.size(); i++) {
                achievementBuilder = achievementBuilder + words.get(i) + " ";
            }
            AchievedTask t = new AchievedTask(achievementBuilder);
            achievements.add(t);
        }
    }

    // EFFECT:
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void save(ArrayList<GeneralTask> tasks) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("AchievementsLog.txt", "UTF-8");
        for (GeneralTask t : tasks) {
            writer.println(t.getAction());
        }
        writer.close();
    }
}
