package design.lld.vendingmachine;

public class DispensingProduct implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String productId) {
        System.out.println("Dispensing in progress, please wait.");
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Dispensing in progress, please wait.");
    }
    @Override
    public void dispenseProduct(VendingMachine machine) {
        String selectedProduct = machine.getSelectedProduct();
        System.out.println("Dispensing product: " + selectedProduct);
        machine.deductPriceFromInsertedMoney(selectedProduct);
        machine.setSelectedProduct(null); // Reset the selected product
        machine.setState(new WaitingForSelection());
    }
}
