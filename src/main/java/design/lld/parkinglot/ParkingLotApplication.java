package design.lld.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import design.lld.parkinglot.enums.ParkingSpotType;
import design.lld.parkinglot.enums.VehicleType;
import design.lld.parkinglot.models.account.Address;
import design.lld.parkinglot.models.account.Admin;
import design.lld.parkinglot.models.parking.CarParkingSpot;
import design.lld.parkinglot.models.parking.EntrancePanel;
import design.lld.parkinglot.models.parking.ExitPanel;
import design.lld.parkinglot.models.parking.MotorBikeParkingSpot;
import design.lld.parkinglot.models.parking.ParkingFloor;
import design.lld.parkinglot.models.parking.ParkingLot;
import design.lld.parkinglot.models.parking.ParkingSpot;
import design.lld.parkinglot.models.parking.ParkingTicket;
import design.lld.parkinglot.models.vehicle.Car;
import design.lld.parkinglot.models.vehicle.MotorBike;
import design.lld.parkinglot.models.vehicle.Van;
import design.lld.parkinglot.models.vehicle.Vehicle;
import java.util.Optional;

public class ParkingLotApplication {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        Address address = new Address();
        address.setAddressLine1("Ram parking Complex");
        address.setStreet("BG Road");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setPinCode("560075");
        parkingLot.setAddress(address);
        Admin adminAccount = new Admin();

        // Admin Case 1 - should be able to add a parking floor case
        adminAccount.addParkingFloor(new ParkingFloor("1"));

        // Admin Case 2 - should be able to add a parking floor case
        adminAccount.addParkingFloor(new ParkingFloor("2"));
        EntrancePanel entrancePanel = new EntrancePanel("1");
        ExitPanel exitPanel = new ExitPanel("1");

        // Admin Case 3 - should be able to add an entrance panel
        adminAccount.addEntrancePanel(entrancePanel);

        // Admin Case 4 - should be able to add an exit panel
        adminAccount.addExitPanel(exitPanel);
        String floorId = parkingLot.getParkingFloors().get(0).getFloorId();
        ParkingSpot carSpot1 = new CarParkingSpot("carSpot1");
        ParkingSpot carSpot2 = new CarParkingSpot("carSpot2");
        ParkingSpot bikeSport = new MotorBikeParkingSpot("bikeSpot1");

        // Admin case 5 - should be able to add a car parking spot
        adminAccount.addParkingSpot(floorId, carSpot1);

        // Admin case 6 - should be able to add a bike parking spot
        adminAccount.addParkingSpot(floorId, bikeSport);

        // Admin case 7 - should be able to add a car parking spot
        adminAccount.addParkingSpot(floorId, carSpot2);

        assertEquals(parkingLot.getEntrancePanels().size(), 1);

        // #User Test cases Test case 1 - check for availability of parking lot - TRUE
        assertTrue(parkingLot.getParkingLotId(), parkingLot.canPark(VehicleType.CAR));

        // Test case 2 - check for availability of parking lot - FALSE
        assertFalse(parkingLot.getParkingLotId(), parkingLot.canPark(VehicleType.EBIKE));

        // Test case 3 - check for availability of parking lot - FALSE
        assertFalse(parkingLot.canPark(VehicleType.ELECTRIC));

        // TEST case 4 - Check if full
        assertFalse(parkingLot.isFull());

        Vehicle van = new Van("KA01MR7804");
        ParkingSpot vanSpot = parkingLot.getParkingSpot(van.getVehicleType());
        assertNull(vanSpot);

        Vehicle vehicle = new Car("KA05MR2311");
        ParkingSpot availableSpot = parkingLot.getParkingSpot(vehicle.getVehicleType());
        assertEquals(availableSpot.getParkingSpotType(), ParkingSpotType.CAR);
        assertEquals(availableSpot.getParkingSpotId(), "carSpot1");
        Optional<ParkingTicket> mtrTktOpt = entrancePanel.getParkingTicket(new MotorBike("ka01ee4901"));
        assertTrue(mtrTktOpt.isPresent());
        ParkingTicket mtrTkt = mtrTktOpt.get();
        assertEquals("bikeSpot1", mtrTkt.getAllocatedSpotId());

        mtrTkt = exitPanel.checkout(mtrTkt); // Assuming this updates the ticket
        assertEquals(10.0, mtrTkt.getCharge(), 1e-10);
        assertTrue(mtrTkt.getCharge() > 0);

        Optional<ParkingTicket> mtrTkt1Opt = entrancePanel.getParkingTicket(new MotorBike("ka01ee7791"));
        assertTrue(mtrTkt1Opt.isPresent());
        ParkingTicket mtrTkt1 = mtrTkt1Opt.get();
        assertEquals("bikeSpot1", mtrTkt1.getAllocatedSpotId());

        Optional<ParkingTicket> unavailableTktOpt = entrancePanel.getParkingTicket(new MotorBike("ka01ee4455"));
        assertFalse(unavailableTktOpt.isPresent());
    }
}
