package design.lld.foodelivery;

import design.lld.cabbooking.models.Location;

public class Restaurant {
    String name;	//storing name as id itself for now. id should be generated and name should be passed in ctor
    boolean isAvail;
    Location location;
    Menu menu;
    RestaurantOwner owner;	//can support multiple owners, but for simplicity, one owner
}
