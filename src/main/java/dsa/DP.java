package dsa;

import static java.lang.Math.max;
import static java.lang.Math.min;

import dsa.models.BinaryTree;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DP {

  /*
 O(mn) TC && O(mn) SC
  */
  public static String longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int row = 1; row < m + 1; row++) {
      for (int col = 1; col < n + 1; col++) {
        if (text1.charAt(row - 1) == text2.charAt(col - 1)) {
          dp[row][col] = 1 + dp[row - 1][col - 1];
        } else {
          dp[row][col] = Util.max(dp[row - 1][col], dp[row][col - 1]);
        }
      }
    }
    return Util.buildSubsequence(dp, text1);
  }

  /**
   * Given an integer array coins representing coins of different denominations and an integer
   * amount representing a total amount of money, this method calculates the fewest number of coins
   * needed to make up that amount. If the amount of money cannot be made up by any combination of
   * the coins, the method returns -1. Assumptions: - You have an infinite number of each kind of
   * coin.
   *
   * @param coins  An array of integers representing different coin denominations. 1 <= coins.length
   *               <= 12 1 <= coins[i] <= 231 - 1 (2^31 - 1)
   * @param amount The total amount of money to be made up using the coins. 0 <= amount <= 10^4
   * @return The fewest number of coins needed to make up the given amount, or -1 if it is not
   * possible to make up the amount with the given coins. Example: Input: coins = [1, 2, 5], amount
   * = 11 Output: 3 Explanation: 11 = 5 + 5 + 1 Input: coins = [2], amount = 3 Output: -1
   * Explanation: It is not possible to make up the amount 3 with the coin 2.
   */
  public static int minNumberOfCoinsForChange(int amount, int[] coins) {
    int[] count = new int[amount + 1];
    Arrays.fill(count, Integer.MAX_VALUE);
    count[0] = 0; // No coins needed to make zero amount
    for (int coin : coins) {
      for (int amt = 1; amt <= amount; amt++) {
        if (coin <= amt && count[amt - coin] != Integer.MAX_VALUE) {
          count[amt] = Math.min(count[amt], count[amt - coin] + 1);
        }
      }
    }
    return count[amount] == Integer.MAX_VALUE ? -1 : count[amount];
  }

  public static int numberOfWaysToMakeChange(int amount, int[] coins) {
    int[] count = new int[amount + 1];
    // Base case: there's one way to make change for amount 0 (using no coins)
    count[0] = 1;
    for (int coin : coins) {
      for (int amt = 1; amt <= amount; amt++) {
        if (coin <= amt) {
          count[amt] += count[amt - coin];
        }
      }
    }
    return count[amount];
  }

  /*
  A message containing letters from A-Z can be encoded into numbers using the following mapping:
  'A' -> "1"
  'B' -> "2"
  ...
  'Z' -> "26"
  To decode an encoded message, all the digits must be grouped then mapped back into letters using the
  reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
  "AAJF" with the grouping (1 1 10 6)
  "KJF" with the grouping (11 10 6)
  Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is
  different from "06".
  Given a string s containing only digits, return the number of ways to decode it.
   */
  public int numDecodings(String s) {
    Map<Integer, Integer> memo = new HashMap<>();
    return helper(s, 0, memo);
  }

  private int helper(String s, int index, Map<Integer, Integer> memo) {
    if (index == s.length()) {
      return 1;
    }
    if (s.charAt(index) == '0') {
      return 0;
    }
    if (memo.containsKey(index)) {
      return memo.get(index);
    }
    int ways = helper(s, index + 1, memo);
    if (index < s.length() - 1) {
      int twoDigit = Integer.parseInt(s.substring(index, index + 2));
      if (twoDigit >= 10 && twoDigit <= 26) {
        ways += helper(s, index + 2, memo);
      }
    }
    memo.put(index, ways);
    return ways;
  }

  /**
   * House Robber I Calculates the maximum amount of money that can be robbed from houses along a
   * street without alerting the police.
   *
   * @param nums An integer array representing the amount of money in each house.
   * @return The maximum amount of money that can be robbed without alerting the police.
   * @throws IllegalArgumentException if nums is null or empty.
   */
  public int robI(int[] nums) {
    return robHelper(nums, 0, nums.length - 1);
  }

  /**
   * House Robber II
   */
  public int robII(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    // Calculate the maximum amount by considering two scenarios:
    // 1. Rob the first house to the second-to-last house.
    // 2. Skip the first house and rob from the second house to the last house.
    return max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
  }

  private int robHelper(int[] nums, int start, int end) {
    int prevMax = 0; // Maximum amount that can be obtained if we skip the current house
    int currMax = 0; // The Maximum amount that can be obtained if we rob the current house
    for (int i = start; i <= end; i++) {
      int temp = currMax;
      currMax = max(prevMax + nums[i], currMax);
      prevMax = temp;
    }
    return currMax;
  }

  /**
   * House Robber III
   */
  public int robIII(BinaryTree root) {
    Map<BinaryTree, Integer> memo = new HashMap<>();
    return helper(root, memo);
  }

  private int helper(BinaryTree node, Map<BinaryTree, Integer> memo) {
    if (node == null) {
      return 0;
    }
    if (memo.containsKey(node)) {
      return memo.get(node);
    }
    // Calculate the maximum value if we rob the current node
    int robCurrentNode = node.value;
    if (node.left != null) {
      robCurrentNode += helper(node.left.left, memo) + helper(node.left.right, memo);
    }
    if (node.right != null) {
      robCurrentNode += helper(node.right.left, memo) + helper(node.right.right, memo);
    }
    // Calculate the maximum value if we skip the current node
    int skipCurrentNode = helper(node.left, memo) + helper(node.right, memo);
    // Store the maximum value in the memoization map
    int maxAmount = Math.max(robCurrentNode, skipCurrentNode);
    memo.put(node, maxAmount);
    return maxAmount;
  }

  /**
   * House Robber IV
   */
  public int minCapability(int[] nums, int k) {
    int l = Arrays.stream(nums).min().getAsInt();
    int r = Arrays.stream(nums).max().getAsInt();
    int ans = Integer.MAX_VALUE;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (feasible(nums, mid, k)) {
        ans = Math.min(ans, mid);
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return ans;
  }

  public boolean feasible(int[] nums, int target, int k) {
    int lastIndex = -2;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= target && lastIndex + 1 < i) {
        k--;
        lastIndex = i;
        if (k == 0) {
          return true;
        }
      }
    }
    return false;
  }


  public int numSquares(int n) {
    // Initialize an array to store minimum counts for each number up to n.
    int[] dp = new int[n + 1];

    // Initialize dp[0] to 0 because it takes 0 squares to represent 0.
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      // Initialize dp[i] to i because the worst case is to use i 1s.
      dp[i] = i;
      // Try all perfect squares <= i
      for (int j = 1; j * j <= i; j++) {
        int square = j * j;
        dp[i] = min(dp[i], 1 + dp[i - square]);
      }
    }
    // The final result is stored in dp[n].
    return dp[n];
  }

  /*
  Partition equal subset sum
  can partition the array into 2 subsets such that sum of the elements in both subsets is equal
   */
  public boolean canPartition(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return false;
    }
    int sum = Arrays.stream(nums).sum();
    if (sum % 2 == 1) {
      return false;
    }
    int sumHalf = sum / 2;
    boolean[][] dp = new boolean[sumHalf + 1][nums.length + 1];
    Arrays.fill(dp[0], true);
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        dp[i][j] = dp[i][j - 1];
        if ((i - nums[j - 1]) >= 0) {
          dp[i][j] |= dp[i - nums[j - 1]][j - 1];
        }
      }
    }
    return dp[sumHalf][nums.length];
  }

  public int findTargetSumWays(int[] numbers, int targetSum) {
    return calculate(numbers, 0, 0, targetSum, new HashMap<>());
  }

  private int calculate(int[] nums, int index, int currentSum, int targetSum,
      Map<String, Integer> memo) {
    if (index == nums.length) {
      return currentSum == targetSum ? 1 : 0;
    }
    String memoKey = index + ":" + currentSum;
    if (memo.containsKey(memoKey)) {
      return memo.get(memoKey);
    }
    int add = calculate(nums, index + 1, currentSum + nums[index], targetSum, memo);
    int subtract = calculate(nums, index + 1, currentSum - nums[index], targetSum, memo);
    int totalWays = add + subtract;
    memo.put(memoKey, totalWays);
    return totalWays;
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] res = new boolean[n];
    Set<String> set = new HashSet<>(wordDict);
    for (int i = 0; i < n; i++) {
      String subs = s.substring(0, i + 1);
      if (set.contains(subs)) {
        res[i] = true;
      } else {
        for (int j = 0; j < i; j++) {
          if (res[j] && set.contains(s.substring(j + 1, i + 1))) {
            res[i] = true;
            break;
          }
        }
      }
    }
    return res[n - 1];
  }

  public int staircaseTraversal(int height, int maxSteps) {
    int[] ans = new int[height + 1];
    ans[0] = 1;
    for (int h = 1; h <= height; h++) {
      for (int j = h - 1; j >= Math.max(h - maxSteps, 0); j--) {
        ans[h] += ans[j];
      }
    }
    return ans[height];
  }


  /**
   * Minimum Path Sum Given an m x n grid filled with non-negative numbers, find a path from top
   * left to bottom right, which minimizes the sum of all numbers along its path. Note: You can only
   * move either down or right at any point in time.
   */
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 1; i < m; i++) {
      grid[i][0] += grid[i - 1][0];
    }
    for (int j = 1; j < n; j++) {
      grid[0][j] += grid[0][j - 1];
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
      }
    }
    return grid[m - 1][n - 1];
  }

  /**
   * Maximal Rectangle Given a rows x cols binary matrix filled with 0's and 1's, find the largest
   * rectangle containing only 1's and return its area.
   */
  public int maximalRectangle(char[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int ans = 0;
    int[][] dp = new int[m][n];
    for (int row = 0; row < m; row++) {
      if (matrix[row][n - 1] == '1') {
        ans = 1;
        dp[row][n - 1] = 1;
      }
    }
    for (int col = 0; col < n; col++) {
      if (matrix[m - 1][col] == '1') {
        ans = 1;
        dp[m - 1][col] = 1;
      }
    }
    for (int row = m - 2; row >= 0; row--) {
      for (int col = n - 2; col >= 0; col--) {
        if (matrix[row][col] == '0') {
          dp[row][col] = 0;
        } else {
          if (matrix[row + 1][col + 1] == '0') {

          }
        }
        ans = Math.max(ans, -1);
      }
    }
    return ans;
  }


  /**
   * Maximal Square Given an m x n binary matrix filled with 0's and 1's, find the largest square
   * containing only 1's and return its area.
   */
  public int maximalSquare(char[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int ans = 0;
    int[][] dp = new int[m][n];
    for (int row = 0; row < m; row++) {
      if (matrix[row][n - 1] == '1') {
        ans = 1;
        dp[row][n - 1] = 1;
      }
    }
    for (int col = 0; col < n; col++) {
      if (matrix[m - 1][col] == '1') {
        ans = 1;
        dp[m - 1][col] = 1;
      }
    }
    for (int row = m - 2; row >= 0; row--) {
      for (int col = n - 2; col >= 0; col--) {
        if (matrix[row][col] == '0') {
          dp[row][col] = 0;
        } else {
          dp[row][col] = 1 + Math.min(dp[row + 1][col], Math.min(dp[row][col + 1],
              dp[row + 1][col + 1]));
        }
        ans = Math.max(ans, dp[row][col] * dp[row][col]);
      }
    }
    return ans;
  }
}
