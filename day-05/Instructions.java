import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instructions {
  private List<Instruction> instructions;

  public Instructions() {
    instructions = new ArrayList<Instruction>();
  }

  public void load(String[] data) {

    Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
    for (String line : data) {
      // scan line for tokens
      Matcher matcher = pattern.matcher(line);
      if (matcher.find()) {
        int qty = Integer.parseInt(matcher.group(1));
        int from = Integer.parseInt(matcher.group(2));
        int to = Integer.parseInt(matcher.group(3));

        Instruction instruction = new Instruction(qty, from, to);
        instructions.add(instruction);
      }
    }
  }

  public void execute(CrateStacks stacks) {
    stacks.print();

    for (Instruction instruction : instructions) {
      System.out.println();
      System.out.println(instruction);

      stacks.execute(instruction);

      System.out.println();
      stacks.print();
    }
  }

  public void execute2(CrateStacks stacks) {
    stacks.print();

    for (Instruction instruction : instructions) {
      System.out.println();
      System.out.println(instruction);

      stacks.execute2(instruction);

      System.out.println();
      stacks.print();
    }
  }

}
