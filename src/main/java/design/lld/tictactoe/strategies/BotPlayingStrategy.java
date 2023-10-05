package design.lld.tictactoe.strategies;

import design.lld.tictactoe.models.Board;
import design.lld.tictactoe.models.Move;
import design.lld.tictactoe.models.Player;

public interface BotPlayingStrategy {

    Move move(Player player, Board board);
}
