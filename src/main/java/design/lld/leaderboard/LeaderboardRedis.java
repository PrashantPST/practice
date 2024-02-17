package design.lld.leaderboard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

public class LeaderboardRedis {

  private final JedisPool jedisPool;
  private final String leaderboardName;

  public LeaderboardRedis(String leaderboardName, JedisPool jedisPool) {
    this.jedisPool = jedisPool;
    this.leaderboardName = leaderboardName;
  }

  /**
   * Adds or updates the score for a given player in the leaderboard. This method uses Redis Sorted
   * Set to store players' scores. Each player is represented by a unique identifier, and their
   * score is stored as the score in the sorted set. If a player already exists in the set, their
   * score is updated. The time complexity of adding or updating a score in Redis Sorted Set using
   * ZADD command is O(log(N)), where N is the number of elements in the sorted set. This is because
   * Redis Sorted Sets are implemented using a data structure similar to a skip list and a hash
   * table.
   *
   * @param playerId The unique identifier of the player.
   * @param score    The score to add or update for the player.
   */
  public void addScore(String playerId, double score) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.zadd(leaderboardName, score, playerId);
    }
  }

  /**
   * Retrieves the top players along with their scores from the leaderboard.
   * This method accesses a Redis Sorted Set to fetch the top-ranking players based on their scores,
   * using the ZREVRANGEWITHSCORES command. The players are returned in descending order of their
   * scores, i.e., the player with the highest score is first.
   * Time Complexity: The time complexity for fetching the top 'k' players from the sorted set is
   * O(log(N) + k), where N is the number of elements in the sorted set. This comprises O(log(N))
   * for the initial access to the sorted set and O(k) for retrieving the top 'k' elements.
   *
   * @param k The number of top players to retrieve. If k is 5, the top 5 players are returned.
   * @return A map where each key is a player's identifier and the value is the player's score. The
   * map entries are ordered from the highest to the lowest score.
   */
  public Map<String, Long> getTopPlayersWithScores(int k) {
    Map<String, Long> topPlayers = new LinkedHashMap<>();
    try (Jedis jedis = jedisPool.getResource()) {
      Set<Tuple> playerScores = jedis.zrevrangeWithScores(leaderboardName, 0, k - 1);
      for (Tuple tuple : playerScores) {
        String player = tuple.getElement();
        Long score = (long) tuple.getScore();
        topPlayers.put(player, score);
      }
    }
    return topPlayers;
  }

  /**
   * Increments the score of a specific player in the leaderboard. This method employs the Redis
   * Sorted Set to increment the score of a player. If the player does not exist in the set, they
   * are added with the given increment as their score. If the player exists, their score is
   * incremented by the specified amount. The time complexity of incrementing a score in a Redis
   * Sorted Set using the ZINCRBY command is O(log(N)), where N is the number of elements in the
   * sorted set. This efficiency is due to the underlying implementation of Redis Sorted Sets, which
   * combines a skip list and a hash table, allowing for fast updates while maintaining order.
   *
   * @param playerId  The unique identifier of the player whose score is to be incremented.
   * @param increment The amount by which the player's score is to be increased.
   */
  public void incrementScore(String playerId, int increment) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.zincrby(leaderboardName, increment, playerId);
    }
  }
}
