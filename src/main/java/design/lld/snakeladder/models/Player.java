package design.lld.snakeladder.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Player {
    private final String id;
    private final String name;
    private int currentPosition;
    private boolean won;
}
