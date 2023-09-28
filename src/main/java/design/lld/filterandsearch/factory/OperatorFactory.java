package design.lld.filterandsearch.factory;

import design.lld.filterandsearch.strategy.ICondition;

import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {

    private static final Map<String, ICondition> operators = new HashMap<>();
    public static ICondition getOperator(String operatorName) {
        return operators.get(operatorName);
    }
}
