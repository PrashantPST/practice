package design.pattern.structural.decorator.burgerking;

public class AvocadoDecorator extends BurgerDecorator {

  public AvocadoDecorator(Burger burger) {
    super(burger);
  }

  @Override
  public String getDescription() {
    return burger.getDescription() + ", Avocado";
  }

  @Override
  public double getCost() {
    return burger.getCost() + 1.00;
  }

  @Override
  public void prepareBurger() {
    burger.prepareBurger();
    System.out.println("Adding Avocado to the burger.");
  }
}
