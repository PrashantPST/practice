package design.lld.ratelimiter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class DistributedTokenBucket {

  private final Jedis redisClient;
  private final String bucketKey;
  private final int maxBucketSize;
  private final int refillRate;
  private final long refillIntervalInMillis;

  public DistributedTokenBucket(String redisHost, int redisPort, String bucketKey,
      int maxBucketSize, int refillRate, long refillIntervalInMillis) {
    this.redisClient = new Jedis(redisHost, redisPort);
    this.bucketKey = bucketKey;
    this.maxBucketSize = maxBucketSize;
    this.refillRate = refillRate;
    this.refillIntervalInMillis = refillIntervalInMillis;

    // Initialize bucket in Redis if not present
    if (!redisClient.exists(bucketKey)) {
      redisClient.hset(bucketKey, "tokens", String.valueOf(maxBucketSize));
      redisClient.hset(bucketKey, "lastRefillTime", String.valueOf(System.currentTimeMillis()));
    }
  }

  public boolean tryConsume() {
    long currentTime = System.currentTimeMillis();
    long lastRefillTime = Long.parseLong(redisClient.hget(bucketKey, "lastRefillTime"));
    int tokensToAdd = (int) ((currentTime - lastRefillTime) / refillIntervalInMillis) * refillRate;

    redisClient.watch(bucketKey);
    int currentTokens = Integer.parseInt(redisClient.hget(bucketKey, "tokens"));
    int newTokenCount = Math.min(maxBucketSize, currentTokens + tokensToAdd);

    if (newTokenCount > 0) {
      Transaction t = redisClient.multi();
      t.hset(bucketKey, "tokens", String.valueOf(newTokenCount - 1));
      t.hset(bucketKey, "lastRefillTime", String.valueOf(currentTime));
      t.exec();
      return true;
    } else {
      redisClient.unwatch();
      return false;
    }
  }

  // Ensure Redis connection is closed
  public void close() {
    redisClient.close();
  }
}
