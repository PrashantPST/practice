package design.lld.foodelivery.strategy;

import design.lld.foodelivery.DeliveryMeta;
import design.lld.foodelivery.DeliveryPartner;

import java.util.List;

public class LocationBasedDeliveryPartnerMatchingStrategy implements DeliveryPartnerMatchingStrategy {

    @Override
    public List<DeliveryPartner> matchDeliveryPartners(DeliveryMeta pMetaData) {

        System.out.println("Quadtrees can be used to get nearest delivery partners, " +
                        "Delivery partner manager can be used to get details of partners" +
                "Returning all delivery partners for demo purposes for now.");

        return null;
    }
}
