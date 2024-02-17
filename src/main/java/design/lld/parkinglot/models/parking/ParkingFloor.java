package design.lld.parkinglot.models.parking;

import static design.lld.parkinglot.models.parking.ParkingLot.getSpotTypeForVehicle;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.enums.VehicleType;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.Getter;

public class ParkingFloor {
    @Getter
    private final String floorId;
    private final Map<ParkingSpotType, Deque<ParkingSpot>> freeParkingSpots = new ConcurrentHashMap<>();
    private final Map<String, ParkingSpot> usedParkingSpots = new ConcurrentHashMap<>();
    private final Map<ParkingSpotType, Object> locks = new ConcurrentHashMap<>();

    public ParkingFloor(String id) {
        this.floorId = id;
        initializeFreeParkingSpots();
    }

    private void initializeFreeParkingSpots() {
        for (ParkingSpotType type : ParkingSpotType.values()) {
            freeParkingSpots.put(type, new ConcurrentLinkedDeque<>());
            locks.put(type, new Object());
        }
    }

    public boolean isFloorFull() {
        return freeParkingSpots.values().stream().allMatch(Deque::isEmpty);
    }

    public boolean canPark(ParkingSpotType parkingSpotType) {
        Deque<ParkingSpot> spots = freeParkingSpots.get(parkingSpotType);
        return spots != null && !spots.isEmpty();
    }

    public ParkingSpot reserveSpot(VehicleType vehicleType) {
        ParkingSpotType parkingSpotType = getSpotTypeForVehicle(vehicleType);
        if (!canPark(parkingSpotType)) {
            return null;
        }
        return reserveSpotInternal(parkingSpotType);
    }

    private ParkingSpot reserveSpotInternal(ParkingSpotType parkingSpotType) {
        synchronized (locks.get(parkingSpotType)) {
            ParkingSpot parkingSpot = freeParkingSpots.get(parkingSpotType).poll();
            if (parkingSpot != null) {
                usedParkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
            }
            return parkingSpot;
        }
    }

    public Optional<ParkingSpot> vacateSpot(String parkingSpotId) {
        ParkingSpot parkingSpot = usedParkingSpots.remove(parkingSpotId);
        if (parkingSpot != null) {
            parkingSpot.freeSpot();
            freeParkingSpots.get(parkingSpot.getParkingSpotType()).addFirst(parkingSpot);
        }
        return Optional.ofNullable(parkingSpot);
    }
}

