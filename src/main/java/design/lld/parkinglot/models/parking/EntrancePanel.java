package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.TicketStatus;
import design.lld.parkinglot.models.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class EntrancePanel {
    private String id;

    public ParkingTicket getParkingTicket(Vehicle vehicle) {
        if (!ParkingLot.getParkingLotInstance().canPark(vehicle.getVehicleType())) {
            return null;
        }
        ParkingSpot parkingSpot = ParkingLot.getParkingLotInstance().getParkingSpot(vehicle.getVehicleType());
        if (parkingSpot == null) {
            return null;
        }
        return generateTicket(vehicle.getRegistrationNumber(), parkingSpot.getParkingSpotId());
    }

    private ParkingTicket generateTicket(String vehicleLicenseNumber, String parkingSpotId) {
        return ParkingTicket.builder().issuedAt(LocalDateTime.now()).allocatedSpotId(parkingSpotId).
                licensePlateNumber(vehicleLicenseNumber).ticketNumber(UUID.randomUUID().toString()).
                ticketStatus(TicketStatus.ACTIVE).build();
    }
}
