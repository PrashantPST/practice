package design.lld.foodelivery.strategy;

import design.lld.foodelivery.DeliveryMeta;
import design.lld.foodelivery.DeliveryPartner;

import java.util.List;

public interface DeliveryPartnerMatchingStrategy {

    List<DeliveryPartner> matchDeliveryPartners(DeliveryMeta deliveryMeta);
}
