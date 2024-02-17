package design.pattern.behavioral.mediator;

public interface AuctionMediator {
    void addBidder(Bidder bidder);
    void placeBid(Bidder bidder, double amount);
    void notifyBidders(String message, Bidder originator);
}

