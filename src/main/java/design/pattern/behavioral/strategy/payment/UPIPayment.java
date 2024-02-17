package design.pattern.behavioral.strategy.payment;


public class UPIPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public void collectPaymentDetails() {

    }
}
