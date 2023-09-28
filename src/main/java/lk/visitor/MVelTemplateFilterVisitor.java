package lk.visitor;

import lk.enums.LogicalOperator;
import lk.visitor.filter.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MVelTemplateFilterVisitor implements FilterVisitor {
    @Override
    public void visitInRangeFilter(InRangeFilter filter) {

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

    @Override
    public void visit(GenericFilter filter) {

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
    static class VariableSegment {
        String segmentName;
        LogicalOperator logicalOperator;
        String template;
        String defaultValue;
        String ruleApplicability;
    }
}
