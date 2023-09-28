package design.ratelimiter.tokenbucket;

public class TokenBucket {
    private final int numberOfRequests;
    private final int windowSizeForRateLimitInMilliSeconds;
    private final int maxBucketSize;
    private int numberOfTokenAvailable;
    private long lastRefillTime;
    private long nextRefillTime;

    public TokenBucket(int maxBucketSize, int numberOfRequests, int windowSizeForRateLimitInMilliSeconds) {
        this.maxBucketSize = maxBucketSize;
        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimitInMilliSeconds = windowSizeForRateLimitInMilliSeconds;
        this.refill();
    }

    public boolean tryConsume() {
        refill();
        if (this.numberOfTokenAvailable > 0) {
            this.numberOfTokenAvailable--;
            return true;
        }
        return false;
    }

    private void refill() {
        if (System.currentTimeMillis() < this.nextRefillTime) {
            return;
        }
        this.lastRefillTime = System.currentTimeMillis();
        this.nextRefillTime = this.lastRefillTime + this.windowSizeForRateLimitInMilliSeconds;
        this.numberOfTokenAvailable = Math.min(this.maxBucketSize, this.numberOfTokenAvailable + this.numberOfRequests);
    }
}
