package design.pattern.behavioral.command;

public class RemoteApplication {
    public static void main(String[] args) {
        final Light light = new Light();

        final Invoker invoker = new Invoker(
                new OnCommand(light),
                new OffCommand(light),
                new UpCommand(light),
                new DownCommand(light)
        );

        invoker.clickOn();
        invoker.clickOff();
        invoker.clickUp();
        invoker.clickDown();

        final Speaker speaker = new Speaker();

        final Invoker speakerInvoker = new Invoker(
                new OnCommand(speaker),
                new OffCommand(speaker),
                new UpCommand(speaker),
                new DownCommand(speaker)
        );

        speakerInvoker.clickOn();
        speakerInvoker.clickOff();
        speakerInvoker.clickUp();
        speakerInvoker.clickDown();
    }
}
