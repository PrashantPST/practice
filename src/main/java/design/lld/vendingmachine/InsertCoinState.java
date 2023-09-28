package design.lld.vendingmachine;

public class InsertCoinState implements State {

    private final VendingMachine vendingMachine;

    public InsertCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(amount);
        vendingMachine.setCurrentState(vendingMachine.getSelectProductState());
    }

    @Override
    public void selectProduct(int aisleNumber) {
        throw new IllegalArgumentException("no coin inserted yet");
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalArgumentException("no coin in inserted yet");
    }

}
