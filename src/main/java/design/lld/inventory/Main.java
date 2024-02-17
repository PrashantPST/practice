package design.lld.inventory;

public class Main {
    public static void main(String[] args) {
        // Create InventoryManager instance
        InventoryManager manager = new InventoryManager();

        // Sample products
        Product apple = new Product("1", "Apple", 0.50);
        Product orange = new Product("2", "Orange", 0.30);

        // Add products in separate threads
        Thread addAppleThread = new Thread(() -> manager.addItemsToInventory(apple, 100));
        Thread addOrangeThread = new Thread(() -> manager.addItemsToInventory(orange, 150));

        // Remove products in separate threads
        Thread removeAppleThread = new Thread(() -> manager.removeItemsFromInventory(apple.getId(), 50));
        Thread removeOrangeThread = new Thread(() -> manager.removeItemsFromInventory(orange.getId(), 30));

        // Start threads
        addAppleThread.start();
        addOrangeThread.start();
        removeAppleThread.start();
        removeOrangeThread.start();

        // Join threads (wait for them to finish)
        try {
            addAppleThread.join();
            addOrangeThread.join();
            removeAppleThread.join();
            removeOrangeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check inventory
        System.out.println("Apple stock: " + manager.checkInventory(apple.getId()));
        System.out.println("Orange stock: " + manager.checkInventory(orange.getId()));
    }
}

