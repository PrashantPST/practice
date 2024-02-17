package design.lld.snakeandladder.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class BoardEntity {

    private final int start;
    private final int end;
    protected abstract String getId();
    protected abstract void print(StringBuilder builder);
}
