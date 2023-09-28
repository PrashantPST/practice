package design.lld.vendingmachine;


import design.lld.vendingmachine.inventory.Inventory;
import design.lld.vendingmachine.inventory.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendingMachine {

    private double amount;
    private Inventory inventory;
    private State insertCoinState;
    private State selectProductState;
    private State productDispenseState;
    private State currentState;
    private static final int AISLE_COUNT = 2;

    public VendingMachine() {
        insertCoinState = new InsertCoinState(this);
        selectProductState = new SelectProductState(this);
        productDispenseState = new ProductDispenseState(this);
        currentState = insertCoinState;
        amount = 0;
        inventory = new Inventory(AISLE_COUNT);
    }

    public boolean hasSufficientAmount(double price) {
        return this.amount >= price;
    }

    public void addProduct(Product product) {
        this.inventory.add(product);
    }

    public void insertCoin(double amount) {
        this.currentState.insertCoin(amount);
        System.out.println(amount + " is inserted");
    }

    public void pressButton(int slotNumber) {
        this.currentState.selectProduct(slotNumber);
        this.currentState.dispense(slotNumber);
    }
}
