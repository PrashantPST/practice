package design.lld.cabbooking.strategies;

import design.lld.cabbooking.models.TripMeta;

public interface PricingStrategy {
    Double calculatePrice(TripMeta tripMeta);
}
