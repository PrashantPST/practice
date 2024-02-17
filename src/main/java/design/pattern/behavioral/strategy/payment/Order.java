package design.pattern.behavioral.strategy.payment;

import lombok.Getter;

@Getter
public class Order {
    private int totalCost = 0;
    private boolean isClosed = false;

    public void processOrder(Payment strategy) {
        strategy.collectPaymentDetails();
        // Here we could collect and store payment data from the strategy.
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public void setClosed() {
        isClosed = true;
    }
}
