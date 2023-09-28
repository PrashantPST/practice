package lk.visitor.filter;

import lk.enums.LogicalOperator;
import lk.enums.Operator;
import lk.projection.VariableDto;
import lk.visitor.FilterVisitor;

public class LessThanFilter extends FilterStructure {
    public LessThanFilter(String segmentName, LogicalOperator logicalOperator, VariableDto variableDto, String[] value, String ruleApplicability, String defaultValue) {
        super(segmentName, logicalOperator, Operator.LESS_THAN, variableDto, value, ruleApplicability, defaultValue);
    }

    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visitLessThanFilter(this);
    }
}
