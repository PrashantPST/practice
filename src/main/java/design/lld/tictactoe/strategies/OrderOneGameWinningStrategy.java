package design.lld.tictactoe.strategies;

import design.lld.tictactoe.models.Board;
import design.lld.tictactoe.models.Cell;
import design.lld.tictactoe.models.Player;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {
    @Override
    public boolean checkWinner(Board board, Player player, Cell cell) {
        return false;
    }
}
