package days.day11;

import java.util.Comparator;
import java.util.stream.Stream;

public class Monkeys {
  public static Monkey[] monkeys = null;

  private static void initMonkeys() {

    monkeys = new Monkey[] {
        new Monkey(
            (old) -> old * 11,
            5,
            7,
            4,
            new int[] { 61 }),
        new Monkey(
            (old) -> old + 4,
            2,
            2,
            6,
            new int[] { 76, 92, 53, 93, 79, 86, 81 }),
        new Monkey(
            (old) -> old * 19,
            13,
            5,
            0,
            new int[] { 91, 99 }),
        new Monkey(
            (old) -> old * old,
            7,
            6,
            1,
            new int[] { 58, 67, 66 }),
        new Monkey(
            (old) -> old + 1,
            19,
            3,
            7,
            new int[] { 94, 54, 62, 73 }),
        new Monkey(
            (old) -> old + 3,
            11,
            0,
            4,
            new int[] { 59, 95, 51, 58, 58 }),
        new Monkey(
            (old) -> old + 8,
            3,
            5,
            2,
            new int[] { 87, 69, 92, 56, 91, 93, 88, 73 }),
        new Monkey(
            (old) -> old + 7,
            17,
            3,
            1,
            new int[] { 71, 57, 86, 67, 96, 95 })
    };
  }

  public static Monkey get(int index) {
    if (monkeys == null)
      initMonkeys();

    return monkeys[index];
  }

  public static long getMonkeyBusiness() {
    long total = Stream.of(monkeys)
        .map(m -> m.inspected) // get the # of items inspected
        .sorted(Comparator.reverseOrder()) // highest first
        .limit(2) // take top 2
        .reduce((long) 1, Math::multiplyExact); // multiply results
    return total;
  }

}
