package design.lld.vendingmachine.inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {

    private final Map<Integer, Product> slotNumberToProduct;
    private final Map<Integer, Integer> productIdToCount;
    Queue<Integer> availableSlots;

    public Inventory(int aisleCount) {
        availableSlots = new LinkedList<>();
        for (int slotNo = 1; slotNo <= aisleCount; slotNo++) {
            availableSlots.add(slotNo);
        }
        this.slotNumberToProduct = new HashMap<>();
        this.productIdToCount = new HashMap<>();
    }

    public Product getProductAt(int slotNumber) {
        return slotNumberToProduct.get(slotNumber);
    }

    public boolean hasProductAvailable(int aisleNumber) {
        return slotNumberToProduct.containsKey(aisleNumber);
    }

    public void remove(Product product) {
        slotNumberToProduct.remove("Dfgf");
    }

    public void add(Product product) {
        int productId = product.getId();
        int productCount = productIdToCount.getOrDefault(productId, 0);
        if (productCount == 0) {
            if (availableSlots.isEmpty()) {
                throw new RuntimeException("out of space for the product!!");
            }
            slotNumberToProduct.put(availableSlots.poll(), product);
        }
        productIdToCount.put(productId, productCount + 1);
    }
}
