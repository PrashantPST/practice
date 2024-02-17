package design.pattern.behavioral.observer;

public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Product product) {
        if (product.isAvailable()) {
            System.out.println("Hi " + name + ", " + product.getName() + " is now available!");
        }
    }

    public void unsubscribeFromProduct(Product product) {
        product.removeObserver(this);
        System.out.println(name + " has unsubscribed from " + product.getName() + " notifications.");
    }
}
