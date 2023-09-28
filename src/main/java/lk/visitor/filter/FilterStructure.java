package lk.visitor.filter;

import lk.enums.LogicalOperator;
import lk.enums.Operator;
import lk.projection.VariableDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class FilterStructure implements GenericFilter {
    protected String segmentName;
    protected LogicalOperator logicalOperator;
    protected Operator operator;
    protected VariableDto variableDto;
    protected String[] value;
    private final String ruleApplicability;
    private final String defaultValue;
}
