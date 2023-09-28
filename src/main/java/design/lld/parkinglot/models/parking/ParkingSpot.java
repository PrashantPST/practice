package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.models.vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public abstract class ParkingSpot {
    private String parkingSpotId;
    private ParkingSpotType parkingSpotType;
    private Vehicle assignedVehicle;
    private ParkingFloor parkingFloor;

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
    }

    public void freeSpot() {
        this.assignedVehicle = null;
    }
}
