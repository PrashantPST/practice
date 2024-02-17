package design.lld.snakeandladder.models;

public class EmptyBoardEntity extends BoardEntity {
    EmptyBoardEntity() {
        super(-1, -1);
    }

    @Override
    protected String getId() {
        return ""; // Empty representation for non-occupied cells
    }

    public void print(StringBuilder builder) {
        // Optional: Custom logic for printing an empty cell
    }
}