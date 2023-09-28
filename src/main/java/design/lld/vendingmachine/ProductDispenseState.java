package design.lld.vendingmachine;

import design.lld.vendingmachine.inventory.Inventory;
import design.lld.vendingmachine.inventory.Product;

public class ProductDispenseState implements State {

    private final VendingMachine vendingMachine;

    public ProductDispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        throw new IllegalArgumentException("product is getting dispensed");
    }

    @Override
    public void selectProduct(int aisleNumber) {
        throw new IllegalArgumentException("product is getting dispensed");
    }

    @Override
    public void dispense(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);
        inventory.remove(product);
        double change = vendingMachine.getAmount() - product.getPrice();
        vendingMachine.setAmount(change);
        vendingMachine.setCurrentState(vendingMachine.getInsertCoinState());
    }

}
