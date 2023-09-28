package design.lld.snakeladder.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class BoardEntity {
    private int start;
    private int end;

    protected abstract String getId();
}
