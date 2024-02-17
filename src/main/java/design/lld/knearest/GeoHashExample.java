package design.lld.knearest;

import ch.hsr.geohash.GeoHash;

public class GeoHashExample {
  public static void main(String[] args) {
    double latitude = 40.7128;  // New York City latitude
    double longitude = -74.0060;  // New York City longitude

    String geoHash = GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, 4);
    System.out.println("GeoHash: " + geoHash);
  }
}

