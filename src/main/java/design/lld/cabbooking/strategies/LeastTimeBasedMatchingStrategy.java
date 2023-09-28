package design.lld.cabbooking.strategies;

import design.lld.cabbooking.models.Cab;
import design.lld.cabbooking.models.Location;
import design.lld.cabbooking.models.Rider;

import java.util.List;
import java.util.Optional;

public class LeastTimeBasedMatchingStrategy implements CabMatchingStrategy {
    @Override
    public Optional<Cab> matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint) {
        return Optional.empty();
    }
}
