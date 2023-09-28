package design.lld.parkinglot.models.parking;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

import static design.lld.parkinglot.enums.ParkingSpotType.CAR;
import static design.lld.parkinglot.enums.ParkingSpotType.ELECTRIC;
import static design.lld.parkinglot.enums.ParkingSpotType.HANDICAPPED;
import static design.lld.parkinglot.enums.ParkingSpotType.LARGE;
import static design.lld.parkinglot.enums.ParkingSpotType.MOTORBIKE;
import static design.lld.parkinglot.models.parking.ParkingLot.getSpotTypeForVehicle;

public class ParkingFloor {
    @Getter
    @Setter
    private String floorId;
    @Getter
    private Map<ParkingSpotType, Deque<ParkingSpot>> freeParkingSpots = new HashMap<>();
    private final Map<String, ParkingSpot> usedParkingSpots = new HashMap<>();

    public ParkingFloor(String id) {
        this.floorId = id;
        freeParkingSpots.put(HANDICAPPED, new ConcurrentLinkedDeque<>());
        freeParkingSpots.put(CAR, new ConcurrentLinkedDeque<>());
        freeParkingSpots.put(LARGE, new ConcurrentLinkedDeque<>());
        freeParkingSpots.put(MOTORBIKE, new ConcurrentLinkedDeque<>());
        freeParkingSpots.put(ELECTRIC, new ConcurrentLinkedDeque<>());
    }

    public boolean isFloorFull() {
        return freeParkingSpots.entrySet().stream().noneMatch(entry -> entry.getValue().size() > 0);
    }

    public boolean canPark(ParkingSpotType parkingSpotType) {
        return freeParkingSpots.get(parkingSpotType).size() > 0;
    }

    public synchronized ParkingSpot reserveSpot(VehicleType vehicleType) {
        if (!canPark(getSpotTypeForVehicle(vehicleType))) {
            return null;
        }
        ParkingSpotType parkingSpotType = getSpotTypeForVehicle(vehicleType);
        ParkingSpot parkingSpot = freeParkingSpots.get(parkingSpotType).poll();
        usedParkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
        return parkingSpot;
    }

    public Optional<ParkingSpot> vacateSpot(String parkingSpotId) {
        ParkingSpot parkingSpot = usedParkingSpots.remove(parkingSpotId);
        if (Objects.nonNull(parkingSpot)) {
            parkingSpot.freeSpot();
            freeParkingSpots.get(parkingSpot.getParkingSpotType()).addFirst(parkingSpot);
            return Optional.of(parkingSpot);
        }
        return Optional.empty();
    }
}
