package design.lld.tictatoe;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;


@Getter
public class Game {

  private final Map<String, Character> board;
  private final Map<String, Integer> rowCounts;
  private final Map<String, Integer> colCounts;
  private final Map<String, Integer> diagonalCounts;
  private final Map<String, Integer> revDiagonalCounts;
  private final int dimension;
  private char currentPlayer = 'X';
  private int totalMoves;

  public Game(int i) {
    this.rowCounts = new HashMap<>();
    this.colCounts = new HashMap<>();
    this.diagonalCounts = new HashMap<>();
    this.revDiagonalCounts = new HashMap<>();
    this.board = new HashMap<>();
    this.dimension = i;
    totalMoves = 0;
  }

  public void printBoard() {
    for (int i = 1; i <= dimension; i++) {
      for (int j = 1; j <= dimension; j++) {
        String key = i + ":" + j;
        if (board.get(key) == null) {
          System.out.print(" . ");
        } else {
          System.out.print(board.get(key) + " ");
        }
      }
      System.out.println();
    }
  }

  public boolean isMoveValid(int row, int col, int size) {
    return row >= 1 && row <= size && col >= 1 && col <= size;
  }

  public boolean makeMove(int row, int col) {
    board.put(row + ":" + col, currentPlayer);
    String rowKey = row + ":" + currentPlayer;
    String colKey = col + ":" + currentPlayer;
    totalMoves++;
    rowCounts.merge(rowKey, 1, Integer::sum);
    colCounts.merge(colKey, 1, Integer::sum);
    if (rowCounts.get(rowKey) == dimension) {
      System.out.println(currentPlayer + " Wins");
      return true;
    }
    if (colCounts.get(colKey) == dimension) {
      System.out.println(currentPlayer +  " Wins");
      return true;
    }
    if (row == col) {
      diagonalCounts.merge(String.valueOf(currentPlayer), 1, Integer::sum);
      if (diagonalCounts.get(String.valueOf(currentPlayer)) == dimension) {
        System.out.println(currentPlayer + " Wins");
        return true;
      }
    }
    if (row + col == dimension + 1) {
      revDiagonalCounts.merge(String.valueOf(currentPlayer), 1, Integer::sum);
      if (revDiagonalCounts.get(String.valueOf(currentPlayer)) == dimension) {
        System.out.println(currentPlayer + " Wins");
        return true;
      }
    }
    if (totalMoves == dimension * dimension) {
      System.out.println("Its a draw!");
      return true;
    }
    this.currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    System.out.println("rowCounts = "+this.rowCounts);
    System.out.println("colCounts = "+this.colCounts);
    System.out.println("diagonalCounts = "+this.diagonalCounts);
    System.out.println("revDiagonalCounts = "+this.revDiagonalCounts);
    return false;
  }
}
