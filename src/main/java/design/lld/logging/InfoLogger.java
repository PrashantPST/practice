package design.lld.logging;

public class InfoLogger extends Logger {
    public InfoLogger(LogLevel level) {
        super(level);
    }

    @Override
    protected void write(String message) {
        System.out.println("InfoLogger: " + message);
    }
}

