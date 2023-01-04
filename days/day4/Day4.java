package days.day4;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import days.Day;
import util.InputLoader;

public class Day4 implements Day {
    private final String filename = "input/day4.txt";
    private List<String> input;

    @Override
    public void initialize() {
        System.out.println("Day 4");

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
        for (String line : input) {
            // split by ,
            String[] ranges = line.split(",");
            assert ranges.length == 2;

            // get first range
            int[] range1 = stringToIntRange(ranges[0]);

            // get second range
            int[] range2 = stringToIntRange(ranges[1]);

            // compare ranges
            if (completelyContains(range1, range2)) {
                // System.out.print(range1[0] + " to " + range1[1] + " completely contains ");
                // System.out.println(range2[0] + " to " + range2[1]);

                ++total;
            } else if (completelyContains(range2, range1)) {
                // System.out.print(range2[0] + " to " + range2[1] + " completely contains ");
                // System.out.println(range1[0] + " to " + range1[1]);

                ++total;
            }
        }

        System.out.println("total = " + total);
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        int total = 0;
        for (String line : input) {

            // split by ,
            String[] ranges = line.split(",");
            assert ranges.length == 2;

            // get first range
            int[] range1 = stringToIntRange(ranges[0]);

            // get second range
            int[] range2 = stringToIntRange(ranges[1]);

            // compare ranges
            if (overlapsAtAll(range1, range2) || overlapsAtAll(range2, range1)) {
                // System.out.print(range1[0] + " to " + range1[1] + " overlaps with ");
                // System.out.println(range2[0] + " to " + range2[1]);

                ++total;
            }

        }

        System.out.println("total = " + total);
    }

    private int[] stringToIntRange(String input) {
        String[] rangeString = input.split("-");
        assert rangeString.length == 2;
        int[] range = Stream.of(rangeString).mapToInt(Integer::parseInt).toArray();
        return range;

    }

    private boolean completelyContains(int[] range1, int[] range2) {
        if ((range1[0] <= range2[0]) && (range1[1] >= range2[1]))
            return true;
        return false;
    }

    private boolean overlapsAtAll(int[] range1, int[] range2) {
        if (((range1[0] <= range2[0]) && (range1[1] >= range2[0]))
                || ((range1[1] >= range2[1]) && (range1[0] <= range2[1])))
            return true;
        return false;
    }

}
