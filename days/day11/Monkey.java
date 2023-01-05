package days.day11;

import java.util.*;

public class Monkey {
  public List<Item> items;
  public long inspected = 0;

  public Monkey(
      MonkeyOperation op,
      int divisor,
      int ifTrue,
      int ifFalse,
      int[] initialItems) {
    operation = op;
    testDivisor = divisor;
    trueMonkey = ifTrue;
    falseMonkey = ifFalse;

    items = new ArrayList<Item>(initialItems.length);
    for (int worryLevel : initialItems)
      items.add(new Item(worryLevel));
  }

  public MonkeyOperation operation;

  public int testDivisor;

  public int trueMonkey;

  public int falseMonkey;

  public void evaluate(Item item) {
    ++inspected;
    item.worryLevel = operation.adjust(item.worryLevel);
    // Part Two problem...
    // item.worryLevel /= 3;

    if (item.worryLevel % testDivisor == 0)
      throwTo(item, trueMonkey);
    else
      throwTo(item, falseMonkey);

    // Part Two solution....
    item.worryLevel = item.worryLevel % (2 * 3 * 5 * 7 * 11 * 13 * 17 * 19);
  }

  public void catchItem(Item item) {
    items.add(item);
  }

  public void throwTo(Item item, int monkeyIndex) {
    Monkey monkey = Monkeys.get(monkeyIndex);
    // items.remove(item);
    monkey.catchItem(item);
  }
}
