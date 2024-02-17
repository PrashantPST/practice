package design.lld.shoppingcart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products = new ArrayList<>();
    private Coupon coupon;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product product : products) {
            double productPrice = coupon != null ? coupon.apply(product) : product.getPrice();
            total += productPrice;
        }
        return total;
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Special Cereal", 5.0, ProductType.BAKERY));

        Coupon baseCoupon = new PercentageCoupon(10); // 10% discount
        Coupon extraCoupon = new FixedDiscountCoupon(1); // Additional $1 discount
        Coupon combinedCoupon = new CombinedCoupon(baseCoupon, extraCoupon);

        cart.applyCoupon(combinedCoupon);

        double total = cart.calculateTotal();
        System.out.println("Total cost after applying combined coupons: " + total);
    }
}
