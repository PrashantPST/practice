package design.pattern.behavioral.strategy;

public interface Payment {
    boolean pay(double amount);
    void collectPaymentDetails();
}
