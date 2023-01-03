import java.util.*;

public class CrateStacks {
  private List<Stack<Character>> stacks;

  public CrateStacks() {
    this.stacks = new ArrayList<Stack<Character>>();
    for (int i = 0; i < 9; i++) {
      stacks.add(new Stack<Character>());
    }
  }

  public void load(String[] data) {
    // initialize the stacks from the input data
    for (int i = data.length - 2; i >= 0; --i) {
      // System.out.println(data[i]);
      for (int j = 1, col = 0; j <= 34; j += 4, ++col) {
        // System.out.println("col=" + col + " j=" + j);
        Character c = data[i].charAt(j);
        if (Character.isLetter(c))
          stacks.get(col).push(c);
      }
    }
  }

  // pretty print the stacks
  public void print() {
    // find the largest stack
    int maxSize = stacks.parallelStream().max(Comparator.comparingInt(s -> s.size())).get().size();
    // System.out.println(maxSize);
    for (int j = maxSize - 1; j >= 0; --j) {
      for (int i = 0; i < 9; i++) {
        if (stacks.get(i).size() > j) {
          Character c = stacks.get(i).get(j);
          System.out.print(" [" + c + "] ");
        } else {
          System.out.print("     ");
        }
      }
      System.out.println();
    }
  }

  // execute a single instruction
  public void execute(Instruction instruction) {
    for (int i = 0; i < instruction.qty; i++) {
      Character c = stacks.get(instruction.from - 1).pop();
      stacks.get(instruction.to - 1).push(c);
    }
  }

  public void execute2(Instruction instruction) {
    // use intermediate stack to simulate picking up multiple crates at once
    Stack<Character> inter = new Stack<>();

    for (int i = 0; i < instruction.qty; i++) {
      Character c = stacks.get(instruction.from - 1).pop();
      inter.push(c);
    }

    for (int i = 0; i < instruction.qty; i++) {
      Character c = inter.pop();
      stacks.get(instruction.to - 1).push(c);
    }
  }
}
