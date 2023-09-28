package design.pattern.structural.flyweight;

import lombok.Builder;
import lombok.Setter;

@Builder
public class Ball {
    private String color;
    private String imageUrl;

    @Setter
    private int coordX;
    @Setter
    private int coordY;
    @Setter
    private int radius;

    public void draw() {

    }
}
