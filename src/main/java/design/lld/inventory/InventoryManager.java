package design.lld.inventory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryManager {
    private final Inventory inventory;
    private final Lock lock = new ReentrantLock();

    public InventoryManager() {
        this.inventory = new Inventory();
    }

    public void addItemsToInventory(Product product, int quantity) {
        lock.lock();
        try {
            inventory.addProduct(product, quantity);
            System.out.println("Added " + quantity + " units of " + product.getName() + " to the inventory.");
        } finally {
            lock.unlock();
        }
    }

    public void removeItemsFromInventory(String productId, int quantity) {
        lock.lock();
        try {
            inventory.removeProduct(productId, quantity);
            System.out.println("Removed " + quantity + " units of product ID " + productId + " from the inventory.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public int checkInventory(String productId) {
        lock.lock();
        try {
            return inventory.getProductQuantity(productId);
        } finally {
            lock.unlock();
        }
    }
}
