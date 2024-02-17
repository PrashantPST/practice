package design.pattern.behavioral.observer;

public class ProductAvailabilityNotifier {
    public static void main(String[] args) {
        // Create a product
        Product smartphone = new Product("SuperPhone 5G");

        // Create users
        User john = new User("John");
        User emma = new User("Emma");

        // Users subscribe to notifications
        smartphone.addObserver(john);
        smartphone.addObserver(emma);

        // Simulate product availability change
        smartphone.setAvailability(true);

        // John decides to opt-out of the notifications
        john.unsubscribeFromProduct(smartphone);

        // Simulate another product availability change
        smartphone.setAvailability(false); // Set to unavailable
        smartphone.setAvailability(true);  // And available again
    }
}
