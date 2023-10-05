package dsa;

import dsa.models.BinaryTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import static java.lang.Math.*;

public class DP {

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

    /*
    House Robber I
     */
    public int robI(int[] nums, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> (abs(b - x) == abs(a - x)) ? b : (abs(b - x)-  abs(a - x))
        );
        int n = nums.length;
        int first = nums[0];
        if (n == 1) {
            return nums[0];
        }
        int second = max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }

    /*
    House Robber II
     */
    public int robII(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return max(nums[0], nums[1]);
        }
        // Calculate the maximum amount by considering two scenarios:
        // 1. Rob the first house to the second-to-last house.
        // 2. Skip the first house and rob from the second house to the last house.
        return max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    private int robHelper(int[] nums, int start, int end) {
        int prevMax = 0; // Maximum amount that can be obtained if we skip the current house
        int currMax = 0; // Maximum amount that can be obtained if we rob the current house
        for (int i = start; i <= end; i++) {
            int temp = currMax;
            currMax = max(prevMax + nums[i], currMax);
            prevMax = temp;
        }
        return currMax;
    }

    /*
    House Robber III
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
        int maxAmount = max(robCurrentNode, skipCurrentNode);
        memo.put(node, maxAmount);
        return maxAmount;
    }

    public int numSquares(int n) {
        // Initialize an array to store minimum counts for each number up to n.
        int[] dp = new int[n + 1];

        // Initialize dp[0] to 0 because it takes 0 squares to represent 0.
        dp[0] = 0;

        // Fill the dp array from 1 to n.
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

    private int calculate(int[] nums, int index, int currentSum, int targetSum, Map<String, Integer> memo) {
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
}
