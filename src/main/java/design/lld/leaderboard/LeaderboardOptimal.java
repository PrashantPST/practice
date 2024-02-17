package design.lld.leaderboard;

import java.util.*;

public class LeaderboardOptimal {
  private final Map<Integer, Integer> playerScores; // Maps playerId to score
  private final TreeMap<Integer, Integer> scoreFrequency; // Maps score to its frequency

  public LeaderboardOptimal() {
    playerScores = new HashMap<>();
    scoreFrequency = new TreeMap<>(Collections.reverseOrder());
  }

  public void addScore(int playerId, int score) {
    if (playerScores.containsKey(playerId)) {
      int currentScore = playerScores.get(playerId);
      int newScore = currentScore + score;
      playerScores.put(playerId, newScore);
      scoreFrequency.put(currentScore, scoreFrequency.get(currentScore) - 1);
      if (scoreFrequency.get(currentScore) == 0) {
        scoreFrequency.remove(currentScore);
      }
      scoreFrequency.merge(newScore, 1, Integer::sum);
    } else {
      playerScores.put(playerId, score);
      scoreFrequency.merge(score, 1, Integer::sum);
    }
  }


  // O(K)
  public int top(int K) {
    int sum = 0;
    for (var entry : scoreFrequency.entrySet()) {
      int times = Math.min(entry.getValue(), K);
      sum += entry.getKey() * times;
      K -= times;
      if (K <= 0) {
        break;
      }
    }
    return sum;
  }

  public void reset(int playerId) {
    int score = playerScores.get(playerId);
    playerScores.remove(playerId);
    scoreFrequency.put(score, scoreFrequency.get(score) - 1);
    if (scoreFrequency.get(score) == 0) {
      scoreFrequency.remove(score);
    }
  }

  // Example usage
  public static void main(String[] args) {
    LeaderboardOptimal leaderboardOptimal = new LeaderboardOptimal();
    leaderboardOptimal.addScore(1, 73);
    leaderboardOptimal.addScore(2, 56);
    leaderboardOptimal.addScore(3, 39);
    leaderboardOptimal.addScore(4, 51);
    leaderboardOptimal.addScore(5, 4);
    System.out.println(leaderboardOptimal.top(1));  // Output: 73
    leaderboardOptimal.reset(1);
    leaderboardOptimal.reset(2);
    leaderboardOptimal.addScore(2, 51);
    System.out.println(leaderboardOptimal.top(3));  // Output: 141
  }
}

