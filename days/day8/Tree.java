package days.day8;

public class Tree {

  public int height;

  public boolean visible = false;

  // viewing distances
  public int north = 0;
  public int east = 0;
  public int south = 0;
  public int west = 0;

  public Tree(int height) {
    this.height = height;
  }

  public int getScenicScore() {
    return north * east * south * west;
  }

}
