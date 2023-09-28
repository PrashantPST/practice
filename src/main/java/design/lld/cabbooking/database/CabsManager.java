package design.lld.cabbooking.database;

import design.lld.cabbooking.exceptions.CabAlreadyExistsException;
import design.lld.cabbooking.exceptions.CabNotFoundException;
import design.lld.cabbooking.models.Cab;
import design.lld.cabbooking.models.Location;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabsManager {
    Map<String, Cab> cabs = new HashMap<>();

    public void createCab(@NonNull final Cab newCab) throws CabAlreadyExistsException {
        if (cabs.containsKey(newCab.getId())) {
            throw new CabAlreadyExistsException();
        }
        cabs.put(newCab.getId(), newCab);
    }

    public Cab getCab(@NonNull final String cabId) throws CabNotFoundException {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        return cabs.get(cabId);
    }

    public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation) throws CabNotFoundException {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateCabAvailability(
            @NonNull final String cabId, @NonNull final Boolean newAvailability) throws CabNotFoundException {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setIsAvailable(newAvailability);
    }

    public List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final Double toDistance) {
        List<Cab> result = new ArrayList<>();
        for (Cab cab : cabs.values()) {
            // TODO: Use epsilon comparison because of double
            if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= toDistance) {
                result.add(cab);
            }
        }
        return result;
    }
}
