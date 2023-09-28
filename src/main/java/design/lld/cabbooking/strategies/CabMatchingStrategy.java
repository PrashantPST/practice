package design.lld.cabbooking.strategies;

import design.lld.cabbooking.models.Cab;
import design.lld.cabbooking.models.Location;
import design.lld.cabbooking.models.Rider;

import java.util.List;
import java.util.Optional;

public interface CabMatchingStrategy {
    Optional<Cab> matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
