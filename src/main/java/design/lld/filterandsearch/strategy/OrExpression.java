package design.lld.filterandsearch.strategy;

import design.lld.filterandsearch.model.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrExpression implements IExpression {
    private IExpression left;
    private IExpression right;

    public boolean evaluate(Product product) {
        return left.evaluate(product) || right.evaluate(product);
    }

    @Override
    public String sqlWhereClause() {
        return "(" + left.sqlWhereClause() + ") OR ( " + right.sqlWhereClause() + " )";
    }
}