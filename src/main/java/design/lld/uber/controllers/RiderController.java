package design.lld.uber.controllers;


import design.lld.uber.database.RidersManager;
import design.lld.uber.database.TripsManager;
import design.lld.uber.exceptions.NoCabsAvailableException;
import design.lld.uber.exceptions.RiderAlreadyExistsException;
import design.lld.uber.exceptions.RiderNotFoundException;
import design.lld.uber.models.Location;
import design.lld.uber.models.Rider;
import design.lld.uber.models.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RiderController {
    private final RidersManager ridersManager;
    private final TripsManager tripsManager;

    public RiderController(RidersManager ridersManager, TripsManager tripsManager) {
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/rider", method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId, final String riderName) throws RiderAlreadyExistsException {
        ridersManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(
            final String riderId,
            final Double sourceX,
            final Double sourceY,
            final Double destX,
            final Double destY) throws RiderNotFoundException, NoCabsAvailableException {

        tripsManager.createTrip(
                ridersManager.getRider(riderId),
                new Location(sourceX, sourceY),
                new Location(destX, destY));

        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderId) throws RiderNotFoundException {
        List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}
