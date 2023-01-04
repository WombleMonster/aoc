package days.day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import days.Day;
import util.InputLoader;

public class Day7 implements Day {
    private final String filename = "input/day7.txt";

    List<String> input;
    DirectoryTree tree = new DirectoryTree();

    @Override
    public void initialize() {
        System.out.println("Day 7");

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

        tree.process(input);
        // tree.print();

        int total = walkTree(tree.getRoot());
        System.out.println("total = " + total);
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        int smallestDir = calcSmallestDir(tree.getRoot());
        System.out.println("Smallest dir = " + smallestDir);
    }

    // find the sum total of all directories whose content is less than 100000
    private int walkTree(DirectoryEntry root) {
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
    private List<Integer> walkTree2(DirectoryEntry root) {
        List<Integer> list = new ArrayList<>();

        int currentSize = root.getDirectorySizeRecursive();
        list.add(currentSize);

        for (DirectoryEntry current : root.directories) {
            List<Integer> subList = walkTree2(current);
            list.addAll(subList);
        }

        return list;
    }

    private int calcSmallestDir(DirectoryEntry root) {
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

        return smallestDir;
    }

}
