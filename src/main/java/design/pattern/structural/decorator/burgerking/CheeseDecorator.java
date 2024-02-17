package design.pattern.structural.decorator.burgerking;

public class CheeseDecorator extends BurgerDecorator {
  public CheeseDecorator(Burger burger) {
    super(burger);
  }

  @Override
  public String getDescription() {
    return burger.getDescription() + ", Cheese";
  }

  @Override
  public double getCost() {
    return burger.getCost() + 0.50;
  }

  @Override
  public void prepareBurger() {
    burger.prepareBurger();
    System.out.println("Adding Cheese to the burger.");
  }
}
