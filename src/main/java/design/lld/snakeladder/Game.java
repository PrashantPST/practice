package design.lld.snakeladder;

import design.lld.snakeladder.enums.GameStatus;
import design.lld.snakeladder.exceptions.GameAlreadyStartedException;
import design.lld.snakeladder.models.Board;
import design.lld.snakeladder.strategy.Dice;
import design.lld.snakeladder.models.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    Board board;
    Dice dice;
    Queue<Player> players;
    GameStatus status;

    public Game(Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>();
        this.status = GameStatus.NOT_YET_STARTED;
    }

    public void startGame() {
        this.status = GameStatus.IN_PROGRESS;
        board.printBoard();
        while (players.size() > 1) {
            Player currentPlayer = players.poll();
            makeMove(currentPlayer);
            if (currentPlayer.getCurrentPosition() == board.getTotalCells()) {
                System.out.println(currentPlayer.getId() + " has completed the game!");
            } else {
                players.add(currentPlayer);
            }
        }
        this.status = GameStatus.FINISHED;
    }

    private void makeMove(Player currentPlayer) {
        System.out.println(currentPlayer.getId() + "'s turn.");
        System.out.println("Press anything to roll the dice.");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        int playerPosition = currentPlayer.getCurrentPosition();
        int rollValue = dice.roll();
        System.out.println("rollValue = " + rollValue);
        int targetPosition = playerPosition + rollValue;
        if (targetPosition > board.getTotalCells()) {
            System.out.println("Invalid Move");
        } else {
            if (board.hasBoardEntity(targetPosition)) {
                targetPosition = board.getBoardEntity(targetPosition).get().getEnd();
            }
            currentPlayer.setCurrentPosition(targetPosition);
            System.out.println("curr pos = " + targetPosition);
        }
    }

    public void addPlayers(List<Player> players) throws GameAlreadyStartedException {
        if (this.status == GameStatus.NOT_YET_STARTED) {
            this.players.addAll(players);
        } else {
            throw new GameAlreadyStartedException("Can't add players after game started");
        }
    }
}
