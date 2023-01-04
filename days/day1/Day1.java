package days.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import days.Day;

public class Day1 implements Day {
    private static String filename = "input/day1.txt";
    private HashMap<Integer, Integer> elves = new HashMap<>();

    public void initialize() {
        System.out.println("Day 1");

        try (BufferedReader reader = new BufferedReader(new FileReader((filename)))) {
            String line;
            int currentElf = 1;
            int calories = 0;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    elves.put(currentElf, calories);
                    currentElf++;
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void partOne() {
        System.out.println("Part One");

        // for (int i : elves.keySet()) {
        // System.out.println(i + " : " + elves.get(i));
        // }

        int highest = elves.values().stream().max(Integer::compareTo).get();
        System.out.println("Highest = " + highest);
    }

    public void partTwo() {
        System.out.println("Part Two");

        int total = elves.entrySet().stream()
                .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                .limit(3)
                .map(p -> p.getValue())
                .reduce((p, t) -> p + t)
                .get();

        System.out.println("Total = " + total);
    }
}