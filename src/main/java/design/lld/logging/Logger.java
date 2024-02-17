package design.lld.logging;


public abstract class Logger {

    protected LogLevel logLevel;
    protected Logger nextLogger;

    public Logger(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogLevel level, String message) {
        if (canHandle(level)) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    private boolean canHandle(LogLevel level) {
        return this.logLevel == level;
    }

    protected abstract void write(String message);
}



