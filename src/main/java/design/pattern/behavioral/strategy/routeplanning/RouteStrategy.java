package design.pattern.behavioral.strategy.routeplanning;

import design.lld.uber.models.Location;

public interface RouteStrategy {
    void buildRoute(Location pointA, Location pointB);
}
