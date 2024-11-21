package design.lld.loadbalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinLoadBalancer implements LoadBalancer {
  private final AtomicInteger currentIndex = new AtomicInteger(0);

  @Override
  public Server selectServer(List<Server> servers) {
    if (servers.isEmpty()) {
      throw new RuntimeException("No healthy servers available");
    }
    int index = currentIndex.getAndUpdate(i -> (i + 1) % servers.size());
    return servers.get(index);
  }
}
