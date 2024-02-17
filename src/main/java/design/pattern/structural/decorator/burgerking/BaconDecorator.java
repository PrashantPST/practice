package design.pattern.structural.decorator.burgerking;

public class BaconDecorator extends BurgerDecorator {

  public BaconDecorator(Burger burger) {
    super(burger);
  }

  @Override
  public String getDescription() {
    return burger.getDescription() + ", Bacon";
  }

  @Override
  public double getCost() {
    return burger.getCost() + 0.75;
  }

  @Override
  public void prepareBurger() {
    burger.prepareBurger();
    System.out.println("Adding Bacon to the burger.");
  }
}