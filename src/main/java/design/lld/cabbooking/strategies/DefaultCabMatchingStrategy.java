package design.lld.cabbooking.strategies;

import design.lld.cabbooking.models.Cab;
import design.lld.cabbooking.models.Location;
import design.lld.cabbooking.models.Rider;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {
    @Override
    public Optional<Cab> matchCabToRider(
            @NonNull final Rider rider,
            @NonNull final List<Cab> candidateCabs,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint) {
        if (candidateCabs.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(candidateCabs.get(0));
    }
}
