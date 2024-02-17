package design.pattern.behavioral.template;

public abstract class HouseBuilder {

    // Template method
    public final void buildHouse() {
        buildFoundation();
        buildWalls();
        buildRoof();
        paintHouse();
        decorateHouse();
    }

    // Steps with default implementations
    private void buildFoundation() {
        System.out.println("Building foundation with cement and sand");
    }

    private void buildRoof() {
        System.out.println("Building a simple roof");
    }

    // Steps to be implemented by subclasses
    protected abstract void buildWalls();
    protected abstract void paintHouse();
    protected abstract void decorateHouse();
}

