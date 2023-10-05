package design.lld.tictactoe.strategies;

import design.lld.tictactoe.models.Board;
import design.lld.tictactoe.models.Cell;
import design.lld.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Player player, Cell cell);
}
