package design.lld.splitwise.models.split;

import design.lld.splitwise.models.User;
import lombok.Getter;

@Getter
public class PercentSplit extends Split {

    double percent;
    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }
}
