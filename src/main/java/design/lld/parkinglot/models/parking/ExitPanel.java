package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.factory.CostCalculationStrategyFactory;
import design.lld.parkinglot.strategy.CostCalculationStrategy;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ExitPanel extends Panel {

  private final ParkingLot parkingLot;

  public ExitPanel(String id) {
    super(id);
    this.parkingLot = ParkingLot.getParkingLotInstance();
  }

  public ParkingTicket checkout(ParkingTicket parkingTicket) {
    if (parkingTicket == null) {
      throw new IllegalArgumentException("Invalid or null parking ticket.");
    }

    ParkingSpot parkingSpot = parkingLot.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
    parkingTicket.setVacatedAt(LocalDateTime.now());
    parkingTicket.setCharge(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
    return parkingTicket;
  }

  private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
    CostCalculationStrategy costStrategy = CostCalculationStrategyFactory.getStrategy(parkingSpotType);
    return costStrategy.calculateCost(parkingTicket);
  }
}



