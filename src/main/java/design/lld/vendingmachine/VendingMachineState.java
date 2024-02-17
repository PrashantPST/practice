package design.lld.vendingmachine;

public interface VendingMachineState {
    void selectProduct(VendingMachine machine, String productId);
    void insertMoney(VendingMachine machine, double amount);
    void dispenseProduct(VendingMachine machine);
}

