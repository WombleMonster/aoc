package days.day11;

import java.io.IOException;
import java.util.*;
import util.InputLoader;

import days.Day;

public class Day11 implements Day {
    private final String filename = "input/day11.txt";
    private List<String> input;

    @Override
    public void initialize() {
        System.out.println("Day 11");

        try {
            input = InputLoader.loadList(filename);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        Monkeys.get(0); // initialize monkeys
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        for (int round = 1; round <= 20; round++) {
            for (int i = 0; i <= 7; i++) {
                Monkey monkey = Monkeys.get(i);
                Iterator<Item> iter = monkey.items.iterator();
                while (iter.hasNext()) {
                    Item item = iter.next();
                    monkey.evaluate(item);
                    iter.remove();
                }
            }
            System.out.printf("Round %d: \n", round);
            for (int i = 0; i <= 7; i++) {
                Monkey monkey = Monkeys.get(i);
                System.out.println("Monkey " + i + ":" + monkey.items);
            }
        }

        for (int i = 0; i <= 7; i++) {
            Monkey monkey = Monkeys.get(i);
            System.out.printf("Monkey %d inspected items %d times\n", i, monkey.inspected);
        }

        System.out.println("Total Monkey Business = " + Monkeys.getMonkeyBusiness());

    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        for (int round = 1; round <= 10000; round++) {
            for (int i = 0; i <= 7; i++) {
                Monkey monkey = Monkeys.get(i);
                Iterator<Item> iter = monkey.items.iterator();
                while (iter.hasNext()) {
                    Item item = iter.next();
                    monkey.evaluate(item);
                    iter.remove();
                }
            }
            System.out.printf("Round %d: \n", round);
            for (int i = 0; i <= 7; i++) {
                Monkey monkey = Monkeys.get(i);
                System.out.println("Monkey " + i + ":" + monkey.items);
            }
        }

        for (int i = 0; i <= 7; i++) {
            Monkey monkey = Monkeys.get(i);
            System.out.printf("Monkey %d inspected items %d times\n", i, monkey.inspected);
        }

        System.out.println("Total Monkey Business = " + Monkeys.getMonkeyBusiness());

    }
}