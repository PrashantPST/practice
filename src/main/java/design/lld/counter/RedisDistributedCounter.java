package design.lld.counter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisDistributedCounter {

  private static final String COUNTER_KEY = "my_counter";
  private final JedisPool jedisPool;

  public RedisDistributedCounter(String host, int port) {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(50);
    this.jedisPool = new JedisPool(poolConfig, host, port);
  }

  public void increment() {
    executeRedisOperation(jedis -> jedis.incr(COUNTER_KEY));
  }

  public void decrement() {
    executeRedisOperation(jedis -> jedis.decr(COUNTER_KEY));
  }

  public long getCount() {
    return executeRedisOperation(jedis -> {
      String value = jedis.get(COUNTER_KEY);
      return value != null ? Long.parseLong(value) : 0;
    });
  }

  private <T> T executeRedisOperation(RedisOperation<T> operation) {
    int attempts = 3;
    while (attempts > 0) {
      try (Jedis jedis = jedisPool.getResource()) {
        return operation.execute(jedis);
      } catch (JedisConnectionException e) {
        attempts--;
        if (attempts == 0) {
          throw e;
        }
        try {
          Thread.sleep(1000); // Wait before retry
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          throw new RuntimeException("Operation interrupted", ie);
        }
      }
    }
    return null;
  }

  public static void main(String[] args) {
    RedisDistributedCounter counter = new RedisDistributedCounter("localhost", 6379);

    try {
      counter.increment();
      System.out.println("Counter incremented.");
      System.out.println("Current counter value: " + counter.getCount());
      counter.decrement();
      System.out.println("Counter decremented.");
      System.out.println("Current counter value: " + counter.getCount());
    } catch (Exception e) {
      System.err.println("Error interacting with Redis: " + e.getMessage());
    }
  }
}
