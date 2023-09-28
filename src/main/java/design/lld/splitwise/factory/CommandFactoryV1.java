package design.lld.splitwise.factory;

import design.lld.splitwise.Command;
import design.lld.splitwise.ExpenseCommand;
import design.lld.splitwise.ShowCommand;
import design.lld.splitwise.constants.Constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactoryV1 {
    private static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put(Constant.SHOW, new ShowCommand());
        commandsMap.put(Constant.EXPENSE, new ExpenseCommand());
    }

    public Optional<Command> getCommand(String commandName) {
        return Optional.ofNullable(commandsMap.get(commandName.toUpperCase()));
    }
}
