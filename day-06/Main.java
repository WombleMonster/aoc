import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;;

public class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day 06");

        partOne();
        // partTwo();
    }

    private static void partOne() {
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // just need the first line!
                break;
            }

            System.out.println(detectStartOfPacket(line, 4));
            System.out.println(detectStartOfPacket(line, 14));

        } catch (IOException e) {
            System.out.println(e);
        }

        // System.out.println("total = " + total);
    }

    private static void partTwo() {

    }

    private static int detectStartOfPacket(String data, int length) {
        for (int pos = length - 1; pos < data.length() - length; pos++) { // first possible position
            String candidate = data.substring(pos, pos + length);
            if (candidate.chars().distinct().count() == length)
                return pos + length;
        }
        return 0;
    }

}
