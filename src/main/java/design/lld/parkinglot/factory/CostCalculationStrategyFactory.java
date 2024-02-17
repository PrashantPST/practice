package design.lld.parkinglot.factory;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.strategy.CostCalculationStrategy;
import design.lld.parkinglot.strategy.impl.BikeCostStrategy;
import design.lld.parkinglot.strategy.impl.CarCostStrategy;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CostCalculationStrategyFactory {

  private static final Map<ParkingSpotType, CostCalculationStrategy> strategyMap;

  static {
    Map<ParkingSpotType, CostCalculationStrategy> aMap = new ConcurrentHashMap<>();
    aMap.put(ParkingSpotType.CAR, new CarCostStrategy());
    aMap.put(ParkingSpotType.BIKE, new BikeCostStrategy());
    // Add other strategies as needed
    strategyMap = Collections.unmodifiableMap(aMap);
  }

  public static CostCalculationStrategy getStrategy(ParkingSpotType type) {
    CostCalculationStrategy strategy = strategyMap.get(type);
    if (strategy == null) {
      throw new IllegalArgumentException("No strategy found for type: " + type);
    }
    return strategy;
  }
}



