package design.pattern.behavioral.command;

public class Speaker implements Device {
    @Override
    public void on() {
        System.out.println("speaker on");
    }

    @Override
    public void off() {
        System.out.println("speaker off");
    }

    @Override
    public void up() {
        System.out.println("volume up");
    }

    @Override
    public void down() {
        System.out.println("volume down");
    }
}
