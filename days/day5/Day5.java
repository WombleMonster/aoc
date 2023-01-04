package days.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import days.Day;

public class Day5 implements Day {
    private final String filename = "input/day5.txt";

    private List<String> stacksData = new ArrayList<>();
    private List<String> instructionsData = new ArrayList<>();

    @Override
    public void initialize() {
        System.out.println("Day 5");

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
            System.exit(1);
        }
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        int total = 0;

        System.out.println("There are " + stacksData.size() + " stacks.");
        System.out.println("There are " + instructionsData.size() + " instructions.");

        CrateStacks crateStacks = new CrateStacks();
        crateStacks.load(stacksData.toArray(new String[stacksData.size()]));

        Instructions instructions = new Instructions();
        instructions.load(instructionsData.toArray(new String[instructionsData.size()]));

        instructions.execute(crateStacks);

        System.out.println("total = " + total);
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        int total = 0;

        System.out.println("There are " + stacksData.size() + " stacks.");
        System.out.println("There are " + instructionsData.size() + " instructions.");

        CrateStacks crateStacks = new CrateStacks();
        crateStacks.load(stacksData.toArray(new String[stacksData.size()]));

        Instructions instructions = new Instructions();
        instructions.load(instructionsData.toArray(new String[instructionsData.size()]));

        instructions.execute2(crateStacks);

        System.out.println("total = " + total);
    }
}
