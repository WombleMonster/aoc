import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day 05");

        partOne();
        // partTwo();
    }

    private static void partOne() {
        int total = 0;
        ArrayList<String> stacksData = new ArrayList<>();
        ArrayList<String> instructionsData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<String> current = stacksData;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0)
                    current.add(line);
                else
                    current = instructionsData;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("There are " + stacksData.size() + " stacks.");
        System.out.println("There are " + instructionsData.size() + " instructions.");

        CrateStacks crateStacks = new CrateStacks();
        crateStacks.load(stacksData.toArray(new String[stacksData.size()]));

        Instructions instructions = new Instructions();
        instructions.load(instructionsData.toArray(new String[instructionsData.size()]));

        // instructions.execute(crateStacks);
        instructions.execute2(crateStacks);

        System.out.println("total = " + total);
    }

    private static void partTwo() {

    }

}
