import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;;

public class Main {
    private static String filename = "input.txt";
    private static List<String> input;

    public static void main(String args[]) {
        System.out.println("Day XX");

        readInput();

        partOne();
        // partTwo();
    }

    private static void readInput() {
        input = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void partOne() {

    }

    private static void partTwo() {

    }

}
