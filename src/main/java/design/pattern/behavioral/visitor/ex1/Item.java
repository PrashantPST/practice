package design.pattern.behavioral.visitor.ex1;

public interface Item {
    int accept(ShoppingCartVisitor visitor);
}
