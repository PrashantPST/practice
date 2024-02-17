package design.pattern.behavioral.strategy.routeplanning;

public class LeastTrafficStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String pointA, String pointB) {
        System.out.println("Building least traffic path from " + pointA + " to " + pointB);
    }
}
