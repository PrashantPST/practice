package lk.projection;

import lk.enums.VariableType;
import lk.enums.VariableValueType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VariableDto {
    private final String name;
    private final String query;
    private VariableType type;
    private VariableValueType valueType;

    public String getExpression() {
        return null;
    }
}
