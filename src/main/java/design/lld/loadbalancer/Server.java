package design.lld.loadbalancer;

import java.util.concurrent.atomic.AtomicInteger;

public class Server {
  private final String ip;
  private final int port;
  private final AtomicInteger activeConnections;
  private volatile boolean healthy;

  public Server(String ip, int port) {
    this.ip = ip;
    this.port = port;
    this.activeConnections = new AtomicInteger(0);
    this.healthy = true;
  }

  public String getIp() {
    return ip;
  }

  public int getPort() {
    return port;
  }

  public int getActiveConnections() {
    return activeConnections.get();
  }

  public void incrementConnections() {
    activeConnections.incrementAndGet();
  }

  public void decrementConnections() {
    activeConnections.decrementAndGet();
  }

  public boolean isHealthy() {
    return healthy;
  }

  public void setHealthy(boolean healthy) {
    this.healthy = healthy;
  }

  @Override
  public String toString() {
    return String.format("Server[ip=%s, port=%d, activeConnections=%d, healthy=%s]",
        ip, port, activeConnections.get(), healthy);
  }
}