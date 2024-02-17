package design.lld.logging;

public class DebugLogger extends Logger {
    public DebugLogger(LogLevel logLevel) {
        super(logLevel);
    }

    @Override
    protected void write(String message) {
        System.out.println("DebugLogger: " + message);
    }
}
