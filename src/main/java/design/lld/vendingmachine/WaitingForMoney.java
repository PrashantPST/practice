package design.lld.vendingmachine;

public class WaitingForMoney implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Product already selected, insert money to continue.");
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        if (machine.hasSufficientMoney(amount)) {
            System.out.println("Money inserted: " + amount);
            machine.setState(new DispensingProduct());
        } else {
            System.out.println("Insufficient money, please insert more.");
        }
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please insert money first.");
    }
}

