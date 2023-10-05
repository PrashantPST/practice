package design.lld.tictactoe.factories;

import design.lld.tictactoe.enums.BotDifficultyLevel;
import design.lld.tictactoe.strategies.BotPlayingStrategy;
import design.lld.tictactoe.strategies.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        return new RandomBotPlayingStrategy();
    }
}
