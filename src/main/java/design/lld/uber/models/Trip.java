package design.lld.uber.models;

import design.lld.uber.enums.TripStatus;
import design.lld.uber.strategies.CabMatchingStrategy;
import design.lld.uber.strategies.PricingStrategy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Trip {

    private final String tripId;
    private final Rider rider;
    private final Cab cab;
    private TripStatus status;
    double price;
    PricingStrategy pricingStrategy;
    CabMatchingStrategy cabMatchingStrategy;
    TripMeta tripMeta;

    public void endTrip() {
        this.status = TripStatus.ENDED;
    }
}
