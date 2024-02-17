package design.lld.leaderboard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.Assert;

class LeaderboardPQ {
  private final Map<Integer, Integer> playerScores;

  public LeaderboardPQ() {
    playerScores = new HashMap<>();
  }

  public void addScore(int playerId, int score) {
    playerScores.merge(playerId, score, Integer::sum);
  }


  // O(M log M) for M >>> K
  public int top(int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int sum = 0;
    for (int e: playerScores.values()) {
      pq.offer(e);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    while(!pq.isEmpty()) {
      sum += pq.poll();
    }
    return sum;
  }

  public void reset(int playerId) {
    playerScores.remove(playerId);
  }

  public static void main(String[] args) {
    LeaderboardPQ leaderboardPQ = new LeaderboardPQ();
    leaderboardPQ.addScore(1, 73);
    leaderboardPQ.addScore(2, 56);
    leaderboardPQ.addScore(3, 39);
    leaderboardPQ.addScore(4, 51);
    leaderboardPQ.addScore(5, 4);
    Assert.assertEquals(leaderboardPQ.top(1), 73);
    leaderboardPQ.reset(1);
    leaderboardPQ.reset(2);
    leaderboardPQ.addScore(2, 51);
    Assert.assertEquals(leaderboardPQ.top(3), 141);
  }
}