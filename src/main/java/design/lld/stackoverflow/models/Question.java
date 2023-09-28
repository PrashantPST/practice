package design.lld.stackoverflow.models;

import java.util.ArrayList;
import java.util.List;

public class Question extends Entity {
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final Integer bounty;
    private final List<Tag> tags;
    private Boolean isOpen;

    public Question(String content, Member owner) {
        super(content, owner);
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.bounty = 0;
        this.tags = new ArrayList<>();
        this.isOpen = true;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

}
