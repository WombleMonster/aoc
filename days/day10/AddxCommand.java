package days.day10;

import java.util.Scanner;

public class AddxCommand implements Command {

  private int parameter;

  @Override
  public void parse(Scanner scanner) {
    // one parameter
    parameter = scanner.nextInt();
  }

  // execute the instruction. returns false while there is more work to do...
  @Override
  public boolean execute(int cycleNo, Registers reg) {
    // Adds parameter to X register. Takes two cycles.
    if (cycleNo == 2) {
      reg.X += parameter;
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "addx " + parameter;
  }

}
