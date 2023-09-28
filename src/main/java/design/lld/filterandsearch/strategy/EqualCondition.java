package design.lld.filterandsearch.strategy;

public class EqualCondition implements ICondition {
    @Override
    public boolean perform(String productAttributeValue, String filterAttributeValue) {
        return productAttributeValue.equals(filterAttributeValue);
    }
}
