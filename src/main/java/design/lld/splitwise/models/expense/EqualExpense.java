package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.split.EqualSplit;
import design.lld.splitwise.models.split.Split;
import design.lld.splitwise.models.User;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(String id, double amount, User paidBy, List<Split> splits, ExpenseMeta meta) {
        super(id, amount, paidBy, splits, meta);
    }

    @Override
    public boolean validate() {
        return getSplits().stream().allMatch(split -> split instanceof EqualSplit);
    }
}
