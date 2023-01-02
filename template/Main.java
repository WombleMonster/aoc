import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day XX");

        partOne();
        // partTwo();
    }

    private static void partOne() {
        int total = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // do something clever
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(total);
    }

    private static void partTwo() {

    }


}
