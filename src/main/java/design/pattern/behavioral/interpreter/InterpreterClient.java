package design.pattern.behavioral.interpreter;

public class InterpreterClient {
  public static void main(String[] args) {
    // Represents the expression "1 + 2 - 3"
    Expression expr = new Minus(new Plus(new Number(1), new Number(2)), new Number(3));

    System.out.println("The result of 1 + 2 - 3 is " + expr.interpret());
  }
}

