package design.lld.snakeladder.models;

public class Snake extends BoardEntity {

    public Snake(int start, int end) {
        super(start, end);
    }

    @Override
    protected String getId() {
        return "S_" + this.getEnd();
    }
}
