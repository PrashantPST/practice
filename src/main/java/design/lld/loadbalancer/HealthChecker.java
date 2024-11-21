package design.lld.loadbalancer;

import java.util.Timer;
import java.util.TimerTask;

public class HealthChecker {
  private final ServerPool serverPool;
  private final Timer timer;

  public HealthChecker(ServerPool serverPool) {
    this.serverPool = serverPool;
    this.timer = new Timer(true);
  }

  public void start() {
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        checkHealth();
      }
    }, 0, 5000); // Check every 5 seconds
  }

  private void checkHealth() {
    for (Server server : serverPool.getHealthyServers()) {
      boolean healthy = ping(server);
      server.setHealthy(healthy);
    }
  }

  private boolean ping(Server server) {
    // Simulate health check (e.g., HTTP or gRPC ping)
    System.out.println("Pinging server: " + server.getIp() + ":" + server.getPort());
    return true; // Assume server is healthy
  }
}
