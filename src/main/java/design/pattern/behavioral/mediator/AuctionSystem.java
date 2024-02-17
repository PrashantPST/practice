package design.pattern.behavioral.mediator;

public class AuctionSystem {
    public static void main(String[] args) {
        Auctioneer auctioneer = new Auctioneer();
        Bidder bidder1 = new Bidder("John", auctioneer);
        Bidder bidder2 = new Bidder("Alice", auctioneer);

        auctioneer.addBidder(bidder1);
        auctioneer.addBidder(bidder2);

        bidder1.placeBid(100);
        bidder2.placeBid(150);

        auctioneer.announceWinner();
    }
}
