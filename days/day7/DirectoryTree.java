package days.day7;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DirectoryTree {

  private DirectoryEntry root = new DirectoryEntry(null, "/");

  private enum State {
    DEFAULT,
    LISTING,
  }

  public DirectoryTree() {

  }

  public DirectoryEntry getRoot() {
    return root;
  }

  public void process(List<String> input) {
    Iterator<String> iter = input.iterator();
    State state = State.DEFAULT;
    DirectoryEntry current = root;

    while (iter.hasNext()) {
      String line = iter.next();
      try (Scanner scanner = new Scanner(line)) {

        while (scanner.hasNext()) {
          String tok = scanner.next();
          if (tok.equals("$")) {
            // get command
            state = State.DEFAULT;
            tok = scanner.next();
            switch (tok) {
              case "cd":
                // process dir change
                tok = scanner.next();
                if (tok.equals("..")) {
                  // pop current dir
                  current = current.parent;
                  assert current != null;
                } else if (tok.equals("/")) {
                  current = root;
                } else {
                  // change to directory "dir"
                  current = current.getDirectory(tok);
                  assert current != null;
                }
                while (scanner.hasNext())
                  scanner.next();
                break;
              case "ls":
                state = State.LISTING;
                while (scanner.hasNext())
                  scanner.next();

                break;

            }
          } else {
            // non-command - either directory or file
            assert state == State.LISTING;

            if (tok.equals("dir")) {
              // directory entry
              tok = scanner.next();
              DirectoryEntry dir = new DirectoryEntry(current, tok);
              current.directories.add(dir);
            } else {
              // file entry
              FileEntry file = new FileEntry();

              file.filesize = Integer.parseInt(tok);
              tok = scanner.next();
              file.filename = tok;

              current.files.add(file);
            }
          }
        }
      }
    }
  }

  public void print() {
    root.print();
  }
}
