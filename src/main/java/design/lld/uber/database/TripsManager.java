package design.lld.uber.database;

import design.lld.uber.exceptions.NoCabsAvailableException;
import design.lld.uber.exceptions.TripNotFoundException;
import design.lld.uber.models.Cab;
import design.lld.uber.models.Location;
import design.lld.uber.models.Rider;
import design.lld.uber.models.Trip;
import design.lld.uber.models.TripMeta;
import design.lld.uber.strategies.CabMatchingStrategy;
import design.lld.uber.strategies.PricingStrategy;
import lombok.Builder;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
public class TripsManager {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
    private final Map<Rider, List<Trip>> trips = new HashMap<>();

    private final CabsManager cabsManager;
    private final RidersManager ridersManager;
    private final CabMatchingStrategy cabMatchingStrategy;
    private final PricingStrategy pricingStrategy;

    public void createTrip(
            @NonNull final Rider rider,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint) throws NoCabsAvailableException {
        final List<Cab> closeByCabs = cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        final List<Cab> closeByAvailableCabs = closeByCabs.stream().filter(cab -> cab.getCurrentTrip() == null).
                collect(Collectors.toList());

        final Optional<Cab> selectedCab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs,
                fromPoint, toPoint);
        if (selectedCab.isEmpty()) {
            throw new NoCabsAvailableException();
        }
        TripMeta tripMeta = TripMeta.builder().build();
        final Double price = pricingStrategy.calculatePrice(tripMeta);
        final Trip newTrip = Trip.builder().cab(selectedCab.get()).price(price).build();
        if (!trips.containsKey(rider)) {
            trips.put(rider, new ArrayList<>());
        }
        trips.get(rider).add(newTrip);
        selectedCab.get().setCurrentTrip(newTrip);
    }

    public List<Trip> tripHistory(@NonNull final Rider rider) {
        return trips.get(rider);
    }

    public void endTrip(@NonNull final Cab cab) throws TripNotFoundException {
        if (Objects.isNull(cab.getCurrentTrip())) {
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }
}
