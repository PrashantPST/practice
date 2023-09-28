package design.pattern.behavioral.command;

public class OffCommand implements Command {
    private final Device device;

    public OffCommand(final Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        this.device.off();
    }
}
