package design.lld.splitwise.models.split;

import design.lld.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Split {
    private final User user;
    protected double amount;

    public Split(User user) {
        this.user = user;
    }
}
