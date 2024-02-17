package design.lld.shoppingcart;

public class CouponDecorator implements Coupon {
    private final Coupon wrappedCoupon;

    public CouponDecorator(Coupon wrappedCoupon) {
        this.wrappedCoupon = wrappedCoupon;
    }

    @Override
    public double apply(Product product) {
        // apply the wrapped coupon's discount
        return wrappedCoupon.apply(product);
    }
}

