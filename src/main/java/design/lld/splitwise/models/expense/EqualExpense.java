package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.User;
import design.lld.splitwise.models.split.EqualSplit;
import design.lld.splitwise.models.split.Split;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(String id, double amount, User paidBy, List<Split> splits) {
        super(id, amount, paidBy, splits);
    }

    /**
     * Validates if all the splits in this expense are of type EqualSplit.
     * @return true if all splits are EqualSplit; false otherwise.
     */
    @Override
    public boolean validate() {
        return getSplits().stream().allMatch(EqualSplit.class::isInstance);
    }
}

