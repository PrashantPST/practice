package design.pattern.behavioral.visitor.example2;

public interface DocumentPart {

    void accept(DocumentVisitor visitor);

}
