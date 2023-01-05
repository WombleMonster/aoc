package days.day11;

import java.math.BigInteger;

public class Item {
  public long worryLevel;

  public Item(int initalWorryLevel) {
    worryLevel = initalWorryLevel;
  }

  @Override
  public String toString() {
    return "" + worryLevel;
  }
}
