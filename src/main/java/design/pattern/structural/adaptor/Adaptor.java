package design.pattern.structural.adaptor;

import design.pattern.structural.adaptor.client.ClientInterface;
import design.pattern.structural.adaptor.client.JsonObject;
import design.pattern.structural.adaptor.vendor.XmlObject;
import design.pattern.structural.adaptor.vendor.XmlVendor;

public class Adaptor implements ClientInterface {
    XmlVendor vendor;

    public Adaptor(XmlVendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String serveRequest(JsonObject request) {
        // Convert JSONObject to XMLObject ( vendor accepts)
        XmlObject xmlObject = XmlObject.builder().requestId(request.getRequestId())
                .metadata(request.getMetadata()).build();
        System.out.println("Vendor call made");
        return vendor.serveRequest(xmlObject);
    }
}
