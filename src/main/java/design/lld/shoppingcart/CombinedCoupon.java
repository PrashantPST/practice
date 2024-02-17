package design.lld.shoppingcart;

public class CombinedCoupon extends CouponDecorator {
    private final Coupon additionalCoupon;

    public CombinedCoupon(Coupon initialCoupon, Coupon additionalCoupon) {
        super(initialCoupon);
        this.additionalCoupon = additionalCoupon;
    }

    @Override
    public double apply(Product product) {
        double priceAfterFirstCoupon = super.apply(product);
        // Applying the second coupon to the price after the first coupon is applied
        return additionalCoupon.apply(new Product(product.getName(), priceAfterFirstCoupon, product.getType()));
    }
}
