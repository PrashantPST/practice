package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;

public class ElectricBikeParkingSpot extends ParkingSpot {
    public ElectricBikeParkingSpot(String id) {
        super(id, ParkingSpotType.EBIKE);
    }
}
