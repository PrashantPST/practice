package design.lld.tictactoe.models;

import design.lld.tictactoe.enums.CellState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Board {

    private final List<List<Cell>> board;

    public Board(int dimension) {
        this.board = new ArrayList<>();

        for (int i = 0; i < dimension; ++i) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < dimension; ++j) {
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void display() {
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board.size(); ++j) {
                if (board.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|   |");
                } else {
                    System.out.printf("| " + board.get(i).get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println("\n");
        }
    }
}
