package design.lld.shoppingcart;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final String name;
    private final double price;
    private final ProductType type;
}

