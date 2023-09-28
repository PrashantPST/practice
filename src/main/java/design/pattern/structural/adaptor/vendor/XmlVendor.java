package design.pattern.structural.adaptor.vendor;

public class XmlVendor {
    public String serveRequest(XmlObject xmlObject) {
        return (xmlObject.getRequestId() + " " + xmlObject.getMetadata() + " Success");
    }
}
