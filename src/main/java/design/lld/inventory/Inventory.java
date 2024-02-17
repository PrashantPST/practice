package design.lld.inventory;

import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

    private final ConcurrentHashMap<String, Integer> productStock;

    public Inventory() {
        productStock = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        productStock.merge(product.getId(), quantity, Integer::sum);
    }

    public void removeProduct(String productId, int quantity) throws Exception {
        productStock.computeIfPresent(productId, (id, currentStock) -> {
            if (currentStock < quantity) {
                throw new RuntimeException("Insufficient stock for product: " + productId);
            }
            return currentStock - quantity;
        });
        if (!productStock.containsKey(productId)) {
            throw new Exception("Product not found or insufficient stock for product: " + productId);
        }
    }
    public int getProductQuantity(String productId) {
        return productStock.getOrDefault(productId, 0);
    }
}

