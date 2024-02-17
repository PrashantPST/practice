package design.pattern.behavioral.interpreter;

public class Number implements Expression {
  private final int number;

  public Number(int number) {
    this.number = number;
  }

  @Override
  public int interpret() {
    return number;
  }
}

