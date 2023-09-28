package design.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class BallFactory {
    private static final Map<String, Ball> ballMap = new HashMap<>();

    public static Ball getBall(String color, String url) {
        String ballCacheKey = color + url;
        Ball ball = ballMap.get(ballCacheKey);
        if (ball == null) {
            ball = Ball.builder().color(color).imageUrl(url).build();
            ballMap.put(ballCacheKey, ball);
        }
        return ball;
    }
}
