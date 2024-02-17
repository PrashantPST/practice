package design.lld.uber.strategies;

import design.lld.uber.models.Cab;
import design.lld.uber.models.Location;
import design.lld.uber.models.Rider;
import java.util.List;
import java.util.Optional;

public interface CabMatchingStrategy {

    /**
     * Matches a cab to a rider based on various criteria such as proximity, cab availability,
     * rider preferences, and other factors like traffic conditions and estimated time of arrival.
     *
     * @param rider The rider requesting the cab. The rider object can include preferences
     *              and past ride history which can be used in the matching logic.
     * @param candidateCabs A list of available cabs in the vicinity. Each cab can have details
     *                      like current location, driver rating, vehicle type, etc.
     * @param fromPoint The starting location of the ride. This can be used to calculate the distance
     *                  to each candidate cab and estimate arrival times.
     * @param toPoint The destination of the ride. This can be used to match cabs that are best suited
     *                for the route or destination area (e.g., cabs with permits for certain areas or
     *                expertise in specific routes).
     * @return An Optional containing the matched cab if a suitable one is found, otherwise an empty Optional.
     *         The implementation should define what makes a cab "suitable" based on the strategy used.
     */
    Optional<Cab> matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}

