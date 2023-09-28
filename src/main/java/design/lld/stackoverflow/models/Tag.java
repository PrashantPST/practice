package design.lld.stackoverflow.models;

import java.util.UUID;

public class Tag {
    private final String tagId;
    private final String tag;

    public Tag(String tag) {
        this.tagId = UUID.randomUUID().toString();
        this.tag = tag;
    }
}
