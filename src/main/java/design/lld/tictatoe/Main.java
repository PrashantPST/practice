package design.lld.tictatoe;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    GameV2 game = new GameV2( 3); // Example board size

    while (true) {
      game.printBoard();
      System.out.println("Current player: " + game.getCurrentPlayer());
      System.out.print("Enter row and column to make a move, or type 'quit' to exit: ");

      if (!scanner.hasNextInt()) {
        String input = scanner.next();
        if ("quit".equalsIgnoreCase(input)) {
          System.out.println("Game has been quit. Thank you for playing!");
          break;
        } else {
          System.out.println("Invalid input. Please try again.");
          continue;
        }
      }

      int row = scanner.nextInt();
      int col = scanner.nextInt();

      if (game.isMoveValid(row, col)) {
        if (game.makeMove(row, col)) {
          game.printBoard();
          break;
        }
      } else {
        System.out.println("Invalid move. Please try again.");
      }
    }
    scanner.close();
  }
}

