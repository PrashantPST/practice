package design.pattern.behavioral.visitor.ex1;

public interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Fruit fruit);
}
