package design.lld.splitwise;


/*
SHOW
SHOW <user-id>
 */
public class ShowCommand implements Command {

  ExpenseManager expenseManager = ExpenseManager.getExpenseManager();

  @Override
  public void execute(String[] commandParams) {
    if (commandParams.length == 0) {
      expenseManager.showBalancesForAll();
    } else {
      expenseManager.showBalanceForAUser(commandParams[0]);
    }
  }
}
