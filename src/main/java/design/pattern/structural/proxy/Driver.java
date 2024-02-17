package design.pattern.structural.proxy;

public class Driver {

  public static void main(String[] args) {
    RealInternet realInternet = new RealInternet();
    Internet internet = new ProxyInternet(realInternet);

    try {
      internet.connectTo("google.com");
      internet.connectTo("xyz.com");
      internet.connectTo("xvideos.com");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
