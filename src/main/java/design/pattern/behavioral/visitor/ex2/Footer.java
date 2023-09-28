package design.pattern.behavioral.visitor.ex2;

public class Footer implements DocumentPart {

    public String getFooter() {
        return "Document footer";
    }
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
