import java.util.ArrayList;
import java.util.List;

public class DirectoryEntry {
  public String filename;
  public DirectoryEntry parent;
  public List<FileEntry> files = new ArrayList<>();
  public List<DirectoryEntry> directories = new ArrayList<>();

  public DirectoryEntry(DirectoryEntry parent, String name) {
    this.parent = parent;
    this.filename = name;
  }

  public DirectoryEntry getDirectory(String name) {
    return directories.stream().filter(entry -> entry.filename.equals(name)).findFirst().get();
  }

  // returns the total size of all files in this directory
  public int getDirectorySize() {
    return files.stream()
        .mapToInt(entry -> entry.filesize)
        .reduce((size, total) -> total + size)
        .orElse(0);
  }

  // returns the total size of all files in this directory
  public int getDirectorySizeRecursive() {
    if (directories.size() == 0)
      return getDirectorySize();

    return directories.stream()
        .mapToInt(entry -> entry.getDirectorySizeRecursive())
        .reduce((size, total) -> total + size)
        .orElse(0)
        +
        getDirectorySize();
  }

  public void print() {
    print(0);
  }

  public void print(int level) {
    String spacer = " ".repeat(level);
    System.out.println(spacer + filename + " (dir) " + getDirectorySizeRecursive());
    for (DirectoryEntry dir : directories) {
      dir.print(level + 1);
    }
  }
}
