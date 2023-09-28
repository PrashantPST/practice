package design.lld.splitwise.factory;

import design.lld.splitwise.Command;

import java.util.List;
import java.util.Optional;

public class CommandFactoryV2 {
    List<Command> commands;

    public Optional<Command> getCommand(String commandName) {

        for (Command command: commands) {
            if (command.isApplicable(commandName)) {
                return Optional.of(command);
            }
        }
        for (Command command: commands) {
            if (command.supportedCommand().equals(commandName)) {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }
}
