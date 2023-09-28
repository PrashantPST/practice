package design.lld.filterandsearch.strategy;

import design.lld.filterandsearch.model.Product;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ConditionExpression implements IExpression {
    private String attributeName;
    private String searchValue;
    private ICondition condition;

    @Override
    public boolean evaluate(Product product) {
        return condition.perform(product.getAttribute(attributeName).getValue(), searchValue);
    }

    @Override
    public String sqlWhereClause() {
        return null;
    }
}
