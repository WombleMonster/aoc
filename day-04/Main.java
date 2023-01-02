import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day 04");

        // partOne();
         partTwo();
    }

    private static void partOne() {
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // split by ,
                String[] ranges = line.split(",");
                assert ranges.length == 2;
                
                // get first range
                int[] range1 = stringToIntRange(ranges[0]);
                
                // get second range
                int[] range2 = stringToIntRange(ranges[1]);

                // compare ranges
                if (completelyContains(range1, range2)) {
                    System.out.print(range1[0] + " to " + range1[1] + " completely contains ");
                    System.out.println(range2[0] + " to " + range2[1]);

                    ++total;
                } else if (completelyContains(range2, range1)) {
                    System.out.print(range2[0] + " to " + range2[1] + " completely contains ");
                    System.out.println(range1[0] + " to " + range1[1]);

                    ++total;                    
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("total = " + total);
    }

    private static int[] stringToIntRange(String input) {
        String[] rangeString = input.split("-");
        assert rangeString.length == 2;
        int[] range = Stream.of(rangeString).mapToInt(Integer::parseInt).toArray();
        return range;

    }
    private static boolean completelyContains(int[] range1, int[] range2) {
        if (( range1[0] <= range2[0] ) && (range1[1] >= range2[1])) 
            return true;
        return false;
    }

    private static boolean overlapsAtAll(int[] range1, int[] range2) {
        if (((range1[0] <= range2[0]) && (range1[1] >= range2[0])) || ((range1[1] >= range2[1]) && (range1[0] <= range2[1])))
            return true;
        return false;
    }

    private static void partTwo() {
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // split by ,
                String[] ranges = line.split(",");
                assert ranges.length == 2;
                
                // get first range
                int[] range1 = stringToIntRange(ranges[0]);
                
                // get second range
                int[] range2 = stringToIntRange(ranges[1]);

                // compare ranges
                if (overlapsAtAll(range1, range2) || overlapsAtAll(range2, range1)) {
                    System.out.print(range1[0] + " to " + range1[1] + " overlaps with ");
                    System.out.println(range2[0] + " to " + range2[1]);

                    ++total;
                } 

            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("total = " + total);
    }


}
