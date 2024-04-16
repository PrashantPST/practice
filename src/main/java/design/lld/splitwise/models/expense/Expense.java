package design.lld.splitwise.models.expense;

import design.lld.splitwise.models.User;
import design.lld.splitwise.models.split.Split;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Expense {

  private final String id;
  private final double amount;
  private final User paidBy;
  private final List<Split> splits;

  public Expense(String id, double amount, User paidBy, List<Split> splits) {
    this.id = id;
    this.amount = amount;
    this.paidBy = paidBy;
    this.splits = Collections.unmodifiableList(splits != null ? splits : Collections.emptyList());
  }

  /**
   * Validates the expense based on its specific rules.
   *
   * @return true if the expense is valid; false otherwise.
   */
  public abstract boolean validate();
}

