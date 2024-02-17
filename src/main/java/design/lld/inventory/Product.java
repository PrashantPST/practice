package design.lld.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private final String id;
    private final String name;
    private final double price;
}

