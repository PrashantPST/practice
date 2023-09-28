package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;

public class ElectricMotorBike extends Vehicle {
    public ElectricMotorBike(String licenseNumber) {
        super(licenseNumber, VehicleType.EBIKE);
    }
}
