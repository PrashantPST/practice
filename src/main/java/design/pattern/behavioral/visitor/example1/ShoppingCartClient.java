package design.pattern.behavioral.visitor.example1;

import java.util.Arrays;

public class ShoppingCartClient {
    public static void main(String[] args) {
        Item[] items = new Item[] {
                Book.builder().price(20).isbnNumber("1234").build(),
                Book.builder().price(100).isbnNumber("5678").build(),
                Fruit.builder().pricePerKg(10).weight(2).name("Banana").build(),
                Fruit.builder().pricePerKg(5).weight(5).name("Apple").build()
        };

        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(Item[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        return Arrays.stream(items).mapToInt(item -> item.accept(visitor)).sum();
    }
}
