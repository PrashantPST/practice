package lk.enums;

public enum VariableType {
    STANDARD,
    DSL_EVAL,
    DSL,
    INTERNAL;

    @Override
    public String toString() {
        return this.name();
    }
}
