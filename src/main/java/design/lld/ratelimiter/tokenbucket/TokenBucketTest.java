package design.lld.ratelimiter.tokenbucket;

public class TokenBucketTest {
    public static void main(String[] args) {

        int numberOfRequest = 5;
        int maxBucketSize = 5;
        int windowSizeForRateLimitInMilliSeconds = 500;

        TokenBucket tokenBucket = new TokenBucket(maxBucketSize, numberOfRequest, windowSizeForRateLimitInMilliSeconds);

        int numberOfConsumed = 0;
        long startTime = System.currentTimeMillis();
        int totalTime = 5;
        while ((System.currentTimeMillis() - startTime) < totalTime) {
            boolean consumeSuccess = tokenBucket.tryConsume();
            System.out.println("try consume = " + consumeSuccess);
            if (consumeSuccess) {
                numberOfConsumed++;
            }
        }
        System.out.println("no of consumed request = " + numberOfConsumed);
        System.out.println("time taken = " + totalTime);
        System.out.println("no of request per window =" + (numberOfConsumed * windowSizeForRateLimitInMilliSeconds / totalTime));
        System.out.println("no of request per window expected = " + numberOfRequest);
    }
}
