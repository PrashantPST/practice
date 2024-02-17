package design.lld.parkinglot.strategy.impl;

import design.lld.parkinglot.models.parking.ParkingTicket;
import design.lld.parkinglot.strategy.CostCalculationStrategy;

public class BikeCostStrategy implements CostCalculationStrategy {
  @Override
  public double calculateCost(ParkingTicket parkingTicket) {
    long hours = calculateHours(parkingTicket);
    return hours * 5.0; // Example cost per hour for bike
  }
}
