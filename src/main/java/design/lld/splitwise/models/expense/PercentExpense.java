package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.split.PercentSplit;
import design.lld.splitwise.models.split.Split;
import design.lld.splitwise.models.User;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMeta metadata) {
        super(amount, paidBy, splits, metadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }
        }
        // the total sum of percentage shares should be 100
        double sumSplitPercent = getSplits().stream().map(split -> (PercentSplit) split).mapToDouble(PercentSplit::getPercent).sum();
        return sumSplitPercent == 100;
    }
}
