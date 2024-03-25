package design.lld.splitwise;

import design.lld.splitwise.factory.CommandFactory;
import design.lld.splitwise.models.User;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;


public class Driver {
    public static void main(String[] args) {

        ExpenseManager expenseManager = ExpenseManager.getExpenseManager();

        CommandFactory commandFactory = new CommandFactory();

        expenseManager.addUser(
                User.builder().id("u1").name("User1").email("gaurav@workat.tech").phone("9876543210").build());
        expenseManager.addUser(
                User.builder().id("u2").name("User2").email("sagar@workat.tech").phone("9876543210").build());
        expenseManager.addUser(
                User.builder().id("u3").name("User3").email("hi@workat.tech").phone("9876543210").build());
        expenseManager.addUser(
                User.builder().id("u4").name("User4").email("mock-interviews@workat.tech").phone("9876543210").build());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] params = input.split("\\s+");
            String commandName = params[0];
            String[] commandParams = Arrays.copyOfRange(params, 1, params.length);
            Optional<Command> command = commandFactory.getCommand(commandName);
            if (command.isPresent()) {
                command.get().execute(commandParams);
            }
            else {
                return;
            }
        }
    }
}
