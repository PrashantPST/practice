package design.lld.vendingmachine;

import design.lld.vendingmachine.inventory.Inventory;
import design.lld.vendingmachine.inventory.Product;

public class SelectProductState implements State {

    private final VendingMachine vendingMachine;

    public SelectProductState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(amount + vendingMachine.getAmount());
    }

    @Override
    public void selectProduct(int slotNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(slotNumber);
        if (!vendingMachine.hasSufficientAmount(product.getPrice())) {
            throw new IllegalArgumentException("insufficient amount to buy this product");
        }
        if (!inventory.hasProductAvailable(slotNumber)) {
            throw new IllegalArgumentException("not available yet");
        }
        vendingMachine.setCurrentState(vendingMachine.getProductDispenseState());
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalArgumentException("product not chosen yet");
    }

}
