package design.lld.uber.models;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Location {

  private double latitude;
  private double longitude;

  public double distance(Location location2) {
    final int R = 6371; // Radius of the Earth in kilometers

    double latDistance = toRadians(location2.latitude - this.latitude);
    double lonDistance = toRadians(location2.longitude - this.longitude);

    double a = sin(latDistance / 2) * sin(latDistance / 2)
        + cos(toRadians(this.latitude)) * cos(toRadians(location2.latitude))
        * sin(lonDistance / 2) * sin(lonDistance / 2);

    double c = 2 * atan2(sqrt(a), sqrt(1 - a));

    return R * c; // returns the distance in kilometers
  }
}
