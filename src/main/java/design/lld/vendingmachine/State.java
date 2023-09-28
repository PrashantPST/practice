package design.lld.vendingmachine;

public interface State {

    //operations possible by any concrete state
    void insertCoin(double amount);
    void selectProduct(int aisleNumber);
    void dispense(int aisleNumber);

}
