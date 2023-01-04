package days.day2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import days.Day;
import util.InputLoader;

public class Day2 implements Day {
    private final String filename = "input/day2.txt";
    private List<String> input;

    @Override
    public void initialize() {
        System.out.println("Day 2");

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

        ArrayList<Integer> scores = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            while (scanner.hasNext()) {
                String opponent = scanner.next();
                String player = scanner.next();
                int score = calculateScore(opponent.charAt(0), player.charAt(0));
                scores.add(score);
                // System.out.println(score);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        int total = scores.stream().reduce((s, t) -> s + t).get();
        System.out.println(total);

    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        ArrayList<Integer> scores = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            while (scanner.hasNext()) {
                String opponent = scanner.next();
                String strategy = scanner.next();
                Character player = chooseMove(opponent.charAt(0), strategy.charAt(0));
                int score = calculateScore(opponent.charAt(0), player);
                scores.add(score);
                // System.out.println(score);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        int total = scores.stream().reduce((s, t) -> s + t).get();
        System.out.println(total);

    }

    private Character chooseMove(Character opponent, Character strategy) {
        switch (strategy) {
            case 'X': // lose
                switch (opponent) {
                    case 'A':
                        return 'Z';
                    case 'B':
                        return 'X';
                    case 'C':
                        return 'Y';
                }
                break;
            case 'Y': // draw
                switch (opponent) {
                    case 'A':
                        return 'X';
                    case 'B':
                        return 'Y';
                    case 'C':
                        return 'Z';
                }
                break;

            case 'Z': // win
                switch (opponent) {
                    case 'A':
                        return 'Y';
                    case 'B':
                        return 'Z';
                    case 'C':
                        return 'X';
                }
                break;
        }
        throw new IllegalArgumentException();
    }

    private int calculateScore(Character opponent, Character player) {
        // X Y Z
        // A 4 8 3
        // B 1 5 9
        // C 7 2 6

        int score = 0;
        switch (opponent) {
            case 'A':
                switch (player) {
                    case 'X':
                        score = 4;
                        break;
                    case 'Y':
                        score = 8;
                        break;
                    case 'Z':
                        score = 3;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown player move '" + player + "'");
                }
                break;
            case 'B':
                switch (player) {
                    case 'X':
                        score = 1;
                        break;
                    case 'Y':
                        score = 5;
                        break;
                    case 'Z':
                        score = 9;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown player move '" + player + "'");
                }
                break;
            case 'C':
                switch (player) {
                    case 'X':
                        score = 7;
                        break;
                    case 'Y':
                        score = 2;
                        break;
                    case 'Z':
                        score = 6;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown player move '" + player + "'");
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown opponent move '" + opponent + "'");
        }
        return score;
    }

}
