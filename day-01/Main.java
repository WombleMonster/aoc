import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Main {
    private static String filename = "input.txt";

    public static void main(String args[]) {
        System.out.println("Day 01");

        HashMap<Integer, Integer> elves = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader((filename)))) {
            String line;
            int currentElf = 1;
            int calories = 0;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    elves.put(currentElf, calories);
                    currentElf++;
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        int total = elves.entrySet().stream()
                .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                .limit(3)
                .map(p -> p.getValue())
                .reduce((p, t) -> p + t)
                .get();

        System.out.println(total);
        // for (int i : elves.keySet()) {
        // System.out.println(i + " : " + elves.get(i));
        // }
    }
}