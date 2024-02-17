package design.lld.vendingmachine;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class VendingMachine {

    private VendingMachineState state;
    private final Map<String, Double> productPriceMap = new HashMap<>();
    private String selectedProduct;
    private double insertedMoney;
    public VendingMachine() {
        this.state = new WaitingForSelection();
        // Initialize with some products and prices
        productPriceMap.put("Soda", 1.5);
        productPriceMap.put("Water", 1.0);
    }

    public void selectProduct(String productId) {
        state.selectProduct(this, productId);
    }

    public void insertMoney(double amount) {
        state.insertMoney(this, amount);
    }

    public void dispenseProduct() {
        state.dispenseProduct(this);
    }

    public boolean hasSufficientMoney(double amount) {
        this.insertedMoney += amount;
        return insertedMoney >= productPriceMap.getOrDefault(selectedProduct, Double.MAX_VALUE);
    }
    public void deductPriceFromInsertedMoney(String productId) {
        Double price = productPriceMap.getOrDefault(productId, 0.0);
        this.insertedMoney -= price;
        System.out.println("Remaining money: " + this.insertedMoney);
    }
}

