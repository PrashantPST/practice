package design.lld.splitwise;

import static design.lld.splitwise.constants.Constant.EQUAL;
import static design.lld.splitwise.constants.Constant.EXACT;
import static design.lld.splitwise.constants.Constant.PERCENT;

import design.lld.splitwise.enums.ExpenseType;
import design.lld.splitwise.models.split.EqualSplit;
import design.lld.splitwise.models.split.ExactSplit;
import design.lld.splitwise.models.split.PercentSplit;
import design.lld.splitwise.models.split.Split;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
EXPENSE u1 1250 2 u2 u3       EXACT 370 880
EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
 */
public class ExpenseCommand implements Command {

  ExpenseManager expenseManager = ExpenseManager.getExpenseManager();

  @Override
  public void execute(String[] commandParams) {
    System.out.println(Arrays.toString(commandParams));
    String paidBy = commandParams[0];
    double amount = Double.parseDouble(commandParams[1]);
    int noOfUsers = Integer.parseInt(commandParams[2]);
    String expenseType = commandParams[3 + noOfUsers];
    List<Split> splits = new ArrayList<>();
    switch (expenseType) {
      case EQUAL -> {
        for (int i = 0; i < noOfUsers; i++) {
          splits.add(new EqualSplit(expenseManager.users.get(commandParams[3 + i])));
        }
        expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits);
      }
      case EXACT -> {
        for (int i = 0; i < noOfUsers; i++) {
          splits.add(new ExactSplit(expenseManager.users.get(commandParams[3 + i]),
              Double.parseDouble(commandParams[4 + noOfUsers + i])));
        }
        expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits);
      }
      case PERCENT -> {
        for (int i = 0; i < noOfUsers; i++) {
          splits.add(new PercentSplit(expenseManager.users.get(commandParams[3 + i]),
              Double.parseDouble(commandParams[4 + noOfUsers + i])));
        }
        expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits);
      }
    }
  }
}
