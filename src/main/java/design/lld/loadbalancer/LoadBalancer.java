package design.lld.loadbalancer;

import java.util.List;

public interface LoadBalancer {
  Server selectServer(List<Server> servers);
}
