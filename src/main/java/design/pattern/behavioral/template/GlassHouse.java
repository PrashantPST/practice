package design.pattern.behavioral.template;

public class GlassHouse extends HouseBuilder {

    @Override
    protected void buildWalls() {
        System.out.println("Building glass walls");
    }

    @Override
    protected void paintHouse() {
        System.out.println("Painting house with transparent paint");
    }

    @Override
    protected void decorateHouse() {
        System.out.println("Decorating with modern furniture");
    }
}

