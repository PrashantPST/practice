package design.lld.filterandsearch.strategy;

import design.lld.filterandsearch.model.Product;

public interface IExpression {
    boolean evaluate(Product product);
    String sqlWhereClause();
}
