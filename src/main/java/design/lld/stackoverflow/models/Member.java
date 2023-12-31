package design.lld.stackoverflow.models;


import design.lld.stackoverflow.enums.VoteType;

import java.util.List;

public class Member extends User {
    Account account;
    List<Badge> badges;

    public Member(Account account) {
        super();
    }

    public void addQuestion(Question question) {}
    public void addAnswer(Question question, Answer answer) {}
    public void addTag(Question question, Tag tag) {}
    public void addVote(Entity entity, VoteType vote) {}
    public void addComment(Entity entity, Comment comment) {}
    public List<Badge> getBadge(){return badges;}
    public void flag(Entity entity) {}
}
