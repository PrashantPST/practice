package design.pattern.behavioral.strategy.routeplanning;

public class ShortestPathStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String pointA, String pointB) {
        System.out.println("Building shortest path from " + pointA + " to " + pointB);
    }
}
