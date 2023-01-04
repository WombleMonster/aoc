package days.day5;

public class Instruction {
  public int qty;
  public int from;
  public int to;

  public Instruction(int qty, int from, int to) {
    this.qty = qty;
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return "move " + qty + " from " + from + " to " + to;
  }
}
