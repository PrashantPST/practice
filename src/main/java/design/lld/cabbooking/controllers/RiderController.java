package design.lld.cabbooking.controllers;


import design.lld.cabbooking.database.RidersManager;
import design.lld.cabbooking.database.TripsManager;
import design.lld.cabbooking.exceptions.NoCabsAvailableException;
import design.lld.cabbooking.exceptions.RiderAlreadyExistsException;
import design.lld.cabbooking.exceptions.RiderNotFoundException;
import design.lld.cabbooking.models.Location;
import design.lld.cabbooking.models.Rider;
import design.lld.cabbooking.models.Trip;
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
