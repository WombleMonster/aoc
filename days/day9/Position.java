package days.day9;

public class Position {
  public int x;
  public int y;

  public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
  }

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Position move(Direction direction) {
    switch (direction) {
      case NORTH:
        return new Position(x, y + 1);
      case SOUTH:
        return new Position(x, y - 1);
      case WEST:
        return new Position(x - 1, y);
      case EAST:
        return new Position(x + 1, y);
      default:
        return null;
    }
  }

  public boolean isTouching(Position other) {
    // touching is abs(x2-x1)<=1 && abs(y2-y1)<=1
    return ((Math.abs(other.x - x) <= 1) && (Math.abs(other.y - y) <= 1));
  }

  public Position moveTowards(Position other) {
    int newX, newY;

    if (isTouching(other))
      return this;

    if (x == other.x) {
      // same row
      newX = x;
      newY = y + ((other.y - y) / 2);
    } else if (y == other.y) {
      // same column
      newY = y;
      newX = x + ((other.x - x) / 2);
    } else {
      // diagonal
      if (Math.abs(other.x - x) > 1) {
        newY = other.y;
        newX = x + ((other.x - x) / 2);
      } else {
        newX = other.x;
        newY = y + ((other.y - y) / 2);
      }
    }
    return new Position(newX, newY);
  }

  public static Direction directionFromString(String direction) {
    switch (direction) {
      case "R":
        return Direction.EAST;
      case "D":
        return Direction.SOUTH;
      case "U":
        return Direction.NORTH;
      case "L":
        return Direction.WEST;
      default:
        return null;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Position)) {
      return false;
    }

    Position p = (Position) o;
    return (p.x == x) && (p.y == y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
