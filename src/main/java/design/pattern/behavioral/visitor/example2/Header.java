package design.pattern.behavioral.visitor.example2;

public class Header implements DocumentPart {

    public String getTitle() {
        return "Document title";
    }
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
