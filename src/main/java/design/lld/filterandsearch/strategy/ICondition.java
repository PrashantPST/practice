package design.lld.filterandsearch.strategy;

public interface ICondition {

    boolean perform(String productAttributeValue, String filterAttributeValue);
}
