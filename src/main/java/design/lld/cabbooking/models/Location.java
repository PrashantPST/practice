package design.lld.cabbooking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.Math.pow;

@Getter
@AllArgsConstructor
public class Location {
    private double latitude;
    private double longitude;

    public double distance(Location location2) {
        return Math.sqrt(pow(this.latitude - location2.latitude, 2) + pow(this.longitude - location2.longitude, 2));
    }
}
