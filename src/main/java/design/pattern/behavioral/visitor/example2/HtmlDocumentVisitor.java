package design.pattern.behavioral.visitor.example2;

public class HtmlDocumentVisitor implements DocumentVisitor {
    @Override
    public void visit(Document document) {
        System.out.println("Generating document metadata HTML markup: "
                + document.getDocumentMetadata());
    }

    @Override
    public void visit(Header header) {
        System.out.println("Generating document header HTML markup: "
                + header.getTitle());
    }

    @Override
    public void visit(Content content) {
        System.out.println("Generating document content HTML markup: "
                + content.getDocumentContent());
    }

    @Override
    public void visit(Footer footer) {
        System.out.println("Generating document footer HTML markup: "
                + footer.getFooter());
    }
}
