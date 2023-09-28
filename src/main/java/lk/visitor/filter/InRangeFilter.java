package lk.visitor.filter;

import lk.enums.LogicalOperator;
import lk.enums.Operator;
import lk.projection.VariableDto;
import lk.visitor.FilterVisitor;
import lombok.Getter;

@Getter
public class InRangeFilter extends FilterStructure {

    private Double left;
    private Double right;
    public InRangeFilter(String segmentName, LogicalOperator logicalOperator, VariableDto variableDto, String[] value, String ruleApplicability, String defaultValue) {
        super(segmentName, logicalOperator, Operator.IN_RANGE, variableDto, value, ruleApplicability, defaultValue);
    }

    @Override
    public void accept(FilterVisitor visitor) {
        this.left = Double.valueOf(value[0]);
        this.right = Double.valueOf(value[1]);
        visitor.visitInRangeFilter(this);
    }
}
