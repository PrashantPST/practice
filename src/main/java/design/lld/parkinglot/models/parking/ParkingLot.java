package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.enums.VehicleType;
import design.lld.parkinglot.models.account.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static design.lld.parkinglot.enums.ParkingSpotType.CAR;
import static design.lld.parkinglot.enums.ParkingSpotType.ELECTRIC;
import static design.lld.parkinglot.enums.ParkingSpotType.LARGE;
import static design.lld.parkinglot.enums.ParkingSpotType.MOTORBIKE;


@Getter
@Setter
public class ParkingLot {
    private final String parkingLotId;
    private Address address;
    private final List<ParkingFloor> parkingFloors;
    private final List<EntrancePanel> entrancePanels;
    private final List<ExitPanel> exitPanels;

    private static volatile ParkingLot parkingLot;

    public static ParkingLot getParkingLotInstance() {
        if (Objects.isNull(parkingLot)) {
            synchronized (ParkingLot.class) {
                if (Objects.isNull(parkingLot)) {
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    private ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString();
        parkingFloors = new ArrayList<>();
        entrancePanels = new ArrayList<>();
        exitPanels = new ArrayList<>();
    }

    public boolean isFull() {
        return parkingFloors.stream().allMatch(ParkingFloor::isFloorFull);
    }

    public boolean canPark(VehicleType vehicleType) {
        return parkingFloors.stream().anyMatch(parkingFloor -> parkingFloor.canPark(getSpotTypeForVehicle(vehicleType)));
    }

    public ParkingSpot getParkingSpot(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpot parkingSpot = parkingFloor.reserveSpot(vehicleType);
            if (parkingSpot != null) {
                return parkingSpot;
            }
        }
        return null;
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotId) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            Optional<ParkingSpot> parkingSpot = parkingFloor.vacateSpot(parkingSpotId);
            if (parkingSpot.isPresent()) {
                return parkingSpot.get();
            }
        }
        return null;
    }

    public static ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> CAR;
            case ELECTRIC -> ELECTRIC;
            case MOTORBIKE -> MOTORBIKE;
            default -> LARGE;
        };
    }
}
