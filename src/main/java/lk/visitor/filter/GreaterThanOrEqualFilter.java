package lk.visitor.filter;

import lk.enums.LogicalOperator;
import lk.enums.Operator;
import lk.projection.VariableDto;
import lk.visitor.FilterVisitor;

public class GreaterThanOrEqualFilter extends FilterStructure {
    public GreaterThanOrEqualFilter(String segmentName, LogicalOperator logicalOperator, VariableDto variableDto, String[] value, String ruleApplicability, String defaultValue) {
        super(segmentName, logicalOperator, Operator.GREATER_THAN_OR_EQUAL, variableDto, value, ruleApplicability, defaultValue);
    }

    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visitGreaterThanOrEqualFilter(this);
    }
}
