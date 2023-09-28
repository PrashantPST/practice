package design.pattern.behavioral.strategy;


public class UPIPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        return false;
    }

    @Override
    public void collectPaymentDetails() {

    }
}
