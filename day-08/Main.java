import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;;

public class Main {
    private static String filename = "input.txt";
    private static List<String> input;
    private static Tree[][] trees;

    private static final int MAX = 99;

    public static void main(String args[]) {
        System.out.println("Day 08");

        readInput();
        createArray();

        // partOne();
        partTwo();
    }

    private static void readInput() {
        input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void createArray() {
        trees = new Tree[MAX][MAX];
        for (int y = 0; y < input.size(); y++) {
            String row = input.get(y);
            for (int x = 0; x < row.length(); x++) {
                trees[x][y] = new Tree(Character.getNumericValue(row.charAt(x)));
            }
        }
    }

    private static void partOne() {
        int total = 0;
        for (int y = 0; y < MAX; y++) {
            for (int x = 0; x < MAX; x++) {
                if (checkVisible(x, y))
                    ++total;
                System.out.printf("(%d, %d) is visible.\n", x, y);
            }
        }
        System.out.println("Total = " + total);
    }

    private static void partTwo() {
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
                    System.out.printf("(%d, %d) score %d.\n", x, y, score);
                }

                //
            }
        }
        System.out.println("Best Score = " + bestScore + " (" + bestX + ", " + bestY + ")");
    }

    private static boolean checkVisible(int x, int y) {
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

    private static Tree calcViewingDistances(int x, int y) {
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
