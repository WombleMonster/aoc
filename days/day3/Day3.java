package days.day3;

import java.io.IOException;
import java.util.*;

import days.Day;
import util.InputLoader;

public class Day3 implements Day {
    private final String filename = "input/day3.txt";
    private List<String> input;

    @Override
    public void initialize() {
        System.out.println("Day 3");

        try {
            input = InputLoader.loadList(filename);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        int total = 0;
        for (String rucksack : input) {
            String s1 = rucksack.substring(0, rucksack.length() / 2);
            String s2 = rucksack.substring(rucksack.length() / 2);
            var common = compareCompartments(s1, s2);
            int score = calcScore(common);
            total += score;
        }

        System.out.println("Total = " + total);
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        int total = 0;
        Iterator<String> iter = input.iterator();
        while (iter.hasNext()) {
            String s1 = iter.next();
            String s2 = iter.next();
            String s3 = iter.next();

            var common = compareCompartments(s1, s2, s3);
            assert common.size() == 1;

            int score = calcScore(common);
            total += score;
        }

        System.out.println("Total = " + total);
    }

    private SortedSet<Character> compareCompartments(String c1, String c2) {
        SortedSet<Character> common = new TreeSet<Character>();

        for (char c : c1.toCharArray()) {
            if (c2.contains(Character.toString(c))) {
                common.add(c);
            }
        }

        return common;
    }

    private SortedSet<Character> compareCompartments(String c1, String c2, String c3) {
        SortedSet<Character> common = new TreeSet<Character>();

        for (char c : c1.toCharArray()) {
            if (c2.contains(Character.toString(c)))
                if (c3.contains(Character.toString(c))) {
                    common.add(c);
                }
        }

        return common;
    }

    private int calcScore(SortedSet<Character> chars) {
        int score = 0;
        for (Character c : chars) {
            if (Character.isLowerCase(c)) {
                int value = ((int) c) - ((int) 'a') + 1;
                score += value;
            } else {
                int value = ((int) c) - ((int) 'A') + 27;
                score += value;
            }
        }
        return score;
    }

}
