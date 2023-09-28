package design.pattern.structural.adaptor;

import design.pattern.structural.adaptor.client.Client;
import design.pattern.structural.adaptor.vendor.XmlVendor;

public class Driver {
    public static void main(String[] args) {
        Client client = new Client(new Adaptor(new XmlVendor()));
        System.out.println(client.apiCallToVendor());
    }
}
