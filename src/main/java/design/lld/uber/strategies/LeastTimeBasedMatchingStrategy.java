package design.lld.uber.strategies;

import design.lld.uber.models.Cab;
import design.lld.uber.models.Location;
import design.lld.uber.models.Rider;

import java.util.List;
import java.util.Optional;

public class LeastTimeBasedMatchingStrategy implements CabMatchingStrategy {

    @Override
    public Optional<Cab> matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint) {
        if (candidateCabs.isEmpty()) {
            return Optional.empty();
        }

        Cab selectedCab = null;
        int shortestETA = Integer.MAX_VALUE;

        for (Cab cab : candidateCabs) {
            int eta = calculateETA(cab, fromPoint);
            if (eta < shortestETA) {
                shortestETA = eta;
                selectedCab = cab;
            }
        }

        return Optional.ofNullable(selectedCab);
    }

    /**
     * Calculates the estimated time of arrival (ETA) for the cab to reach the rider's location.
     * This calculation would take into account factors such as distance, current traffic conditions, and cab's speed.
     *
     * @param cab The cab for which to calculate the ETA.
     * @param riderLocation The location of the rider.
     * @return The estimated time of arrival in minutes.
     */
    private int calculateETA(Cab cab, Location riderLocation) {
        // Implementation of ETA calculation
        // This could involve integrating with a mapping service to get real-time traffic data, distances, etc.
        return 0; // placeholder return value
    }
}

