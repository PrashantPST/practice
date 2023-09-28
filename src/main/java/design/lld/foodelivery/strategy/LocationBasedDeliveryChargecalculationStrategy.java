package design.lld.foodelivery.strategy;

import design.lld.foodelivery.DeliveryMeta;

public class LocationBasedDeliveryChargecalculationStrategy implements DeliveryChargeCalculationStrategy {
    @Override
    public double calculateDeliveryCharge(DeliveryMeta deliveryMeta) {
        System.out.println("Delivery charge is decided based on location. Setting to 20 as default value for now");
        return 20.0;
    }
}
