package design.lld.vendingmachine.inventory;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private String name;
    private int id;
    private double price;

}
