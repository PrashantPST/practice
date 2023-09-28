package design.lld.cabbooking.models;

import design.lld.cabbooking.enums.TripStatus;
import design.lld.cabbooking.strategies.CabMatchingStrategy;
import design.lld.cabbooking.strategies.PricingStrategy;
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
