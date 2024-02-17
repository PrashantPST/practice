package design.lld.logging;

public class ChainOfResponsibilityClient {
    public static void main(String[] args) {
        Logger loggerChain = LoggerChainCreator.getChainOfLoggers();

        loggerChain.logMessage(LogLevel.INFO, "This is an informational message.");
        loggerChain.logMessage(LogLevel.DEBUG, "This is a debug message.");
        loggerChain.logMessage(LogLevel.ERROR, "This is an error message.");
    }
}


