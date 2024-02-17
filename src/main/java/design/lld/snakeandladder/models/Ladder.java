package design.lld.snakeandladder.models;

public class Ladder extends BoardEntity {

    public Ladder(int start, int end) {
        super(start, end);
    }

    @Override
    protected String getId() {
        return "L_" + this.getEnd();
    }

    @Override
    public void print(StringBuilder builder) {
        builder.append(getId());
    }
}
