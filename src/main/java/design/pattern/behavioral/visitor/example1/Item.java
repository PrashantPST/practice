package design.pattern.behavioral.visitor.example1;

public interface Item {
    int accept(ShoppingCartVisitor visitor);
}
