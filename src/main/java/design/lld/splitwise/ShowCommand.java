package design.lld.splitwise;


import design.lld.splitwise.constants.Constant;

/*
SHOW
SHOW <user-id>
 */
public class ShowCommand implements Command {

    ExpenseManager expenseManager = ExpenseManager.getExpenseManager();
    @Override
    public boolean isApplicable(String commandName) {
        return commandName.equalsIgnoreCase(Constant.SHOW);
    }

    @Override
    public void execute(String[] commandParams) {
        if (commandParams.length == 0) {
            expenseManager.showBalancesForAll();
        } else {
            expenseManager.showBalanceForAUser(commandParams[0]);
        }
    }

    @Override
    public String supportedCommand() {
        return Constant.SHOW;
    }
}
