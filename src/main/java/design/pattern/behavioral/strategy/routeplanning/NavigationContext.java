package design.pattern.behavioral.strategy.routeplanning;

import design.lld.uber.models.Location;

public class NavigationContext {
    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void navigate(Location pointA, Location pointB) {
        routeStrategy.buildRoute(pointA, pointB);
    }
}
