package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.split.ExactSplit;
import design.lld.splitwise.models.split.Split;
import design.lld.splitwise.models.User;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(String id, double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMetadata) {
        super(id, amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }
        //  the total sum of shares should be equal to the expense amount
        double totalAmount = getAmount();
        double sumSplitAmount = getSplits().stream().map(split -> (ExactSplit) split).mapToDouble(Split::getAmount).sum();
        return totalAmount == sumSplitAmount;
    }
}
