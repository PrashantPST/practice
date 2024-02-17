package design.pattern.behavioral.visitor.example1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Fruit implements Item {
    private int pricePerKg;
    private int weight;
    private String name;

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

}
