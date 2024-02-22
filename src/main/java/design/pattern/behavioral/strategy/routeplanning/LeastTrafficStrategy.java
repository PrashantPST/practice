package design.pattern.behavioral.strategy.routeplanning;

import design.lld.uber.models.Location;

public class LeastTrafficStrategy implements RouteStrategy {
    @Override
    public void buildRoute(Location pointA, Location pointB) {
        System.out.println("Building least traffic path from " + pointA + " to " + pointB);
    }
}
