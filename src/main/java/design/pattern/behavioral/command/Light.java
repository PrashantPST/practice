package design.pattern.behavioral.command;

public class Light implements Device {
    @Override
    public void on() {
        System.out.println("light on");
    }

    @Override
    public void off() {
        System.out.println("light off");
    }

    @Override
    public void up() {
        System.out.println("increase the brightness");
    }

    @Override
    public void down() {
        System.out.println("decrease the brightness");
    }
}
