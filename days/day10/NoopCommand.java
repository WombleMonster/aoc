package days.day10;

import java.util.Scanner;

public class NoopCommand implements Command {

  @Override
  public void parse(Scanner scanner) {
    // No parameters

  }

  @Override
  public boolean execute(int cycleNo, Registers reg) {
    // Does nothing. Takes one cycle
    return true;
  }

  @Override
  public String toString() {
    return "noop";
  }

}
