package design.pattern.structural.decorator.burgerking;

public class DecoratorPatternDemo {

  public static void main(String[] args) {

    // Start with a simple burger
    Burger myBurger = new SimpleBurger();
    System.out.println(
        "Initial Burger: " + myBurger.getDescription() + " | Cost: $" + myBurger.getCost());
    myBurger.prepareBurger();

    // Add cheese to the burger
    myBurger = new CheeseDecorator(myBurger);
    System.out.println(
        "After Adding Cheese: " + myBurger.getDescription() + " | Cost: $" + myBurger.getCost());

    // Add bacon to the burger
    myBurger = new BaconDecorator(myBurger);
    System.out.println(
        "After Adding Bacon: " + myBurger.getDescription() + " | Cost: $" + myBurger.getCost());

    // Now add avocado to the burger
    myBurger = new AvocadoDecorator(myBurger);
    System.out.println(
        "Final Burger: " + myBurger.getDescription() + " | Cost: $" + myBurger.getCost());

    // Preparing the final burger with all customizations
    System.out.println("\nPreparing your customized burger:");
    myBurger.prepareBurger();
  }
}