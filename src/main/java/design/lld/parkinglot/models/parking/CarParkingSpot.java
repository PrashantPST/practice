package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;

public class CarParkingSpot extends ParkingSpot {
    public CarParkingSpot(String parkingSpotId) {
        super(parkingSpotId, ParkingSpotType.CAR);
    }
}
