package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.TicketStatus;
import design.lld.parkinglot.models.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class EntrancePanel extends Panel {

  private final ParkingLot parkingLot;

  public EntrancePanel(String id) {
    super(id);
    this.parkingLot = ParkingLot.getParkingLotInstance();
  }

  public Optional<ParkingTicket> getParkingTicket(Vehicle vehicle) {
    return Optional.ofNullable(vehicle)
        .filter(this::isParkingAvailableForVehicle)
        .flatMap(this::getParkingSpotForVehicle)
        .map(spot -> generateTicket(vehicle, spot));
  }

  private boolean isParkingAvailableForVehicle(Vehicle vehicle) {
    boolean canPark = parkingLot.canPark(vehicle.getVehicleType());
    if (!canPark) {
      System.out.println("No parking available for this vehicle type.");
    }
    return canPark;
  }

  private Optional<ParkingSpot> getParkingSpotForVehicle(Vehicle vehicle) {
    return Optional.ofNullable(parkingLot.getParkingSpot(vehicle.getVehicleType()))
        .or(() -> {
          System.out.println("No available spot for this vehicle.");
          return Optional.empty();
        });
  }

  private ParkingTicket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
    return ParkingTicket.builder()
        .ticketNumber(UUID.randomUUID().toString())
        .licensePlateNumber(vehicle.getRegistrationNumber())
        .allocatedSpotId(parkingSpot.getParkingSpotId())
        .issuedAt(LocalDateTime.now())
        .ticketStatus(TicketStatus.ACTIVE)
        .build();
  }
}



