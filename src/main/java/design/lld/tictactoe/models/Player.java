package design.lld.tictactoe.models;

import design.lld.tictactoe.enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;


@Getter
@Setter
@AllArgsConstructor
public class Player {

    private String name;
    private char symbol;
    private PlayerType type;

    public Move move(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please tell the row, starting from :");
        int row = scanner.nextInt();

        System.out.println("Please tell the col, starting from 0:");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }
}
