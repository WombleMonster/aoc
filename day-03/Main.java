import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day 03");

        // partOne();
        partTwo();
    }

    private static void partOne() {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String rucksack;
            while ((rucksack = reader.readLine()) != null) {
                String s1 = rucksack.substring(0, rucksack.length() / 2);
                String s2 = rucksack.substring(rucksack.length() / 2);
                var common = compareCompartments(s1, s2);
                int score = calcScore(common);
                total += score;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(total);
    }

    private static void partTwo() {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String rucksack;
            while ((rucksack = reader.readLine()) != null) {
                String s1 = rucksack;
                String s2 = reader.readLine();
                String s3 = reader.readLine();

                var common = compareCompartments(s1, s2, s3);
                assert common.size() == 1;

                int score = calcScore(common);
                total += score;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(total);
    }

    private static SortedSet<Character> compareCompartments(String c1, String c2) {
        SortedSet<Character> common = new TreeSet<Character>();

        for (char c : c1.toCharArray()) {
            if (c2.contains(Character.toString(c))) {
                common.add(c);
            }
        }

        return common;
    }

    private static SortedSet<Character> compareCompartments(String c1, String c2, String c3) {
        SortedSet<Character> common = new TreeSet<Character>();

        for (char c : c1.toCharArray()) {
            if (c2.contains(Character.toString(c)))
                if (c3.contains(Character.toString(c))) {
                    common.add(c);
                }
        }

        return common;
    }

    private static int calcScore(SortedSet<Character> chars) {
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
