package design.lld.shoppingcart;

public class PercentageCoupon implements Coupon {

    private final double percentage;

    public PercentageCoupon(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double apply(Product product) {
        return product.getPrice() * (1 - this.percentage / 100.0);
    }
}

