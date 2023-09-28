package design.lld.stackoverflow.models;

import design.lld.stackoverflow.enums.VoteType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public abstract class Entity {
    private final String entityId;
    private final String content;
    private final Map<VoteType, Integer> voteTypeCount;
    private final Member owner;

    public Entity(String content, Member owner) {
        this.entityId = UUID.randomUUID().toString();
        this.content = content;
        this.voteTypeCount = new HashMap<>();
        this.owner = owner;
    }
}
