package design.lld.parkinglot.strategy;

import design.lld.parkinglot.models.parking.ParkingTicket;
import java.time.Duration;

public interface CostCalculationStrategy {

  double calculateCost(ParkingTicket parkingTicket);

  default long calculateHours(ParkingTicket parkingTicket) {
    // Ensuring that both issuedAt and vacatedAt are not null.
    if (parkingTicket.getIssuedAt() == null || parkingTicket.getVacatedAt() == null) {
      throw new IllegalArgumentException("Parking ticket must have both issue and vacate times.");
    }
    long hours = Duration.between(parkingTicket.getIssuedAt(), parkingTicket.getVacatedAt())
        .toHours();
    return Math.max(hours, 1); // Ensure at least 1 hour is charged
  }
}
