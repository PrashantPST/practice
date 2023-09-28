package design.lld.snakeladder.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    int rows;
    int cols;
    Map<Integer, BoardEntity> boardEntities;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.boardEntities = new HashMap<>();
    }

    public void printBoard() {
        int totalCells = rows * cols;
        for (int i = totalCells; i > 0; i--) {
            System.out.print(" | " + i + " ");
            if (hasBoardEntity(i)) {
                System.out.print(boardEntities.get(i).getId());
            }
            System.out.print(" |");
            if (i % cols == 0) {
                System.out.println();
            }
        }
    }

    public int getTotalCells() {
        return rows * cols;
    }

    public void addBoardEntity(BoardEntity entity) {
        int startPosition = entity.getStart();
        boardEntities.put(startPosition, entity);
    }

    public boolean hasBoardEntity(int position) {
        return boardEntities.containsKey(position);
    }

    public Optional<BoardEntity> getBoardEntity(int position) {
        if (hasBoardEntity(position)) {
            return Optional.ofNullable(boardEntities.get(position));
        }
        return Optional.empty();
    }
}
