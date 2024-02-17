package design.pattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class Auctioneer implements AuctionMediator {
    private final List<Bidder> bidders = new ArrayList<>();
    private double currentBid;
    private Bidder highestBidder;

    @Override
    public void addBidder(Bidder bidder) {
        bidders.add(bidder);
    }

    @Override
    public void placeBid(Bidder bidder, double amount) {
        if (amount > currentBid) {
            currentBid = amount;
            highestBidder = bidder;
            notifyBidders(bidder.getName() + " has placed a new high bid of $" + amount, bidder);
        }
    }

    @Override
    public void notifyBidders(String message, Bidder originator) {
        for (Bidder bidder : bidders) {
            if (bidder != originator) {
                bidder.receiveNotification(message);
            }
        }
    }

    public void announceWinner() {
        String winnerMessage = highestBidder != null
                ? "The winner is " + highestBidder.getName() + " with a bid of $" + currentBid
                : "Auction ended with no winner.";
        notifyBidders(winnerMessage, null);
    }
}

