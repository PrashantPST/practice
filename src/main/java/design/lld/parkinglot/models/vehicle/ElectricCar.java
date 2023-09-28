package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;

public class ElectricCar extends Vehicle {
    public ElectricCar(String licenseNumber) {
        super(licenseNumber, VehicleType.ELECTRIC);
    }
}
