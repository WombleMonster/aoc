package days.day10;

import java.util.Scanner;

public interface Command {
  public void parse(Scanner scanner);

  public boolean execute(int cycleNo, Registers reg);
}
