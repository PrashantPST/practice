package design.lld.tictactoe.models;

import design.lld.tictactoe.enums.BotDifficultyLevel;
import design.lld.tictactoe.enums.PlayerType;
import design.lld.tictactoe.factories.BotPlayingStrategyFactory;
import design.lld.tictactoe.strategies.BotPlayingStrategy;
import lombok.Getter;


@Getter
public class Bot extends Player {

    private final BotDifficultyLevel botDifficultyLevel;
    private final BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char aSymbol, BotDifficultyLevel difficultyLevel) {
        super(name, aSymbol, PlayerType.BOT);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategyForDifficultyLevel(difficultyLevel);
    }

    @Override
    public Move move(Board board) {
        return botPlayingStrategy.move(this, board);
    }
}
