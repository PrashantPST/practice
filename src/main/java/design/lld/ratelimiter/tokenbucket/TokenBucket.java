package design.lld.ratelimiter.tokenbucket;

public class TokenBucket {
    private final int refillTokens;
    private final int windowSizeForRateLimitInMilliSeconds;
    private final int capacity;
    private int numberOfTokenAvailable;
    private long lastRefillTime;
    private long nextRefillTime;

    public TokenBucket(int capacity, int refillTokens, int windowSizeForRateLimitInMilliSeconds) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.windowSizeForRateLimitInMilliSeconds = windowSizeForRateLimitInMilliSeconds;
        this.refill();
    }

    public synchronized boolean tryConsume() {
        refill();
        if (this.numberOfTokenAvailable > 0) {
            this.numberOfTokenAvailable--;
            return true;
        }
        return false;
    }

    private synchronized void refill() {
        if (System.currentTimeMillis() < this.nextRefillTime) {
            return;
        }
        this.lastRefillTime = System.currentTimeMillis();
        this.nextRefillTime = this.lastRefillTime + this.windowSizeForRateLimitInMilliSeconds;
        this.numberOfTokenAvailable = Math.min(this.capacity, this.numberOfTokenAvailable + this.refillTokens);
    }
}
