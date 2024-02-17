package design.lld.snakeandladder.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class Board {
    private final int rows;
    private final int cols;
    private final Map<Integer, BoardEntity> boardEntities;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.boardEntities = new HashMap<>();
    }

    public void printBoard() {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(1, rows * cols).map(i -> rows * cols + 1 - i).forEach(i -> {
            builder.append(String.format(" | %2d ", i)); // Format for alignment
            boardEntities.getOrDefault(i, new EmptyBoardEntity()).print(builder);
            builder.append(" |");
            if (i % cols == 1) { // New line at the start of each row
                builder.append("\n");
            }
        });
        System.out.println(builder);
    }

    public void addBoardEntity(BoardEntity entity) {
        boardEntities.put(entity.getStart(), entity);
    }

    public Optional<BoardEntity> getBoardEntity(int position) {
        return Optional.ofNullable(boardEntities.get(position));
    }
    public int getTotalCells() {
        return rows * cols;
    }
    public boolean hasBoardEntity(int position) {
        return boardEntities.containsKey(position);
    }
}