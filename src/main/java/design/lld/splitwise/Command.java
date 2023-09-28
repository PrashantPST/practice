package design.lld.splitwise;

public interface Command {
    boolean isApplicable(String commandName);

    void execute(String[] commandParams);

    String supportedCommand();
}
