package design.pattern.behavioral.visitor.example2;

public class Content implements DocumentPart {
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }

    public String getDocumentContent() {
        return "document content";
    }
}
