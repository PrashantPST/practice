package design.lld.filterandsearch;

import design.lld.filterandsearch.model.Product;
import design.lld.filterandsearch.service.SearchService;
import design.lld.filterandsearch.strategy.AndExpression;
import design.lld.filterandsearch.strategy.ConditionExpression;
import design.lld.filterandsearch.strategy.EqualCondition;
import design.lld.filterandsearch.strategy.FuzzySearchCondition;
import design.lld.filterandsearch.strategy.IExpression;
import design.lld.filterandsearch.strategy.OrExpression;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        final ConditionExpression titleContainsCondition
                = new ConditionExpression("title","shoe", new FuzzySearchCondition());

        final ConditionExpression brandCondition
                = new ConditionExpression("brand","ADIDAS", new EqualCondition());
        final ConditionExpression whiteColorCondition
                = new ConditionExpression("color","WHITE", new EqualCondition());
        final ConditionExpression blackColorCondition
                = new ConditionExpression("black","WHITE", new EqualCondition());

        final IExpression exp = new OrExpression(brandCondition, new AndExpression(blackColorCondition, whiteColorCondition));
        final SearchService searchService = new SearchService();

        // ADIDAS OR (BLACK AND WHITE)
        List<Product> products = searchService.search(exp, new ArrayList<>());

        // only adidas
        searchService.search(brandCondition, new ArrayList<>());
    }
}
