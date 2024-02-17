package design.lld.snakeandladder.models;

public class Snake extends BoardEntity {

    public Snake(int start, int end) {
        super(start, end);
    }

    @Override
    protected String getId() {
        return "S_" + this.getEnd(); // Identifier includes the end position of the snake
    }

    @Override
    public void print(StringBuilder builder) {
        builder.append(getId()); // Append the snake identifier to the StringBuilder
    }
}

