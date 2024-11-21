package design.lld.loadbalancer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerPool {
  private final CopyOnWriteArrayList<Server> servers;

  public ServerPool() {
    this.servers = new CopyOnWriteArrayList<>();
  }

  public void addServer(Server server) {
    servers.add(server);
  }

  public void removeServer(Server server) {
    servers.remove(server);
  }

  public List<Server> getHealthyServers() {
    return servers.stream().filter(Server::isHealthy).toList();
  }

  public List<Server> getAllServers() {
    return List.copyOf(servers);
  }
}
