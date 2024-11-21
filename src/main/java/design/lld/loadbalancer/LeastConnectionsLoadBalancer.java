package design.lld.loadbalancer;

import java.util.Comparator;
import java.util.List;

public class LeastConnectionsLoadBalancer implements LoadBalancer {
  @Override
  public Server selectServer(List<Server> servers) {
    return servers.stream()
        .min(Comparator.comparingInt(Server::getActiveConnections))
        .orElseThrow(() -> new RuntimeException("No available servers"));
  }
}
