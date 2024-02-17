package design.pattern.behavioral.strategy.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCard {
    private double amount;
    private String number;
    private String date;
    private String cvv;
}
