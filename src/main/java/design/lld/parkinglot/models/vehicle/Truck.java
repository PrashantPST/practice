package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }
}
