package design.lld.uber.database;

import design.lld.uber.exceptions.RiderAlreadyExistsException;
import design.lld.uber.exceptions.RiderNotFoundException;
import design.lld.uber.models.Rider;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class RidersManager {
    Map<String, Rider> riders = new HashMap<>();

    public void createRider(@NonNull final Rider newRider) throws RiderAlreadyExistsException {
        if (riders.containsKey(newRider.getId())) {
            throw new RiderAlreadyExistsException();
        }
        riders.put(newRider.getId(), newRider);
    }

    public Rider getRider(@NonNull final String riderId) throws RiderNotFoundException {
        if (!riders.containsKey(riderId)) {
            throw new RiderNotFoundException();
        }
        return riders.get(riderId);
    }
}
