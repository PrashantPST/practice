package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.models.account.HourlyCost;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExitPanel {

    private String id;

    public ParkingTicket checkout(ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot = ParkingLot.getParkingLotInstance().vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setCharge(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
        return parkingTicket;
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        if (hours == 0) {
            hours = 1;
        }
        return hours * (new HourlyCost().getCost(parkingSpotType));
    }
}
