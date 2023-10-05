package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.User;
import design.lld.splitwise.models.split.Split;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public abstract class Expense {
    private final String id;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final ExpenseMeta meta;

    public abstract boolean validate();
}
