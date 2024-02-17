package design.lld.tictatoe;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;


@Getter
public class Game {

  private final int boardSize;
  private final Map<String, Character> board = new HashMap<>();
  private final Map<String, Integer> rowCounters = new HashMap<>();
  private final Map<String, Integer> colCounters = new HashMap<>();
  private final Map<String, Integer> diagonalCounters = new HashMap<>();
  private final Map<String, Integer> reverseDiagonalCounters = new HashMap<>();
  private final int WIN_CONDITION;
  private char currentPlayer = 'X'; // Starting player
  private int totalMoves = 0; // Track the number of moves

  public Game(int boardSize) {
    this.boardSize = boardSize;
    this.WIN_CONDITION = boardSize;
  }

  public boolean isMoveValid(int row, int col) {
    if (row <= 0 || row > boardSize || col <= 0 || col > boardSize) {
      return false; // Move is outside the board's range
    }
    String cellKey = row + ":" + col;
    return !board.containsKey(cellKey); // Move is valid if the cell is not already occupied
  }

  public boolean makeMove(int row, int col) {
    if (!isMoveValid(row, col)) {
      System.out.println("Invalid move. Please try again.");
      return false;
    }

    String cellKey = row + ":" + col;
    board.put(cellKey, currentPlayer);
    totalMoves++;

    if (updateCountersAndCheckWin(row, col, currentPlayer)) {
      System.out.println("Player " + currentPlayer + " wins!");
      return true;
    }

    if (totalMoves == boardSize * boardSize) {
      System.out.println("Game over! It's a draw!");
      return true;
    }
    switchPlayer();
    return false;
  }

  private boolean updateCountersAndCheckWin(int row, int col, char player) {
    String rowKey = "row" + row + player;
    String colKey = "col" + col + player;
    String mainDiagonalKey = row == col ? "mainDiagonal" + player : null;
    String reverseDiagonalKey = row == boardSize - col - 1 ? "reverseDiagonal" + player : null;

    rowCounters.merge(rowKey, 1, Integer::sum);
    colCounters.merge(colKey, 1, Integer::sum);

    if (rowCounters.get(rowKey) == WIN_CONDITION || colCounters.get(colKey) == WIN_CONDITION) {
      return true;
    }

    if (mainDiagonalKey != null) {
      diagonalCounters.merge(mainDiagonalKey, 1, Integer::sum);
      if (diagonalCounters.get(mainDiagonalKey) == WIN_CONDITION) {
        return true;
      }
    }
    if (reverseDiagonalKey != null) {
      reverseDiagonalCounters.merge(reverseDiagonalKey, 1, Integer::sum);
      return reverseDiagonalCounters.get(reverseDiagonalKey) == WIN_CONDITION;
    }
    return false;
  }


  private void switchPlayer() {
    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
  }

  public void printBoard() {
    for (int row = 1; row <= boardSize; row++) {
      for (int col = 1; col <= boardSize; col++) {
        String cellKey = row + ":" + col;
        char mark = board.getOrDefault(cellKey, '.');
        System.out.print(mark + " ");
      }
      System.out.println();
    }
  }
}

