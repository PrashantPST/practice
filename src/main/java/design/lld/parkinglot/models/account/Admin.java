package design.lld.parkinglot.models.account;

import design.lld.parkinglot.exceptions.InvalidParkingFloorException;
import design.lld.parkinglot.models.parking.EntrancePanel;
import design.lld.parkinglot.models.parking.ExitPanel;
import design.lld.parkinglot.models.parking.ParkingFloor;
import design.lld.parkinglot.models.parking.ParkingLot;
import design.lld.parkinglot.models.parking.ParkingSpot;

import java.util.Optional;

public class Admin extends Account {

    public void addParkingFloor(ParkingFloor parkingFloor) {
        Optional<ParkingFloor> floor = ParkingLot.getParkingLotInstance().getParkingFloors().stream()
                .filter(pF -> pF.getFloorId().equalsIgnoreCase(parkingFloor.getFloorId()))
                .findFirst();
        if (floor.isEmpty()) {
            ParkingLot.getParkingLotInstance().getParkingFloors().add(parkingFloor);
        }
    }

    public void addEntrancePanel(EntrancePanel entrancePanel) {
        Optional<EntrancePanel> panel = ParkingLot.getParkingLotInstance().getEntrancePanels().stream()
                .filter(eP -> eP.getId().equalsIgnoreCase(entrancePanel.getId())).findFirst();
        if (panel.isEmpty()) {
            ParkingLot.getParkingLotInstance().getEntrancePanels().add(entrancePanel);
        }
    }

    public void addExitPanel(ExitPanel exitPanel) {
        Optional<ExitPanel> panel = ParkingLot.getParkingLotInstance().getExitPanels().stream().filter(
                eP -> eP.getId().equalsIgnoreCase(exitPanel.getId())).findFirst();
        if (panel.isEmpty()) {
            ParkingLot.getParkingLotInstance().getExitPanels().add(exitPanel);
        }
    }

    public void addParkingSpot(String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingFloorException {
        Optional<ParkingFloor> floor = ParkingLot.getParkingLotInstance().getParkingFloors().stream().filter(
                pF -> pF.getFloorId().equalsIgnoreCase(parkingFloorId)).findFirst();
        if (floor.isEmpty()) {
            throw new InvalidParkingFloorException("Invalid floor");
        }
        Optional<ParkingSpot> spot = floor.get().getFreeParkingSpots().get(parkingSpot.getParkingSpotType())
                .stream()
                .filter(pS -> pS.getParkingSpotId().equalsIgnoreCase(parkingSpot.getParkingSpotId()))
                .findFirst();
        if (spot.isEmpty()) {
            floor.get().getFreeParkingSpots().get(parkingSpot.getParkingSpotType()).addLast(parkingSpot);
        }
    }
}
