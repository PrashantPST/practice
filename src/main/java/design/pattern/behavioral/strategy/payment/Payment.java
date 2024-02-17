package design.pattern.behavioral.strategy.payment;

public interface Payment {
    boolean pay(double amount);
    void collectPaymentDetails();
}
