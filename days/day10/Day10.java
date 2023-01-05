package days.day10;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.*;
import util.InputLoader;

import days.Day;

public class Day10 implements Day {
    private final String filename = "input/day10.txt";
    private List<String> input;
    private List<Command> commands = new ArrayList<>();
    private List<Integer> interestingCycles = Arrays.asList(20, 60, 100, 140, 180, 220);

    @Override
    public void initialize() {
        System.out.println("Day 10");

        try {
            input = InputLoader.loadList(filename);
            for (String line : input) {
                try (var scanner = new Scanner(line)) {
                    Command command;
                    String tok = scanner.next();
                    switch (tok) {
                        case "noop":
                            command = new NoopCommand();
                            break;
                        case "addx":
                            command = new AddxCommand();
                            break;
                        default:
                            command = null;
                    }
                    if (command == null)
                        throw new UnexpectedException("unkown token " + tok);
                    command.parse(scanner);
                    commands.add(command);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Override
    public void partOne() {
        System.out.println("Part One");

        int total = 0;
        Registers regs = new Registers();
        regs.X = 1;

        Iterator<Command> iter = commands.iterator();
        Command command = iter.next();
        int instructionCycle = 1;
        int machineCycle = 1;
        while (true) {
            // System.out.printf("machineCycle %d: executing '%s'\n", machineCycle,
            // command);

            if (interestingCycles.contains(machineCycle)) {
                // do something
                System.out.printf("* cycle=%d, signal strength=%d *\n", machineCycle, machineCycle * regs.X);
                total += machineCycle * regs.X;
            }

            regs.cycle = machineCycle;
            boolean complete = command.execute(instructionCycle, regs);
            instructionCycle++;
            machineCycle++;

            if (complete) {
                // command complete, begin the next cycle
                if (!iter.hasNext())
                    break; // end the loop
                command = iter.next();
                instructionCycle = 1;
            }
        }
        System.out.println("total = " + total);
    }

    private char[] blankPixelLine() {
        char[] c = new char[40];
        for (int i = 0; i < 40; i++)
            c[i] = '.';
        return c;
    }

    @Override
    public void partTwo() {
        System.out.println("Part Two");

        int total = 0;
        Registers regs = new Registers();
        regs.X = 1;

        Iterator<Command> iter = commands.iterator();
        Command command = iter.next();
        int instructionCycle = 1;
        int machineCycle = 1;
        int pixelPosition = 0;
        char[] pixelLine = blankPixelLine();
        while (true) {
            // System.out.printf("machineCycle %d: executing '%s'\n", machineCycle,
            // command);

            // if (interestingCycles.contains(machineCycle)) {
            // // do something
            // System.out.printf("* cycle=%d, signal strength=%d *\n", machineCycle,
            // machineCycle * regs.X);
            // total += machineCycle * regs.X;
            // }

            // DRAW PIXEL
            if ((pixelPosition == regs.X) || (pixelPosition == regs.X - 1) || (pixelPosition == regs.X + 1)) {
                pixelLine[pixelPosition] = '#';
            }

            // EXECUTE
            regs.cycle = machineCycle;
            boolean complete = command.execute(instructionCycle, regs);

            instructionCycle++;
            machineCycle++;
            pixelPosition++;

            // PRINT LINE
            if (pixelPosition == 40) {
                System.out.println(new String(pixelLine));

                pixelPosition = 0; // wrap
                pixelLine = blankPixelLine();
            }

            if (complete) {
                // command complete, begin the next cycle
                if (!iter.hasNext())
                    break; // end the loop
                command = iter.next();
                instructionCycle = 1;
            }
        }
        System.out.println("total = " + total);
    }
}