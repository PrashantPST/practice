package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;

public class Van extends Vehicle {
    public Van(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }
}
