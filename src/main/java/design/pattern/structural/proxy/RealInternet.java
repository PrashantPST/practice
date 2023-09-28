package design.pattern.structural.proxy;

public class RealInternet implements Internet {
    @Override
    public void connectTo(String serverHost) {
        System.out.println("Connected to "+serverHost);
    }
}
