package days.day9;

import java.io.IOException;
import java.util.*;

import days.Day;
import days.day9.Position.Direction;
import util.InputLoader;

public class Day9 implements Day {
    private final String filename = "input/day9.txt";
    private List<String> input;
    private List<Direction> moves;

    @Override
    public void initialize() {
        System.out.println("Day 9");

        try {
            input = InputLoader.loadList(filename);
            moves = createMoves(input);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        // both start at origin
        Position head = new Position(0, 0);
        Position tail = new Position(0, 0);
        List<Position> visited = new ArrayList<Position>();

        visited.add(tail); // add initial position

        for (Direction dir : moves) {

            // move head
            head = head.move(dir);
            System.out.println("Head at " + head);

            // if tail and head aren't touching
            if (!head.isTouching(tail)) {
                System.out.println("Not touching!");

                // move tail
                tail = tail.moveTowards(head);
                System.out.println("Tail at " + tail);

                // if new location, add to visited list
                if (!visited.contains(tail)) {
                    visited.add(tail);
                }
            }
        }

        System.out.println("Total visited = " + visited.size());

    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        Position[] rope = new Position[10];

        // initialize all knots to origin
        for (int i = 0; i < rope.length; i++) {
            rope[i] = new Position(0, 0);
        }

        List<Position> visited = new ArrayList<Position>();

        // add initial position
        visited.add(rope[0]);

        for (Direction dir : moves) {
            // move head

            rope[0] = rope[0].move(dir);
            System.out.println("Head at " + rope[0]);

            // if tail and head aren't touching
            for (int i = 1; i < rope.length; i++) {
                if (!rope[i].isTouching(rope[i - 1])) {
                    System.out.println("Not touching!");

                    // move tail
                    rope[i] = rope[i].moveTowards(rope[i - 1]);
                    System.out.printf("Knot[%d] at %s\n", i, rope[i]);

                }
            }

            // if _taile_ at new location, add to visited list
            if (!visited.contains(rope[9])) {
                visited.add(rope[9]);
            }
        }

        System.out.println("Total visited = " + visited.size());
    }

    private List<Direction> createMoves(List<String> input) {
        var list = new LinkedList<Direction>();

        for (String line : input) {
            try (var scanner = new Scanner(line)) {
                String directionString = scanner.next();
                Direction direction = Position.directionFromString(directionString);

                int repeat = scanner.nextInt();

                for (int i = 0; i < repeat; i++)
                    list.add(direction);
            }
        }
        return list;
    }
}