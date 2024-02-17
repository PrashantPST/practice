package design.pattern.behavioral.strategy.routeplanning;

public class NavigationContext {
    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void navigate(String pointA, String pointB) {
        routeStrategy.buildRoute(pointA, pointB);
    }
}
