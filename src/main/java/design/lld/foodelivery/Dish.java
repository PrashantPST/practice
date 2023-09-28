package design.lld.foodelivery;

import design.lld.foodelivery.enums.CUISINE;

import java.util.List;

public class Dish {
    String name;
    CUISINE cuisine;
    String description;
    double price;
    List<String> dishImages;
    List<DishAddOn> addons;
}
