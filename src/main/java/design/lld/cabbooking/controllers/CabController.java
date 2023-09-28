package design.lld.cabbooking.controllers;

import design.lld.cabbooking.database.CabsManager;
import design.lld.cabbooking.database.TripsManager;
import design.lld.cabbooking.exceptions.CabAlreadyExistsException;
import design.lld.cabbooking.exceptions.CabNotFoundException;
import design.lld.cabbooking.exceptions.TripNotFoundException;
import design.lld.cabbooking.models.Cab;
import design.lld.cabbooking.models.Driver;
import design.lld.cabbooking.models.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CabController {
    private final CabsManager cabsManager;
    private final TripsManager tripsManager;
    public CabController(CabsManager cabsManager, TripsManager tripsManager) {
        this.cabsManager = cabsManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/cab", method = RequestMethod.POST)
    public ResponseEntity regiserCab(final String cabId, final Driver driver) throws CabAlreadyExistsException {
        cabsManager.createCab(new Cab(cabId, driver));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/location", method = RequestMethod.POST)
    public ResponseEntity updateCabLocation(
            final String cabId, final Double newX, final Double newY) throws CabNotFoundException {

        cabsManager.updateCabLocation(cabId, new Location(newX, newY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST)
    public ResponseEntity updateCabAvailability(final String cabId, final Boolean newAvailability) throws CabNotFoundException {
        cabsManager.updateCabAvailability(cabId, newAvailability);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/end/trip", method = RequestMethod.POST)
    public ResponseEntity endTrip(final String cabId) throws CabNotFoundException, TripNotFoundException {
        tripsManager.endTrip(cabsManager.getCab(cabId));
        return ResponseEntity.ok("");
    }
}
