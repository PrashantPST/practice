package design.lld.vendingmachine;

public class WaitingForSelection implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Product selected: " + productId);
        machine.setSelectedProduct(productId);
        machine.setState(new WaitingForMoney());
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please select a product first.");
    }
}

