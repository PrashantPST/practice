package design.pattern.behavioral.visitor.ex2;

public class Main {
    public static void main(String[] args) {
        Document document = new Document();
        document.addDocumentPart(new Header());
        document.addDocumentPart(new Content());
        document.addDocumentPart(new Footer());

        DocumentVisitor documentVisitor = new HtmlDocumentVisitor();
        document.accept(documentVisitor);
    }
}
