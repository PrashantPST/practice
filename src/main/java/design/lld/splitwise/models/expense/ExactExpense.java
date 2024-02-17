package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.User;
import design.lld.splitwise.models.split.ExactSplit;
import design.lld.splitwise.models.split.Split;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(String id, double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMetadata) {
        super(id, amount, paidBy, splits, expenseMetadata);
    }

    /**
     * Validates if all the splits are of type ExactSplit and their total sum equals the expense amount.
     * @return true if both conditions are satisfied; false otherwise.
     */
    @Override
    public boolean validate() {
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
            sumSplitAmount += split.getAmount();
        }
        return getAmount() == sumSplitAmount;
    }
}

