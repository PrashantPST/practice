package design.lld.shoppingcart;

public class FixedDiscountCoupon implements Coupon {
    private final double discount;

    public FixedDiscountCoupon(double discount) {
        this.discount = discount;
    }

    @Override
    public double apply(Product product) {
        return Math.max(product.getPrice() - this.discount, 0);
    }
}
