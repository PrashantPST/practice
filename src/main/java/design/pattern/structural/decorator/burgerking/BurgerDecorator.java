package design.pattern.structural.decorator.burgerking;

public abstract class BurgerDecorator implements Burger {

  protected Burger burger;

  public BurgerDecorator(Burger burger) {
    this.burger = burger;
  }

  @Override
  public String getDescription() {
    return burger.getDescription();
  }

  @Override
  public double getCost() {
    return burger.getCost();
  }
}

