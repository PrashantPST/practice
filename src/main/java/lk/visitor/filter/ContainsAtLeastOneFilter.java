package lk.visitor.filter;

import lk.enums.LogicalOperator;
import lk.enums.Operator;
import lk.projection.VariableDto;
import lk.visitor.FilterVisitor;

public class ContainsAtLeastOneFilter extends FilterStructure {
    public ContainsAtLeastOneFilter(String segmentName, LogicalOperator logicalOperator, VariableDto variableDto, String[] value, String ruleApplicability, String defaultValue) {
        super(segmentName, logicalOperator, Operator.CONTAINS_AT_LEAST_ONE, variableDto, value[0].split(";"), ruleApplicability, defaultValue);
    }

    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visitContainsAtLeastOneFilter(this);
    }
}
