package design.lld.stackoverflow.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Answer extends Entity {

    private final List<Comment> comments;

    public Answer(String content, Member owner) {
        super(content, owner);
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
