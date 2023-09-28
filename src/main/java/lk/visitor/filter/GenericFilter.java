package lk.visitor.filter;

import lk.visitor.FilterVisitor;

public interface GenericFilter {
    void accept(FilterVisitor visitor);
}
