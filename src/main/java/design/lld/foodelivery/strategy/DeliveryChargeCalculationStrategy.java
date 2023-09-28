package design.lld.foodelivery.strategy;

import design.lld.foodelivery.DeliveryMeta;

public interface DeliveryChargeCalculationStrategy {
    double calculateDeliveryCharge(DeliveryMeta deliveryMeta);
}
