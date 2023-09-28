package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;

public class LargeVehicleParkingSpot extends ParkingSpot {
    public LargeVehicleParkingSpot(String id) {
        super(id, ParkingSpotType.LARGE);
    }
}
