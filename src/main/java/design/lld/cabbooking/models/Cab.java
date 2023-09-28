package design.lld.cabbooking.models;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Cab {

    private String id;
    private Driver driver;

    @Setter
    Trip currentTrip;
    @Setter Location currentLocation;
    @Setter Boolean isAvailable;

    public Cab(String cabId, Driver driver) {
        this.id = cabId;
        this.driver = driver;
    }
}
