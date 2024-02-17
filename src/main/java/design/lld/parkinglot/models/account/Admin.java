package design.lld.parkinglot.models.account;

import design.lld.parkinglot.exceptions.InvalidParkingFloorException;
import design.lld.parkinglot.models.parking.EntrancePanel;
import design.lld.parkinglot.models.parking.ExitPanel;
import design.lld.parkinglot.models.parking.Panel;
import design.lld.parkinglot.models.parking.ParkingFloor;
import design.lld.parkinglot.models.parking.ParkingLot;
import design.lld.parkinglot.models.parking.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class Admin extends Account {

    private final ParkingLot parkingLot = ParkingLot.getParkingLotInstance();

    public void addParkingFloor(ParkingFloor parkingFloor) {
        if (parkingFloorExists(parkingFloor.getFloorId())) {
            System.out.println("Parking floor already exists.");
            return;
        }
        parkingLot.getParkingFloors().add(parkingFloor);
    }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        if (panelExists(parkingLot.getEntrancePanels(), entrancePanel.getId())) {
            System.out.println("Entrance panel already exists.");
            return;
        }
        parkingLot.getEntrancePanels().add(entrancePanel);
    }

    public void addExitPanel(ExitPanel exitPanel) {
        if (panelExists(parkingLot.getExitPanels(), exitPanel.getId())) {
            System.out.println("Exit panel already exists.");
            return;
        }
        parkingLot.getExitPanels().add(exitPanel);
    }

    public void addParkingSpot(String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException {
        ParkingFloor floor = findParkingFloor(parkingFloorId)
                .orElseThrow(() -> new InvalidParkingFloorException("Invalid floor"));
        if (spotExists(floor, parkingSpot)) {
            System.out.println("Parking spot already exists.");
            return;
        }
        // floor.getFreeParkingSpots().get(parkingSpot.getParkingSpotType()).addLast(parkingSpot);
    }

    private boolean parkingFloorExists(String floorId) {
        return parkingLot.getParkingFloors().stream()
                .anyMatch(pF -> pF.getFloorId().equalsIgnoreCase(floorId));
    }

    private boolean panelExists(List<? extends Panel> panels, String panelId) {
        return panels.stream().anyMatch(p -> p.getId().equalsIgnoreCase(panelId));
    }

    private boolean spotExists(ParkingFloor floor, ParkingSpot spot) {
      return true;
      /*
        return floor.getFreeParkingSpots().get(spot.getParkingSpotType()).stream()
                .anyMatch(pS -> pS.getParkingSpotId().equalsIgnoreCase(spot.getParkingSpotId()));

       */
    }

    private Optional<ParkingFloor> findParkingFloor(String floorId) {
        return parkingLot.getParkingFloors().stream()
                .filter(pF -> pF.getFloorId().equalsIgnoreCase(floorId))
                .findFirst();
    }
}

