package design.lld.counter;

import redis.clients.jedis.Jedis;

@FunctionalInterface
public interface RedisOperation<T> {
  T execute(Jedis jedis);
}
