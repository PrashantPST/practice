package design.lld.filterandsearch.strategy;

public class FuzzySearchCondition implements ICondition {
    @Override
    public boolean perform(String productAttributeValue, String filterAttributeValue) {
        return false;
    }
}
