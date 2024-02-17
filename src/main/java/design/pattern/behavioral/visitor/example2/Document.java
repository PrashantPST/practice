package design.pattern.behavioral.visitor.example2;

import java.util.ArrayList;
import java.util.List;

public class Document implements DocumentPart {
    private final List<DocumentPart> documentParts = new ArrayList<>();

    public String getArticleMetadata() {
        return "Created in 2014";
    }

    public void addDocumentPart(DocumentPart documentPart) {
        documentParts.add(documentPart);
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
        for (DocumentPart documentPart : documentParts) {
            documentPart.accept(visitor);
        }
    }

    public String getDocumentMetadata() {
        return "document metadata";
    }
}
