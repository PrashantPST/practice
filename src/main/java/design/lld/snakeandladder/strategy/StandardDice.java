package design.lld.snakeandladder.strategy;

import lombok.Builder;
import org.apache.commons.lang3.RandomUtils;

@Builder
public class StandardDice implements Dice {

    private final int start;
    private final int end;
    @Override
    public int roll() {
        return RandomUtils.nextInt(start, end + 1);
    }
}
