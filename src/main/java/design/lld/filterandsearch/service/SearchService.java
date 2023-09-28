package design.lld.filterandsearch.service;

import design.lld.filterandsearch.factory.OperatorFactory;
import design.lld.filterandsearch.model.Attribute;
import design.lld.filterandsearch.model.Filter;
import design.lld.filterandsearch.model.Product;
import design.lld.filterandsearch.strategy.IExpression;

import java.util.ArrayList;
import java.util.List;

public class SearchService {

    private OperatorFactory operatorFactory;

    public List<Product> search(Filter filter, List<Product> products) {
        List<Product> result = new ArrayList<Product>();
        for (Product product : products) {
            for (Attribute searchAttribute : filter.getAttributes()) {
                final List<Attribute> productAttributes = product.getAttributes();
                for (Attribute productAttribute : productAttributes) {
                    if (searchAttribute.isEqual(productAttribute)
                            && OperatorFactory.getOperator(searchAttribute.getOperatorName()).perform(
                                    productAttribute.getValue(), searchAttribute.getValue())) {
                        result.add(product);
                    }
                }
            }
        }
        return result;
    }

    public List<Product> search(IExpression expression, List<Product> products) {
        final List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (expression.evaluate(product)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> searchUsingRepository(IExpression expression) {
        final String where = expression.sqlWhereClause();
        // Build full query and fetch the products from repository.
        return new ArrayList<>();
    }
}
