package days.day6;

import java.io.IOException;
import java.util.List;

import days.Day;
import util.InputLoader;;

public class Day6 implements Day {
    private final String filename = "input/day6.txt";
    private List<String> input;

    @Override
    public void initialize() {
        System.out.println("Day 6");

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

        String line = input.get(0);
        System.out.println(detectStartOfPacket(line, 4));

    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        String line = input.get(0);
        System.out.println(detectStartOfPacket(line, 14));
    }

    private int detectStartOfPacket(String data, int length) {
        for (int pos = length - 1; pos < data.length() - length; pos++) { // first possible position
            String candidate = data.substring(pos, pos + length);
            if (candidate.chars().distinct().count() == length)
                return pos + length;
        }
        return 0;
    }

}
