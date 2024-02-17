package design.pattern.structural.proxy;

public class ProxyInternet implements Internet {
    Internet internet;

    public ProxyInternet(Internet internet) {
        this.internet = internet;
    }

    @Override
    public void connectTo(String serverHost) throws Exception {
        if (Properties.bannedSites.contains(serverHost)) {
            System.out.println("Access Denied to " + serverHost);
        }
        else {
            internet.connectTo(serverHost);
        }
    }
}
