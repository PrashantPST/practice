package design.lld.leaderboard;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class LeaderboardApp {

  public static void main(String[] args) {
    try (JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost")) {
      LeaderboardRedis LeaderboardRedis = new LeaderboardRedis("game_scores", jedisPool);
      // Initial score setting
      LeaderboardRedis.addScore("Player1", 300);
      LeaderboardRedis.addScore("Player2", 150);
      LeaderboardRedis.addScore("Player3", 100);
      // Simulating concurrent score increments
      Thread player1Increment = new Thread(() -> LeaderboardRedis.incrementScore("Player1", 200));
      Thread player2Increment = new Thread(() -> LeaderboardRedis.incrementScore("Player2", 250));
      Thread player3Increment = new Thread(() -> LeaderboardRedis.incrementScore("Player3", 310));
      player1Increment.start();
      player2Increment.start();
      player3Increment.start();
      try {
        // Waiting for threads to complete execution
        player1Increment.join();
        player2Increment.join();
        player3Increment.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      // Displaying the top players after score updates
      System.out.println("Top players: " + LeaderboardRedis.getTopPlayersWithScores(3));
    }
  }
}



