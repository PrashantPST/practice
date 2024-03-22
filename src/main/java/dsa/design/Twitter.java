package dsa.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Twitter {

  private static long time = 0;

  static class Tweet {

    int id;
    long time;

    public Tweet(int id, long time) {
      this.id = id;
      this.time = time;
    }
  }

  Map<Integer, List<Tweet>> tweetsPostedByUser;
  Map<Integer, Set<Integer>> followees;

  public Twitter() {
    tweetsPostedByUser = new HashMap<>();
    followees = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    if (!tweetsPostedByUser.containsKey(userId)) {
      tweetsPostedByUser.put(userId, new ArrayList<>());
    }
    Tweet t = new Tweet(tweetId, time++);
    tweetsPostedByUser.get(userId).add(t);
  }

  public List<Integer> getNewsFeed(int userId) {
    List<Integer> ans = new ArrayList<>();
    PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> Long.compare(t2.time, t1.time));
    if (tweetsPostedByUser.containsKey(userId)) {
      pq.addAll(tweetsPostedByUser.get(userId));
    }
    if (followees.containsKey(userId)) {
      Set<Integer> f = followees.get(userId);
      for (Integer i : f) {
        if (tweetsPostedByUser.containsKey(i)) {
          pq.addAll(tweetsPostedByUser.get(i));
        }
      }
    }
    int n = 10;
    while (!pq.isEmpty() && n-- > 0) {
      Tweet t = pq.poll();
      ans.add(t.id);
    }
    pq.clear();
    return ans;
  }

  public void follow(int followerId, int followeeId) {
    if (!followees.containsKey(followerId)) {
      followees.put(followerId, new HashSet<>());
    }
    followees.get(followerId).add(followeeId);
  }

  public void unfollow(int followerId, int followeeId) {
    if (followees.containsKey(followerId)) {
      followees.get(followerId).remove(followeeId);
    }
  }
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId); List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId); obj.unfollow(followerId,followeeId);
 */