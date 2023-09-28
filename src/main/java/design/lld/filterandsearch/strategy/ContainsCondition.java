package design.lld.filterandsearch.strategy;

public class ContainsCondition implements ICondition {
    @Override
    public boolean perform(String productAttributeValue, String filterAttributeValue) {
        return productAttributeValue.contains(filterAttributeValue);
    }
}
