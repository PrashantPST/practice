package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;

public class ElectricCarParkingSpot extends ParkingSpot {
    public ElectricCarParkingSpot(String id) {
        super(id, ParkingSpotType.ELECTRIC);
    }
}
