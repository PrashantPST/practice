package design.pattern.structural.flyweight;

import java.util.AbstractMap;
import java.util.Map;

public class FlyWeightDemo {
    private static final String[] colors = {"Green", "Yellow", "Pink"};
    private static final Map<String, String> urlMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Green", "url1"),
            new AbstractMap.SimpleEntry<>("Yellow", "url1"),
            new AbstractMap.SimpleEntry<>("Pink", "url1")
    );

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String color = getColor();
            String url = urlMap.get(color);
            Ball ball = BallFactory.getBall(color, url);
            ball.setCoordY(getY());
            ball.setCoordX(getX());
            ball.setRadius(75);
            ball.draw();
            System.out.println(ball.hashCode());
        }
    }

    private static int getY() {
        return (int) (Math.random() * 50);
    }

    private static int getX() {
        return (int) (Math.random() * 50);
    }

    private static String getColor() {
        return colors[(int) (Math.random() * colors.length)];
    }
}
