package design.pattern.structural.decorator.burgerking;

public class SimpleBurger implements Burger {

  @Override
  public String getDescription() {
    return "Bread, Patty";
  }

  @Override
  public double getCost() {
    return 3.00; // base price
  }

  @Override
  public void prepareBurger() {
    System.out.println("Preparing a simple burger with Bread and Patty.");
  }
}
