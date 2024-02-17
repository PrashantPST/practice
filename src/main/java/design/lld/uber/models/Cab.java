package design.lld.uber.models;


import lombok.Getter;
import lombok.Setter;

@Getter
public class Cab {

  private final String id;
  private final String make;
  private final String model;
  private final String licensePlate;
  private final Driver driver;

  @Setter
  private Trip currentTrip;

  @Setter
  private Location currentLocation;

  @Setter
  private Boolean isAvailable;

  /**
   * Constructor for Cab class.
   * @param id           The unique identifier for the cab.
   * @param make         The make of the cab.
   * @param model        The model of the cab.
   * @param licensePlate The license plate of the cab.
   * @param driver       The driver of the cab.
   */
  public Cab(String id, String make, String model, String licensePlate, Driver driver) {
    this.id = id;
    this.make = make;
    this.model = model;
    this.licensePlate = licensePlate;
    this.driver = driver;
    this.isAvailable = true;
  }
}

