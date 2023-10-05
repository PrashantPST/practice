package design.lld.tictactoe.controllers;

import design.lld.snakeladder.enums.GameStatus;
import design.lld.tictactoe.models.Game;
import design.lld.tictactoe.models.Player;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players) {
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        } catch(Exception e) {
            return null;
        }
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
