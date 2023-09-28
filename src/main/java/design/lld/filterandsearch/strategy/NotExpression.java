package design.lld.filterandsearch.strategy;

import design.lld.filterandsearch.model.Product;

public class NotExpression implements IExpression {
    private IExpression expression;

    public boolean evaluate(Product product) {
        return !expression.evaluate(product);
    }

    @Override
    public String sqlWhereClause() {
        return "( NOT " + expression.sqlWhereClause() + ")";
    }
}
