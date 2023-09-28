package design.pattern.behavioral.visitor.ex2;

public interface DocumentVisitor {
    void visit(Document document);

    void visit(Header header);

    void visit(Content content);

    void visit(Footer footer);
}
