package days.day8;

import java.io.IOException;
import java.util.*;

import days.Day;
import util.InputLoader;

public class Day8 implements Day {
    private final String filename = "input/day8.txt";
    private final int MAX = 99;

    private List<String> input;
    private Tree[][] trees;

    @Override
    public void initialize() {
        System.out.println("Day 08");

        try {
            input = InputLoader.loadList(filename);
            trees = createArray();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        int total = 0;
        for (int y = 0; y < MAX; y++) {
            for (int x = 0; x < MAX; x++) {
                if (checkVisible(x, y))
                    ++total;
                // System.out.printf("(%d, %d) is visible.\n", x, y);
            }
        }
        System.out.println("Total = " + total);
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        int bestScore = 0;
        int bestX = 0, bestY = 0;

        for (int y = 0; y < MAX; y++) {
            for (int x = 0; x < MAX; x++) {
                Tree tree = calcViewingDistances(x, y);
                int score = tree.getScenicScore();
                if (score > bestScore) {
                    bestScore = score;
                    bestX = x;
                    bestY = y;
                    // System.out.printf("(%d, %d) score %d.\n", x, y, score);
                }

                //
            }
        }
        System.out.println("Best Score = " + bestScore + " (" + bestX + ", " + bestY + ")");
    }

    private Tree[][] createArray() {
        Tree[][] t = new Tree[MAX][MAX];
        for (int y = 0; y < input.size(); y++) {
            String row = input.get(y);
            for (int x = 0; x < row.length(); x++) {
                t[x][y] = new Tree(Character.getNumericValue(row.charAt(x)));
            }
        }
        return t;
    }

    private boolean checkVisible(int x, int y) {
        int height = trees[x][y].height;
        boolean visible = true;

        int cx = x, cy = y;

        // check left
        while ((cx > 0)) {
            if ((trees[cx - 1][cy].height >= height))
                visible = false;
            --cx;
        }
        if (visible)
            return true;

        // check right
        cx = x;
        visible = true;
        while (cx < MAX - 1) {
            if ((trees[cx + 1][cy].height >= height))
                visible = false;
            cx++;
        }
        if (visible)
            return true;

        // check up
        cx = x;
        visible = true;
        while (cy > 0) {
            if ((trees[cx][cy - 1].height >= height))
                visible = false;
            --cy;
        }
        if (visible)
            return true;

        // check down
        cy = y;
        visible = true;
        while (cy < MAX - 1) {
            if ((trees[cx][cy + 1].height >= height))
                visible = false;
            cy++;
        }

        return visible;
    }

    private Tree calcViewingDistances(int x, int y) {
        Tree tree = trees[x][y];
        int height = tree.height;

        int distance = 0;
        int cx = x, cy = y;

        // check left
        while ((cx > 0)) {
            distance++;
            if ((trees[cx - 1][cy].height >= height))
                break;

            --cx;
        }
        tree.west = distance;

        // check right
        cx = x;
        distance = 0;
        while (cx < MAX - 1) {
            distance++;
            if ((trees[cx + 1][cy].height >= height))
                break;

            cx++;
        }
        tree.east = distance;

        // check up
        cx = x;
        distance = 0;
        while (cy > 0) {
            distance++;
            if ((trees[cx][cy - 1].height >= height))
                break;

            --cy;
        }
        tree.north = distance;

        // check down
        cy = y;
        distance = 0;
        while (cy < MAX - 1) {
            distance++;
            if ((trees[cx][cy + 1].height >= height))
                break;

            cy++;
        }
        tree.south = distance;

        return tree;
    }

}
