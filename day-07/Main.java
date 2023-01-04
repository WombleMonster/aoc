import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;;

public class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day 07");

        partOne();
        // partTwo();
    }

    private static void partOne() {
        int total = 0;

        List<String> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // do something clever
                input.add(line);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        DirectoryTree tree = new DirectoryTree();
        tree.process(input);
        // tree.print();

        // total = walkTree(tree.getRoot());
        partTwo(tree.getRoot());

        // System.out.println("total = " + total);
    }

    // find the sum total of all directories whose content is less than 100000
    private static int walkTree(DirectoryEntry root) {
        int total = 0;
        int currentSize = root.getDirectorySizeRecursive();
        if (currentSize <= 100000)
            total += currentSize;

        for (DirectoryEntry current : root.directories) {
            total += walkTree(current);
        }

        return total;
    }

    // find all directory sizes (including sub directories)
    private static List<Integer> walkTree2(DirectoryEntry root) {
        List<Integer> list = new ArrayList<>();

        int currentSize = root.getDirectorySizeRecursive();
        list.add(currentSize);

        for (DirectoryEntry current : root.directories) {
            List<Integer> subList = walkTree2(current);
            list.addAll(subList);
        }

        return list;
    }

    private static void partTwo(DirectoryEntry root) {
        int used = root.getDirectorySizeRecursive();
        int unused = 70000000 - used;
        int needed = 30000000 - unused;

        System.out.println("Used: " + used + " Unused: " + unused + " Needed: " + needed);

        List<Integer> list = walkTree2(root);
        int smallestDir = list.stream()
                .filter(i -> i > needed)
                .sorted()
                .findFirst()
                .get();

        System.out.println(smallestDir);
    }

}
