package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.split.Split;
import design.lld.splitwise.models.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@ToString
public abstract class Expense {
    private final String id;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final ExpenseMeta meta;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMeta metadata) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.meta = metadata;
    }

    public abstract boolean validate();
}
