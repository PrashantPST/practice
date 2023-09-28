package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;

public class MotorBikeParkingSpot extends ParkingSpot {
    public MotorBikeParkingSpot(String id) {
        super(id, ParkingSpotType.MOTORBIKE);
    }
}
