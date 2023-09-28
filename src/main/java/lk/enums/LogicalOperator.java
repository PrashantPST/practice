package lk.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogicalOperator {
    AND("AND", "&&"),
    OR("OR", "||");
    private final String name;
    private final String expression;
}
