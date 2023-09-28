package lk.visitor;

import lk.visitor.filter.*;

public interface FilterVisitor {
    void visitInRangeFilter(InRangeFilter filter);
    void visitOutRangeFilter(OutRangeFilter filter);
    void visitIncludeFilter(IncludeFilter filter);
    void visitNotIncludeFilter(NotIncludeFilter filter);
    void visitGreaterThanFilter(GreaterThanFilter filter);
    void visitGreaterThanOrEqualFilter(GreaterThanOrEqualFilter filter);
    void visitLessThanFilter(LessThanFilter filter);
    void visitLessThanOrEqualFilter(LessThanOrEqualFilter filter);
    void visitEqualFilter(EqualFilter filter);
    void visitContainsAtLeastOneFilter(ContainsAtLeastOneFilter filter);
    void visitIsSubsetFilter(IsSubsetFilter filter);
    void visit(GenericFilter filter);
    void visitLogicalFilter(LogicalFilter filter);
    String getCompleteTemplate();
}
