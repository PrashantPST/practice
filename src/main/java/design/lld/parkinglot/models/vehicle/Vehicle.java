package design.lld.parkinglot.models.vehicle;

import design.lld.parkinglot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public abstract class Vehicle {
    private final String registrationNumber;
    VehicleType vehicleType;
}
