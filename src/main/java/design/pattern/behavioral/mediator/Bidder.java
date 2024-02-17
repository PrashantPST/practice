package design.pattern.behavioral.mediator;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bidder {

    private final String name;
    private final AuctionMediator mediator;
    public void placeBid(double amount) {
        mediator.placeBid(this, amount);
    }

    public void receiveNotification(String message) {
        System.out.println(name + " received message: " + message);
    }
}
