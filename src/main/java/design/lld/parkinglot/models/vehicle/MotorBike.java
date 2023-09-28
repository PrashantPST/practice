package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;

public class MotorBike extends Vehicle {
    public MotorBike(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORBIKE);
    }
}
