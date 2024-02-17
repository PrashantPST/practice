package design.lld.uber.models;

import design.lld.uber.enums.Rating;

public class Driver extends PersonMeta {
    private String name;
    private boolean available;
    private Rating rating;
    private Cab cab; // Reference to the Cab
}
