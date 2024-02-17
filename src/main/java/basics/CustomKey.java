package basics;

import java.util.Objects;

public class CustomKey {

  private final String keyPartOne;
  private final int keyPartTwo;

  public CustomKey(String keyPartOne, int keyPartTwo) {
    this.keyPartOne = keyPartOne;
    this.keyPartTwo = keyPartTwo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomKey customKey = (CustomKey) o;
    return keyPartTwo == customKey.keyPartTwo && Objects.equals(keyPartOne, customKey.keyPartOne);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyPartOne, keyPartTwo);
  }

  @Override
  public String toString() {
    return "CustomKey{" +
        "partOne='" + keyPartOne + '\'' +
        ", partTwo=" + keyPartTwo +
        '}';
  }
}

