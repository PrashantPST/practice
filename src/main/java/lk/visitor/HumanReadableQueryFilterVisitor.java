package lk.visitor;

import lk.enums.LogicalOperator;
import lk.projection.VariableDto;
import lk.visitor.filter.ContainsAtLeastOneFilter;
import lk.visitor.filter.EqualFilter;
import lk.visitor.filter.GenericFilter;
import lk.visitor.filter.GreaterThanFilter;
import lk.visitor.filter.GreaterThanOrEqualFilter;
import lk.visitor.filter.InRangeFilter;
import lk.visitor.filter.IncludeFilter;
import lk.visitor.filter.IsSubsetFilter;
import lk.visitor.filter.LessThanFilter;
import lk.visitor.filter.LessThanOrEqualFilter;
import lk.visitor.filter.LogicalFilter;
import lk.visitor.filter.NotIncludeFilter;
import lk.visitor.filter.OutRangeFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HumanReadableQueryFilterVisitor implements FilterVisitor {

    private final List<Condition> conditions = new ArrayList<>();

    @Override
    public void visitInRangeFilter(InRangeFilter filter) {
        VariableDto variableDto = filter.getVariableDto();
        Double left = filter.getLeft();
        Double right = filter.getRight();
        conditions.add(Condition.builder()
                .defaultValue(filter.getDefaultValue())
                .ruleApplicability(filter.getRuleApplicability())
                .logicalOperator(filter.getLogicalOperator())
                .query("(" + variableDto.getExpression() + " BETWEEN "+left+ " AND "+ right + ")")
                .build());
    }

    @Override
    public void visitOutRangeFilter(OutRangeFilter filter) {

    }

    @Override
    public void visitIncludeFilter(IncludeFilter filter) {

    }

    @Override
    public void visitNotIncludeFilter(NotIncludeFilter filter) {

    }

    @Override
    public void visitGreaterThanFilter(GreaterThanFilter filter) {

    }

    @Override
    public void visitGreaterThanOrEqualFilter(GreaterThanOrEqualFilter filter) {

    }

    @Override
    public void visitLessThanFilter(LessThanFilter filter) {

    }

    @Override
    public void visitLessThanOrEqualFilter(LessThanOrEqualFilter filter) {

    }

    @Override
    public void visitEqualFilter(EqualFilter filter) {

    }

    @Override
    public void visitContainsAtLeastOneFilter(ContainsAtLeastOneFilter filter) {

    }

    @Override
    public void visitIsSubsetFilter(IsSubsetFilter filter) {

    }
    public void visit(GenericFilter filter) {
        filter.accept(this);
    }

    @Override
    public void visitLogicalFilter(LogicalFilter filter) {

    }

    @Override
    public String getCompleteTemplate() {
        return null;
    }
    @AllArgsConstructor
    @Getter
    @Builder
    static class Condition {
        LogicalOperator logicalOperator;
        String query;
        String defaultValue;
        String ruleApplicability;
    }
}
