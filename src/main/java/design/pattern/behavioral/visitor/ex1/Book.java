package design.pattern.behavioral.visitor.ex1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Book implements Item {
    private int price;
    private String isbnNumber;

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }

}
