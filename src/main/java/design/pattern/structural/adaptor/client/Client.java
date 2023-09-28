package design.pattern.structural.adaptor.client;

public class Client {
    ClientInterface clientInterface;

    public Client(ClientInterface clientInterface) {
        this.clientInterface = clientInterface;
    }

    public String apiCallToVendor() {
        JsonObject jsonObject = JsonObject.builder().requestId("123").metadata("MetaData").build();
        return clientInterface.serveRequest(jsonObject);
    }
}
