package design.lld.tictactoe.strategies;

import design.lld.tictactoe.enums.CellState;
import design.lld.tictactoe.models.Board;
import design.lld.tictactoe.models.Cell;
import design.lld.tictactoe.models.Move;
import design.lld.tictactoe.models.Player;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move move(Player player, Board board) {

        for (int i = 0; i < board.getBoard().size(); ++i) {
            for (int j = 0; j < board.getBoard().size(); ++j) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    return new Move(
                            player,
                            new Cell(i, j)
                    );
                }
            }
        }

        return null;
    }
}
