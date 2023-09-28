package design.lld.snakeladder.strategy;

import lombok.Builder;
import org.apache.commons.lang3.RandomUtils;

@Builder
public class StandardDice implements Dice {

    private int start;
    private int end;
    @Override
    public int roll() {
        return RandomUtils.nextInt(start, end + 1);
    }
}
