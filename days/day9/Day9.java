package days.day9;

import java.io.IOException;
import java.util.*;

import days.Day;
import util.InputLoader;

public class Day9 implements Day {
    private final String filename = "input/day9.txt";
    private List<String> input;

    @Override
    public void initialize() {
        System.out.println("Day 9");

        try {
            input = InputLoader.loadList(filename);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        System.out.println(input);
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

    }
}