package design.lld.cabbooking.strategies;

import design.lld.cabbooking.models.TripMeta;

public class DefaultPricingStrategy implements PricingStrategy {
    public static final Double PER_KM_RATE = 10.0;

    @Override
    public Double calculatePrice(TripMeta tripMeta) {
        return PER_KM_RATE * (tripMeta.getSrcLoc().distance(tripMeta.getDstLoc()));
    }
}
