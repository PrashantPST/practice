package design.pattern.behavioral.strategy.routeplanning;

import design.lld.uber.models.Location;

public class ShortestPathStrategy implements RouteStrategy {
    @Override
    public void buildRoute(Location pointA, Location pointB) {
        System.out.println("Building shortest path from " + pointA + " to " + pointB);
    }
}
