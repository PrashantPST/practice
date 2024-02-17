package design.lld.snakeandladder;

import design.lld.snakeandladder.exceptions.GameAlreadyStartedException;
import design.lld.snakeandladder.models.Board;
import design.lld.snakeandladder.models.BoardEntity;
import design.lld.snakeandladder.strategy.Dice;
import design.lld.snakeandladder.models.Ladder;
import design.lld.snakeandladder.models.Player;
import design.lld.snakeandladder.models.Snake;
import design.lld.snakeandladder.strategy.StandardDice;

import java.util.Arrays;
import java.util.List;

public class SnakeLadderApplication {
    public static void main(String[] args) throws GameAlreadyStartedException {
        BoardEntity snake1 = new Snake(28, 12);
        BoardEntity snake2 = new Snake(78, 34);
        BoardEntity snake3 = new Snake(69, 6);
        BoardEntity snake4 = new Snake(84, 65);

        BoardEntity ladder1 = new Ladder(24, 56);
        BoardEntity ladder2 = new Ladder(43, 83);
        BoardEntity ladder3 = new Ladder(3, 31);
        BoardEntity ladder4 = new Ladder(72, 91);

        Board board = new Board(10, 10);
        board.addBoardEntity(snake1);
        board.addBoardEntity(snake2);
        board.addBoardEntity(snake3);
        board.addBoardEntity(snake4);

        board.addBoardEntity(ladder1);
        board.addBoardEntity(ladder2);
        board.addBoardEntity(ladder3);
        board.addBoardEntity(ladder4);

        Dice dice = StandardDice.builder().start(1).end(6).build();
        Game game = new Game(board, dice);
        Player player1 = Player.builder().id("p1").build();
        Player player2 = Player.builder().id("p2").build();
        Player player3 = Player.builder().id("p3").build();
        List<Player> players = Arrays.asList(player1, player2, player3);
        game.addPlayers(players);
        game.startGame();
    }
}
