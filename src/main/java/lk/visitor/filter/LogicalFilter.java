package lk.visitor.filter;

import lk.visitor.FilterVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class LogicalFilter implements GenericFilter {

    private List<GenericFilter> filters;

    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visitLogicalFilter(this);
    }
}
