package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;

public class Car extends Vehicle {

    public Car(String registrationNumber) {
        super(registrationNumber, VehicleType.CAR);
    }
}
