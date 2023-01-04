package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputLoader {

  public static String[] loadStrings(String filename) throws IOException {
    List<String> list = loadList(filename);
    return list.toArray(new String[list.size()]);
  }

  public static List<String> loadList(String filename) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
    return lines;
  }
}
