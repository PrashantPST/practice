package design.lld.vendingmachine;

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.selectProduct("Soda");
        vendingMachine.insertMoney(4.0); // Insufficient amount
        vendingMachine.insertMoney(0.5); // Sufficient amount
        vendingMachine.dispenseProduct(); // Dispense product
    }
}

