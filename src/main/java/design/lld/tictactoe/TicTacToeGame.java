package design.lld.tictactoe;

import design.lld.snakeladder.enums.GameStatus;
import design.lld.tictactoe.controllers.GameController;
import design.lld.tictactoe.enums.BotDifficultyLevel;
import design.lld.tictactoe.enums.PlayerType;
import design.lld.tictactoe.models.Bot;
import design.lld.tictactoe.models.Game;
import design.lld.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) {
        // It will take input: dimension, players
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("What should be the dimension of game?");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any bot? y/n");
        String isBotString = scanner.next();

        List<Player> players = new ArrayList<>();

        int toIterate = dimension - 1;

        if (isBotString.equals("y")) {
            toIterate = dimension - 2;
        }

        for (int i = 0; i < toIterate; ++i) {
            System.out.println("What is the name of player " + i);
            String playerName = scanner.next();

            System.out.println("What is the char of player " + i);
            String playerSymbol = scanner.next();

            players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));
        }

        if (isBotString.equals("y")) {
            System.out.println("What is the name of bot?");
            String playerName = scanner.next();

            System.out.println("What is the char of bot?");
            String playerSymbol = scanner.next();

            players.add(new Bot(playerName, playerSymbol.charAt(0), BotDifficultyLevel.EASY));
        }

        Game game = gameController.createGame(dimension, players);

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {

            System.out.println("This is the current board:");
            gameController.displayBoard(game);
            gameController.executeNextMove(game);
        }

        System.out.println("Game has ended. Result was: ");
        if (!game.getGameStatus().equals(GameStatus.DRAW)) {
            System.out.println("Winner is: " + gameController.getWinner(game).getName());
            game.displayBoard();
        }
    }
}
