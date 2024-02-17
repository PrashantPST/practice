package design.lld.uber.strategies;

import design.lld.uber.models.TripMeta;

public interface PricingStrategy {
    Double calculatePrice(TripMeta tripMeta);
}
