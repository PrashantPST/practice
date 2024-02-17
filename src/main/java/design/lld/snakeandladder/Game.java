package design.lld.snakeandladder;

import design.lld.snakeandladder.enums.GameStatus;
import design.lld.snakeandladder.exceptions.GameAlreadyStartedException;
import design.lld.snakeandladder.models.Board;
import design.lld.snakeandladder.models.Player;
import design.lld.snakeandladder.strategy.Dice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private GameStatus status;
    private final Scanner scanner; // Reuse the same Scanner instance

    public Game(Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>();
        this.status = GameStatus.NOT_YET_STARTED;
        this.scanner = new Scanner(System.in); // Initialize scanner here
    }

    public void startGame() {
        this.status = GameStatus.IN_PROGRESS;
        board.printBoard();

        while (!isGameFinished()) {
            Player currentPlayer = players.poll();
            assert currentPlayer != null;
            makeMove(currentPlayer);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getId() + " has completed the game!");
            } else {
                players.add(currentPlayer);
            }
        }

        this.status = GameStatus.FINISHED;
        scanner.close(); // Close the scanner at the end of the game
    }

    private void makeMove(Player currentPlayer) {
        System.out.println(currentPlayer.getId() + "'s turn.");
        System.out.println("Press anything to roll the dice.");
        scanner.nextLine(); // Reading input
        int rollValue = dice.roll();
        System.out.println("Roll value = " + rollValue);
        int targetPosition = calculateTargetPosition(currentPlayer, rollValue);
        updatePlayerPosition(currentPlayer, targetPosition);
    }

    private int calculateTargetPosition(Player player, int rollValue) {
        int targetPosition = player.getCurrentPosition() + rollValue;
        if (targetPosition > board.getTotalCells()) {
            System.out.println("Invalid Move");
            return player.getCurrentPosition();
        }
        return board.hasBoardEntity(targetPosition) ?
                board.getBoardEntity(targetPosition).get().getEnd() : targetPosition;
    }

    private void updatePlayerPosition(Player player, int newPosition) {
        player.setCurrentPosition(newPosition);
        System.out.println("Current position = " + newPosition);
    }

    private boolean isGameFinished() {
        return players.size() <= 1;
    }

    private boolean hasPlayerWon(Player player) {
        return player.getCurrentPosition() == board.getTotalCells();
    }

    public void addPlayers(List<Player> newPlayers) throws GameAlreadyStartedException {
        if (this.status == GameStatus.NOT_YET_STARTED) {
            this.players.addAll(newPlayers);
        } else {
            throw new GameAlreadyStartedException("Can't add players after game started");
        }
    }
}

