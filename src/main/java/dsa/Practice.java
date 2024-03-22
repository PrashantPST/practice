package dsa;

import static dsa.Util.EAST;
import static dsa.Util.calculateManhattanDistance;
import static dsa.Util.helper;
import static dsa.Util.inversionCount;
import static dsa.Util.mergeSort;
import static dsa.Util.prefixIsValid;
import static dsa.Util.swap;
import static java.util.Comparator.comparingInt;

import dsa.models.BinaryTree;
import dsa.models.Edge;
import dsa.models.NestedInteger;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Practice {

  private static final int[][] coordinates = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

  public static List<List<Integer>> subsets(List<Integer> array) {
    List<List<Integer>> ans = new ArrayList<>();
    ans.add(new ArrayList<>());
    for (Integer it : array) {
      int length = ans.size();
      for (int i = 0; i < length; i++) {
        List<Integer> a = new ArrayList<>(ans.get(i));
        a.add(it);
        ans.add(a);
      }
    }
    return ans;
  }

  /*
  O(n^2) tc and O(n^2) sc
 */
  public static int palindromePartitioningMinCuts(String s) {
    int n = s.length();
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], -1);
    }
    return minimumCuts(s, 0, n - 1, dp);
  }

  private static int minimumCuts(String s, int i, int j, int[][] dp) {
    if (Util.isPalindrome(s, i, j) || i >= j) {
      dp[i][j] = 0;
      return dp[i][j];
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    int answer = Integer.MAX_VALUE;
    int countCuts;
    for (int k = i; k < j; k++) {
      countCuts = minimumCuts(s, i, k, dp) + minimumCuts(s, k + 1, j, dp) + 1;
      answer = Math.min(answer, countCuts);
    }
    dp[i][j] = answer;
    return dp[i][j];
  }

  /*
  Time Complexity : O(n) Space : O(1)
   */
  public static int[] findUnsortedSubarray(int[] nums) {
    int start = -1;
    int end = -1;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    if (nums.length == 1) {
      return new int[]{-1, -1};
    }
    if (nums[0] > nums[1]) {
      min = nums[0];
      max = nums[0];
    }
    if (nums[nums.length - 2] > nums[nums.length - 1]) {
      min = Math.min(min, nums[nums.length - 1]);
      max = Math.max(max, nums[nums.length - 1]);
    }
    for (int i = 1; i < nums.length - 1; i++) {
      if ((nums[i - 1] > nums[i]) || (nums[i] > nums[i + 1])) {
        min = Math.min(min, nums[i]);
      }
    }
    for (int i = 1; i < nums.length - 1; i++) {
      if ((nums[i - 1] > nums[i]) || (nums[i] > nums[i + 1])) {
        max = Math.max(max, nums[i]);
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > min) {
        start = i;
        break;
      }
    }
    for (int i = nums.length - 1; i >= 0; i--) {
      if (nums[i] < max) {
        end = i;
        break;
      }
    }
    return (start == -1 && end == -1) ? new int[]{-1, -1} : new int[]{start, end};
  }

  public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
    int n = array.length;
    int[] prevIndex = new int[n];
    int[] dp = new int[n];
    Arrays.fill(prevIndex, -1);
    int endIndex = 0;
    int maxSum = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      dp[i] = array[i];
      for (int j = 0; j < i; j++) {
        if (array[j] < array[i] && dp[i] < dp[j] + array[i]) {
          dp[i] = dp[j] + array[i];
          prevIndex[i] = j;
        }
      }
      if (dp[i] > maxSum) {
        maxSum = dp[i];
        endIndex = i;
      }
    }
    List<Integer> maxSumSubarray = new ArrayList<>();
    while (endIndex != -1) {
      maxSumSubarray.add(array[endIndex]);
      endIndex = prevIndex[endIndex];
    }
    Collections.reverse(maxSumSubarray);
    List<List<Integer>> result = new ArrayList<>();
    result.add(Collections.singletonList(maxSum));
    result.add(maxSumSubarray);
    return result;
  }

  public static String addStrings(String num1, String num2) {
    StringBuilder result = new StringBuilder();
    int carry = 0;
    int i = num1.length() - 1;
    int j = num2.length() - 1;

    while (i >= 0 || j >= 0 || carry != 0) {
      int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
      int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;

      int sum = digit1 + digit2 + carry;
      carry = sum / 10;
      result.append(sum % 10);

      i--;
      j--;
    }
    return result.reverse().toString();
  }

  /*
  DP: O(n^2) TC and O(n) SC
  TODO: O(nlogn) TC and O(n) SC
   */
  public static List<Integer> longestIncreasingSubsequence(int[] array) {
    int[] sequences = new int[array.length];
    int[] lengths = new int[array.length];
    Arrays.fill(sequences, -1);
    Arrays.fill(lengths, 1);
    int maxLengthIdx = 0;
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < i; j++) {
        if (array[j] < array[i] && lengths[j] + 1 > lengths[i]) {
          lengths[i] = lengths[j] + 1;
          sequences[i] = j;
        }
      }
      if (lengths[i] > lengths[maxLengthIdx]) {
        maxLengthIdx = i;
      }
    }
    List<Integer> sequence = new ArrayList<>();
    while (maxLengthIdx != -1) {
      sequence.add(0, array[maxLengthIdx]);
      maxLengthIdx = sequences[maxLengthIdx];
    }
    return sequence;
  }

  /*
  Jump Game
  You are initially positioned at the array's first index, and each element in the array represents
  your maximum jump length at that position
  Return true if you can reach the last index, or false otherwise.
   */
  public static boolean canJump(int[] nums) {
    int maxReachable = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > maxReachable) {
        return false;
      }
      maxReachable = Math.max(maxReachable, i + nums[i]);
    }
    return true;
  }

  /*
  Jump Game II
  You are initially positioned at nums[0].
  Each element nums[i] represents the maximum length of a forward jump from index i.
  Return the minimum number of jumps to reach nums[n - 1]
   */
  public static int minNumberOfJumps(int[] nums) {
    final int size = nums.length;
    int destination = size - 1;
    int curCoverage = 0, lastJumpIdx = 0;
    int timesOfJump = 0;
    for (int i = 0; i < size; i++) {
      if (i == destination) {
        return timesOfJump;
      }
      curCoverage = Math.max(curCoverage, i + nums[i]);
      if (i == lastJumpIdx) {
        lastJumpIdx = curCoverage;
        timesOfJump++;
      }
    }
    return timesOfJump;
  }

  /*
  O(nc) TC and O(nc) SC, n: number of items and c: capacity
   */
  public static List<List<Integer>> zeroOneKnapsackProblem(int[][] items, int capacity) {
    int[][] dp = new int[items.length][capacity + 1];
    for (int col = 1; col <= capacity; col++) {
      dp[0][col] = items[0][1] <= col ? items[0][0] : 0;
    }
    for (int row = 1; row < items.length; row++) {
      for (int col = 1; col <= capacity; col++) {
        int local = 0;
        if (col - items[row][1] >= 0) {
          local = items[row][0] + dp[row - 1][col - items[row][1]];
        }
        dp[row][col] = Math.max(dp[row - 1][col], local);
      }
    }
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> a = new ArrayList<>();
    List<Integer> b = new ArrayList<>();
    a.add(dp[items.length - 1][capacity]);
    ans.add(a);
    int row = items.length - 1;
    int col = capacity;
    int wt = capacity;
    while (wt > 0) {
      if (row < 1 || dp[row][col] != dp[row - 1][col]) {
        if (wt - items[row][1] < 0) {
          break;
        }
        wt -= items[row][1];
        b.add(0, row);
        col = col - items[row][1];
      }
      row--;
    }
    ans.add(b);
    return ans;
  }

  public static ArrayList<Integer> findOrder(ArrayList<Integer> height,
      ArrayList<Integer> infront) {
    // Number of people in a queue.
    int n = height.size();
    int[][] people = new int[n][2];
    for (int i = 0; i < n; i++) {
      people[i][0] = height.get(i);
      people[i][1] = infront.get(i);
    }
    Arrays.sort(people, comparingInt(a -> a[0]));
    for (int i = 0; i < n; i++) {
      height.set(i, people[i][0]);
      infront.set(i, people[i][1]);
    }
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      result.add(0);
    }
    for (int i = 0; i < n; i++) {
      int countEmpty = 0;
      for (int j = 0; j < n; j++) {
        if (result.get(j) == 0 && countEmpty < infront.get(i)) {
          countEmpty++;
        } else if (result.get(j) == 0 && countEmpty == infront.get(i)) {
          result.set(j, height.get(i));
          break;
        }
      }
    }
    return result;
  }

  /*
  minimum-window-substring
  TC and SC: O(b + s)
  b: length of bigString
  s: length of smallString
   */
  public static String smallestSubstringContaining(String bigString, String smallString) {
    int bigStringLength = bigString.length();
    int smallStringLength = smallString.length();
    // Check if bigString is smaller than smallString, in which case no valid substring can be found.
    if (bigStringLength < smallStringLength) {
      return "";
    }
    int[] pat1 = new int[128];
    int[] pat2 = new int[128];
    for (char ch : smallString.toCharArray()) {
      pat2[ch]++;
    }
    int left = 0;
    int right = 0;
    int count = 0;
    int minLength = Integer.MAX_VALUE;
    String result = "";
    while (right < bigStringLength) {
      char currentChar = bigString.charAt(right);
      if (pat2[currentChar] != 0 && pat1[currentChar] < pat2[currentChar]) {
        count++;
      }
      pat1[currentChar]++;
      while (count == smallStringLength) {
        int currentWindowSize = right - left + 1;
        if (currentWindowSize < minLength) {
          minLength = currentWindowSize;
          result = bigString.substring(left, right + 1);
        }
        char chh = bigString.charAt(left);
        pat1[chh]--;
        if (pat2[chh] != 0 && pat1[chh] < pat2[chh]) {
          count--;
        }
        left++;
      }
      right++;
    }
    return result;
  }

  /*
      O(N log K) and a space complexity of O(N)
   */
  public static List<String> topKFrequentWords(String[] words, int k) {
    Map<String, Integer> cnt = new HashMap<>();
    for (String word : words) {
      cnt.merge(word, 1, Integer::sum);
    }
    PriorityQueue<String> h = new PriorityQueue<>(
        (word1, word2) -> cnt.get(word1).equals(cnt.get(word2)) ? word2.compareTo(word1)
            : cnt.get(word1) - cnt.get(word2)
    );
    for (String word : cnt.keySet()) {
      h.offer(word);
      // Remove the least frequent word if the heap size exceeds K
      if (h.size() > k) {
        h.poll();
      }
    }
    List<String> res = new LinkedList<>();
    while (!h.isEmpty()) {
      res.add(0, h.poll());  // Add each word at the beginning of the list
    }
    return res;
  }

  /*
      finds all the cells in a matrix (heights) from which water can flow to both
      the Pacific and Atlantic oceans.
   */
  public static List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> res = new ArrayList<>();
    int rows = heights.length, cols = heights[0].length;
    boolean[][] pacific = new boolean[rows][cols];
    boolean[][] atlantic = new boolean[rows][cols];
    for (int i = 0; i < cols; i++) {
      dfs(heights, 0, i, Integer.MIN_VALUE, pacific);
      dfs(heights, rows - 1, i, Integer.MIN_VALUE, atlantic);
    }
    for (int i = 0; i < rows; i++) {
      dfs(heights, i, 0, Integer.MIN_VALUE, pacific);
      dfs(heights, i, cols - 1, Integer.MIN_VALUE, atlantic);
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          res.add(Arrays.asList(i, j));
        }
      }
    }
    return res;
  }

  private static void dfs(
      int[][] heights,
      int i,
      int j,
      int prev,
      boolean[][] ocean) {
    if (i < 0 || i >= ocean.length || j < 0 || j >= ocean[0].length) {
      return;
    }
    if (heights[i][j] < prev || ocean[i][j]) {
      return;
    }
    ocean[i][j] = true;
    for (int[] d : coordinates) {
      dfs(heights, i + d[0], j + d[1], heights[i][j], ocean);
    }
  }

  public static int longestOnes(int[] nums, int k) {
    int left = 0, max_length = 0;

    for (int right = 0; right < nums.length; right++) {
      // If we encounter a 0, decrease k
      if (nums[right] == 0) {
        k--;
      }

      // When k is less than 0, makeMove the left pointer to the right
      while (k < 0) {
        if (nums[left] == 0) {
          k++;
        }
        left++;
      }

      // Calculate the length of the current window
      max_length = Math.max(max_length, right - left + 1);
    }

    return max_length;
  }

  /*
      TC - O(n) and SC - O(n) where n is the length of the input string
   */
  public static String minRemoveToMakeValid(String s) {
    Set<Integer> indexesToRemove = new HashSet<>();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else if (s.charAt(i) == ')') {
        if (stack.isEmpty()) {
          indexesToRemove.add(i);
        } else {
          stack.pop();
        }
      }
    }
    // Add remaining indices from the stack to the set (unmatched '(')
    while (!stack.isEmpty()) {
      indexesToRemove.add(stack.pop());
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (!indexesToRemove.contains(i)) {
        sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }

  /**
   * Finds the two lines that, along with the x-axis, form a container capable of holding the
   * maximum amount of water. This method is designed to solve the "Container With Most Water"
   * problem, where the goal is to maximize the volume of water that can be contained within the
   * bounds of two vertical lines (heights represented by an array) and the x-axis. The approach
   * used here involves a two-pointer technique that scans the array from both ends, moving inward.
   * This ensures an efficient traversal of the possible pairs with a time complexity of O(n) and
   * space complexity of O(1)
   *
   * @param heights an array of integers where each element represents the height of a line on the
   *                x-axis.
   * @return The maximum amount of water that can be contained within the bounds of two lines.
   */
  public static int maxWaterArea(int[] heights) {
    int left = 0;
    int right = heights.length - 1;
    int res = 0;
    while (left < right) {
      int containerLength = right - left;
      int area = containerLength * Math.min(heights[left], heights[right]);
      res = Math.max(res, area);
      if (heights[left] < heights[right]) {
        left++;
      } else {
        right--;
      }
    }
    return res;
  }

  /**
   * Best Time to Buy and Sell Stock I, a single transaction Buy and Sell Stock I
   */
  public static int maxProfitI(int[] prices) {
    int profit = 0, minPrice = Integer.MAX_VALUE;
    for (int price : prices) {
      minPrice = Math.min(minPrice, price);
      profit = Math.max(profit, price - minPrice);
    }
    return profit;
  }

  /**
   * Best Time to Buy and Sell Stock II, as many transactions Buy and Sell Stock II
   */
  public static int maxProfitII(int[] prices) {
    return IntStream.range(1, prices.length)
        .map(i -> Math.max(0, prices[i] - prices[i - 1]))
        .sum();
  }

  /**
   * Find the maximum profit you can achieve. You may complete at most two transactions Buy and Sell
   * Stock III
   */
  public static int maxProfitIII(int[] prices) {
    int n = prices.length;
    if (n == 1) {
      return 0;
    }
    int[] l = new int[n];
    int[] r = new int[n];
    int min = prices[0];
    int max = prices[n - 1];
    for (int i = 1; i < n; i++) {
      l[i] = Math.max(l[i - 1], prices[i] - min);
      r[n - i - 1] = Math.max(r[n - i], max - prices[n - i - 1]);
      min = Math.min(min, prices[i]);
      max = Math.max(max, prices[n - i - 1]);
    }
    return IntStream.range(0, prices.length)
        .map(i -> l[i] + r[i])
        .max()
        .orElse(0);
  }

  /**
   * Find the maximum profit, at most k buy-and-sell transactions Buy and Sell Stock IV
   */
  public static int maxProfitIV(int[] prices, int k) {
    int days = prices.length;
    if (days == 0) {
      return 0;
    }
    int[][] dp = new int[k + 1][days];
    for (int transactions = 1; transactions <= k; transactions++) {
      int local = -prices[0];
      for (int c = 1; c < days; c++) {
        local = Math.max(local, dp[transactions - 1][c - 1] - prices[c - 1]);
        dp[transactions][c] = Math.max(dp[transactions][c - 1], prices[c] + local);
      }
    }
    return dp[k][days - 1];
  }

  /**
   * Best Time to Buy and Sell Stock with Cool down Buy and Sell Stock V
   */
  public int maxProfitV(int[] prices) {
    int n = prices.length;
    int[][] cache = new int[n][2];
    for (int[] c : cache) {
      Arrays.fill(c, -1);
    }
    return calculateMaxProfit(prices, 0, 0, cache);
  }

  private int calculateMaxProfit(int[] prices, int day, int holding, int[][] cache) {
    if (day >= prices.length) {
      return 0;
    }
    if (cache[day][holding] != -1) {
      return cache[day][holding];
    }
    int noAction = calculateMaxProfit(prices, day + 1, holding, cache);
    int action;
    if (holding == 0) {
      // Option to buy the stock
      action = -prices[day] + calculateMaxProfit(prices, day + 1, 1, cache);
    } else {
      // Option to sell the stock and skip the next day
      action = prices[day] + calculateMaxProfit(prices, day + 2, 0, cache);
    }
    cache[day][holding] = Math.max(action, noAction);
    return cache[day][holding];
  }

  /**
   * Best Time to Buy and Sell Stock with Transaction Fee Buy and Sell Stock VI
   */
  public int maxProfitVI(int[] prices, int fee) {
    int n = prices.length;
    int[][] profitCache = new int[n][2]; // Renamed for clarity
    for (int[] row : profitCache) {
      Arrays.fill(row, -1);
    }
    return calculateProfit(prices, 0, 0, profitCache, fee);
  }

  private int calculateProfit(int[] prices, int day, int holding, int[][] profitCache, int fee) {
    if (day >= prices.length) {
      return 0;
    }
    if (profitCache[day][holding] != -1) {
      return profitCache[day][holding];
    }
    // No action for the day
    int noActionProfit = calculateProfit(prices, day + 1, holding, profitCache, fee);
    // Either buy or sell based on 'holding'
    int actionProfit;
    if (holding == 0) {
      // Buying stock
      actionProfit = -prices[day] + calculateProfit(prices, day + 1, 1, profitCache, fee);
    } else {
      // Selling stock, include fee in calculation
      actionProfit = prices[day] - fee + calculateProfit(prices, day + 1, 0, profitCache, fee);
    }
    // Cache and return the higher profit option
    profitCache[day][holding] = Math.max(noActionProfit, actionProfit);
    return profitCache[day][holding];
  }


  public static int[] calculateStockSpan(int[] prices) {
    int[] span = new int[prices.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < prices.length; i++) {
      while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
        stack.pop();
      }
      span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
      stack.push(i);
    }
    return span;
  }

  /*
  O(n) TC and O(1) SC
   */
  public static int findStartingGasStation(int[] gas, int[] cost) {
    int gasSum = 0;     // Total gas available
    int costSum = 0;    // Total cost to travel
    int start = 0;      // Starting index
    int tank = 0;       // Current gas tank
    for (int i = 0; i < gas.length; i++) {
      gasSum += gas[i];
      costSum += cost[i];
      tank += gas[i] - cost[i];
      // If the tank goes negative, reset the starting index
      if (tank < 0) {
        start = i + 1;
        tank = 0;
      }
    }
    // If the total gas is less than the total cost, it's impossible to complete the tour
    return gasSum < costSum ? -1 : start;
  }

  public static List<Integer> countDistinctElements(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> result = new ArrayList<>();
    int distinctCount = 0;
    for (int i = 0; i < arr.length; i++) {
      map.merge(arr[i], 1, Integer::sum);
      if (map.get(arr[i]) == 1) {
        distinctCount++;
      }
      // Remove element that goes out of the window
      if (i >= k) {
        int out = arr[i - k];
        map.put(out, map.get(out) - 1);
        if (map.get(out) == 0) {
          distinctCount--;
          map.remove(out);
        }
      }
      // Store the result for each valid window
      if (i >= k - 1) {
        result.add(distinctCount);
      }
    }
    return result;
  }

  /**
   * Calculates the number of days to wait until a warmer temperature is encountered for each day.
   * If there is no future day with a warmer temperature, the corresponding value in the output
   * array will be 0.
   *
   * @param temperatures An array of integers representing daily temperatures.
   * @return An array where each element represents the number of days to wait until a warmer
   * temperature is encountered.
   */
  public static int[] nextWarmerDay(int[] temperatures) {
    int n = temperatures.length;
    int[] arr = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
        stack.pop();
      }
      arr[i] = stack.isEmpty() ? 0 : stack.peek() - i;
      stack.push(i);
    }
    return arr;
  }

  public static int[] findNextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= nums[i]) {
        stack.pop();
      }
      result[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(nums[i]);
    }
    return result;
  }

  /*
  TC - O(NlogK) SC - O(K)
   */
  public static int[] topKFrequentNumbers(int[] numbers, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    Arrays.stream(numbers).forEach(num -> countMap.merge(num, 1, Integer::sum));
    PriorityQueue<Integer> pq = new PriorityQueue<>(comparingInt(countMap::get));
    countMap.keySet().forEach(num -> {
      pq.offer(num);
      if (pq.size() > k) {
        pq.poll();
      }
    });
    int[] topK = new int[k];
    for (int i = k - 1; i >= 0; --i) {
      topK[i] = pq.poll();
    }
    return topK;
  }

  public static int maxLengthAlternatingSequence(int[] arr) {
    int ans = 1;
    int currentLength = 1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] * arr[i] < 0) {
        currentLength++;
        ans = Math.max(currentLength, ans);
      } else {
        currentLength = 1;
      }
    }
    return ans;
  }

  /*
  Brute force - O(nk) O(1)
  max heap - O(nlogk) O(k)
  deque - O(n) O(k)
   */
  public static int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> dq = new ArrayDeque<>();
    int[] res = new int[nums.length - k + 1];
    int idx = 0;
    for (int i = 0; i < k; i++) {
      while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
        dq.pollLast();
      }
      dq.offerLast(i);
    }
    res[idx++] = nums[dq.peekFirst()];
    for (int i = k; i < nums.length; i++) {
      if (dq.peekFirst() == i - k) {
        dq.pollFirst();
      }
      while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
        dq.pollLast();
      }
      dq.offerLast(i);
      res[idx++] = nums[dq.peekFirst()];
    }
    return res;
  }

  /*
    find the longest substring without repeating characters
    O(n) TC O(n) SC
   */
  public static String longestSubstringWithoutRepeatingCharacters(String str) {
    int longestLength = 0;
    int start = 0;
    int longestStart = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (map.containsKey(ch) && map.get(ch) >= start) {
        start = map.get(ch) + 1;
      }
      int currentLength = i - start + 1;
      map.put(ch, i);
      if (currentLength > longestLength) {
        longestStart = start;
        longestLength = currentLength;
      }
    }
    return str.substring(longestStart, longestStart + longestLength);
  }

  /*
    Permutations I
    time complexity of the algorithm is O(n * n!)
    sc - O(n)
   */
  public List<List<Integer>> permute(int[] numbs) {
    List<List<Integer>> results = new ArrayList<>();
    permuteHelper(numbs, 0, results);
    return results;
  }

  /*
    Permutations II
    Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations
    in any order.
   */
  public List<List<Integer>> permuteUnique(int[] numbs) {
    List<List<Integer>> results = new ArrayList<>();
    permuteUniqueHelper(numbs, 0, results);
    return results;
  }

  private void permuteUniqueHelper(int[] numbs, int i, List<List<Integer>> ans) {
    if (i == numbs.length - 1) {
      ans.add(Arrays.stream(numbs).boxed().toList());
      return;
    }
    Set<Integer> set = new HashSet<>();
    for (int j = i; j < numbs.length; j++) {
      if (!set.add(numbs[j])) {
        continue;
      }
      swap(numbs, i, j);
      permuteUniqueHelper(numbs, i + 1, ans);
      swap(numbs, i, j);
    }
  }

  private void permuteHelper(int[] numbs, int i, List<List<Integer>> results) {
    if (i == numbs.length - 1) {
      List<Integer> list = Arrays.stream(numbs).boxed().toList();
      results.add(list);
      return;
    }
    for (int j = i; i < numbs.length; i++) {
      swap(numbs, i, j);
      permuteHelper(numbs, i + 1, results);
      swap(numbs, i, j); // backtrack
    }
  }

  /*
  O(n^2) TC and O(1) SC
   */
  public String longestPalindromicSubstring(String str) {
    String ans = "";
    for (int i = 0; i < str.length(); i++) {
      String temp = expandAroundCenterForLongestPalindromicSubstring(str, i, i);
      if (temp.length() > ans.length()) {
        ans = temp;
      }
      temp = expandAroundCenterForLongestPalindromicSubstring(str, i - 1, i);
      if (temp.length() > ans.length()) {
        ans = temp;
      }
    }
    return ans;
  }

  private String expandAroundCenterForLongestPalindromicSubstring(String str, int left, int right) {
    while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
      left--;
      right++;
    }
    return str.substring(left + 1, right);
  }

  /*
 return the number of palindromic substrings
 TC: O(N^2)
  */
  public int countPalindromicSubstrings(String str) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      count += expandAroundCenterForCountOfPalindromicSubstrings(str, i, i) +
          expandAroundCenterForCountOfPalindromicSubstrings(str, i - 1, i);
    }
    return count;
  }

  private int expandAroundCenterForCountOfPalindromicSubstrings(String str, int left, int right) {
    int count = 0;
    while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
      count++;
      left--;
      right++;
    }
    return count;
  }

  /*
  O(n) TC
  O(n) SC for set
   */
  public int[] longestConsecutiveSequence(int[] nums) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    int max = 0;
    int[] ans = new int[2];
    for (int num : nums) {
      int start = num;
      if (!set.contains(num - 1)) {
        int count = 0;
        while (set.contains(num)) {
          num++;
          count++;
        }
        int end = num - 1;
        if (count > max) {
          ans[0] = start;
          ans[1] = end;
          max = count;
        }
      }
    }
    return ans;
  }


  // time complexity of O(n) and a space complexity of O(1)
  public void nextPermutation(int[] nums) {
    // Step 1: Find the first decreasing element from the right
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
    // Step 2: If such an element was found, find the element to swap it with
    if (i >= 0) {
      int j = nums.length - 1;
      while (nums[j] <= nums[i]) {
        j--;
      }
      // Swap nums[i] and nums[j]
      swap(nums, i, j);
    }
    // Step 3: Reverse the subarray to the right of the initially found element
    Util.reverse(nums, i + 1, nums.length - 1);
  }

  /*
      words[i] consists of lowercase English letters.
      N be the number of strings and M be the maximum length of a string in words.
      TC - O(N * M)
      SC -  O(N)
   */
  public Set<Set<String>> groupAnagrams(String[] words) {
    Map<String, Set<String>> ans = new HashMap<>();
    Arrays.stream(words).forEach(word -> {
      int[] hash = new int[26];
      for (char c : word.toCharArray()) {
        hash[c - 'a']++;
      }
      String key = Arrays.toString(hash);
      ans.computeIfAbsent(key, k -> new HashSet<>()).add(word);
    });
    return new HashSet<>(ans.values());
  }

  /*
  Unique Paths I
  A robot is initially located at grid[0][0]
  The robot tries to makeMove to the grid[m - 1][n - 1]
  The robot can only makeMove either down or right, return the number of possible unique paths
   */
  public int uniquePaths(int m, int n) {
    BigInteger ans1 = Util.factorial(BigInteger.valueOf(m + n - 2));
    BigInteger ans2 = Util.factorial(BigInteger.valueOf(m - 1));
    BigInteger ans3 = Util.factorial(BigInteger.valueOf(n - 1));
    return (ans1.divide(ans2.multiply(ans3))).intValue();
  }

  /*
  Unique Paths II
  An obstacle and space are marked as 1 or 0 respectively,
  A path that the robot takes cannot include any square that is an obstacle
  Return the number of possible unique paths
   */
  public int uniquePathsWithObstacles(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    mat[0][0] = 1 - mat[0][0];
    IntStream.range(1, m).forEach(row -> mat[row][0] = mat[row][0] == 1 ? 0 : mat[row - 1][0]);
    IntStream.range(1, n).forEach(col -> mat[0][col] = mat[0][col] == 1 ? 0 : mat[0][col - 1]);
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        mat[i][j] = mat[i][j] == 1 ? 0 : mat[i - 1][j] + mat[i][j - 1];
      }
    }
    return mat[m - 1][n - 1];
  }

  public int getSum(int x, int y) {
    // Iterate till there is no carry
    while (y != 0) {
      // carry now contains common
      // set bits of x and y
      int carry = x & y;

      // Sum of bits of x and
      // y where at least one
      // of the bits is not set
      x = x ^ y;

      // Carry is shifted by
      // one so that adding it
      // to x gives the required sum
      y = carry << 1;
    }
    return x;
  }

  public int reverse(int x) {
    long ans = 0;
    boolean negative = false;
    if (x < 0) {
      negative = true;
      x = -x;
    }
    while (x > 0) {
      ans = 10 * ans + x % 10;
      x /= 10;
    }
    if (ans < Integer.MIN_VALUE || ans > Integer.MAX_VALUE) {
      return 0;
    }
    return (int) (negative ? -ans : ans);
  }

  public List<List<Integer>> levelOrder(BinaryTree root) {
    Queue<BinaryTree> q = new LinkedList<>();
    List<List<Integer>> traversal = new ArrayList<>();
    if (root == null) {
      return traversal;
    }
    int levelNodes;
    q.add(root);
    while (!q.isEmpty()) {
      List<Integer> temp = new ArrayList<>();
      levelNodes = q.size();
      while (levelNodes-- > 0) {
        BinaryTree current = q.remove();
        temp.add(current.value);
        if (current.left != null) {
          q.add(current.left);
        }
        if (current.right != null) {
          q.add(current.right);
        }
      }
      traversal.add(temp);
    }
    return traversal;
  }

  /**
   * return the kth largest element in the array Time complexity: O(nlogk) Space complexity: O(k)
   */
  public Optional<Integer> findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    Arrays.stream(nums).forEach(num -> {
      heap.add(num);
      if (heap.size() > k) {
        heap.remove();
      }
    });
    return Optional.ofNullable(heap.peek());
  }

  public Optional<Integer> findKthSmallest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
    Arrays.stream(nums).forEach(num -> {
      heap.add(num);
      if (heap.size() > k) {
        heap.remove();
      }
    });
    return Optional.ofNullable(heap.peek());
  }


  /**
   * Finds the duplicate number in an array of integers.
   *
   * <p>Given an array of integers nums containing n + 1 integers where each integer is in the
   * range [1, n] inclusive. There is only one repeated number in nums, return this repeated
   * number.
   *
   * <p>You must solve the problem without modifying the array nums and use only constant extra
   * space.
   *
   * <p>Example:
   * Input: nums = [1,3,4,2,2] Output: 2
   *
   * <p>Constraints:
   * - 2 <= n <= 3 * 10^4 - nums.length == n + 1 - 1 <= nums[i] <= n - All the integers in nums
   * appear only once except for precisely one integer which appears two or more times. Time
   * Complexity: O(n) Space Complexity: O(1)
   */
  public int findDuplicate(int[] nums) {
    // Find the intersection point of the two runners.
    int tortoise = nums[0];
    int hare = nums[0];
    do {
      tortoise = nums[tortoise];
      hare = nums[nums[hare]];
    } while (tortoise != hare);
    // Find the "entrance" to the cycle.
    tortoise = nums[0];
    while (tortoise != hare) {
      tortoise = nums[tortoise];
      hare = nums[hare];
    }
    return hare;
  }

  /*
  Next Greater Element II
  Given a circular integer array nums
  return the next greater number for every element in nums.
  The next greater number of a number x is the first greater number to its traversing-order next in
  the array, which means you could search circularly to find its next greater number.
  If it doesn't exist, return -1 for this number.
   */
  public int[] nextGreaterElement(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    int[] ans = new int[nums.length];
    Arrays.fill(ans, -1);
    int i = nums.length - 1;
    int total = nums.length * 2;
    while (total > 0) {
      while (!stack.isEmpty() && nums[i] >= stack.peek()) {
        stack.pop();
      }
      if (!stack.isEmpty()) {
        ans[i] = stack.peek();
      }
      stack.push(nums[i]);
      total--;
      i--;
      if (i < 0) {
        i = nums.length - 1;
      }
    }
    return ans;
  }

  /**
   * Given an array of integer heights representing the histogram's bar height where the width of
   * each bar is 1, return the area of the largest rectangle in the histogram O(n) TC and O(n) SC
   */
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int[] aux = new int[n];
    Stack<Integer> s = new Stack<>();
    s.push(-1);
    for (int i = 0; i < heights.length; i++) {
      while (s.peek() != -1 && heights[s.peek()] >= heights[i]) {
        s.pop();
      }
      int peek = s.peek();
      aux[i] = (i - peek - 1);
      s.push(i);
    }
    s.clear();
    s.push(n);
    for (int i = heights.length - 1; i >= 0; i--) {
      while (s.peek() != n && heights[i] <= heights[s.peek()]) {
        s.pop();
      }
      int peek = s.peek();
      aux[i] = (aux[i] + (peek - i)) * heights[i];
      s.push(i);
    }
    return Arrays.stream(aux).max().getAsInt();
  }

  /*
    Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it can trap after raining.
    Approach I: O(n) TC and SC
    Approach II: O(n) TC and O(1) SC
   */
  public int trap(int[] height) {
        /*
        if(height.length == 0) {
            return 0;
        }
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.max(0, Math.min(left_max[i - 1], right_max[i + 1]) - height[i]);
        }
        return ans;
         */

    int left = 0, right = height.length - 1;
    int ans = 0;
    int left_max = 0, right_max = 0;
    while (left < right) {
      if (height[left] < height[right]) {
        if (left_max <= height[left]) {
          left_max = height[left];
        } else {
          ans += left_max - height[left];
        }
        ++left;
      } else {
        if (height[right] >= right_max) {
          right_max = height[right];
        } else {
          ans += right_max - height[right];
        }
        --right;
      }
    }
    return ans;
  }

  public int longestSubstringWithoutRepeatingCharactersWithRecursion(String s) {
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
      max = Math.max(max, helper(s, i, new HashSet<>(), 0));
    }
    return max;
  }

  public boolean isValidPartition(int[] numbers) {
    int n = numbers.length;
    Map<Integer, Boolean> memo = new HashMap<>();
    memo.put(-1, true);
    return prefixIsValid(numbers, n - 1, memo);
  }

  /*
  Given an m x n board of characters and a list of strings words, return all words on the board.
  Each word must be constructed from letters of sequentially adjacent cells,
  where adjacent cells are horizontally or vertically neighboring
   */
  public List<String> findWords(char[][] board, String[] words) {
    return null;
  }

  public boolean isValidSudoku(char[][] board) {
    int N = 9;
    HashSet<Character>[] rows = new HashSet[N];
    HashSet<Character>[] cols = new HashSet[N];
    HashSet<Character>[] boxes = new HashSet[N];
    for (int r = 0; r < N; r++) {
      rows[r] = new HashSet<>();
      cols[r] = new HashSet<>();
      boxes[r] = new HashSet<>();
    }
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        char val = board[r][c];
        // Check if the position is filled with number
        if (val == '.') {
          continue;
        }
        // Check the row
        if (rows[r].contains(val)) {
          return false;
        }
        rows[r].add(val);
        // Check the column
        if (cols[c].contains(val)) {
          return false;
        }
        cols[c].add(val);
        int idx = (r / 3) * 3 + c / 3;
        if (boxes[idx].contains(val)) {
          return false;
        }
        boxes[idx].add(val);
      }
    }
    return true;
  }

  /*
  O(n) TC and O(n) SC
   */
  public List<Integer> sunsetViews(int[] buildings, String direction) {
    List<Integer> buildingsWithSunsetViews = new ArrayList<>();
    int startIdx = 0;
    int step = 1;
    if (direction.equalsIgnoreCase(EAST)) {
      startIdx = buildings.length - 1;
      step = -1;
    }
    int runningMaxHeight = 0;
    while (startIdx >= 0 && startIdx < buildings.length) {
      int currentBuildingHeight = buildings[startIdx];
      if (currentBuildingHeight > runningMaxHeight) {
        buildingsWithSunsetViews.add(startIdx);
      }
      runningMaxHeight = Math.max(runningMaxHeight, currentBuildingHeight);
      startIdx += step;
    }
    if (direction.equalsIgnoreCase(EAST)) {
      Collections.reverse(buildingsWithSunsetViews);
    }
    return buildingsWithSunsetViews;
  }

  public int evaluateReversePolishNotation(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    List<String> ops = Arrays.asList("+", "-", "*", "/");
    Arrays.stream(tokens).forEach(ch -> {
      if (ops.contains(ch)) {
        if (stack.size() < 2) {
          throw new IllegalArgumentException(
              "Invalid input: Not enough operands for operator " + stack);
        }
        Integer op1 = stack.pop();
        Integer op2 = stack.pop();
        switch (ch) {
          case "+" -> stack.push(op2 + op1);
          case "-" -> stack.push(op2 - op1);
          case "*" -> stack.push(op2 * op1);
          case "/" -> stack.push(op2 / op1);
        }
      } else {
        stack.push(Integer.valueOf(ch));
      }
    });
    if (stack.isEmpty()) {
      throw new IllegalArgumentException("Invalid input: stack is empty");
    }
    return stack.peek();
  }

  /*
  -1000 <= asteroids[i] <= 1000 asteroids[i] != 0
   */
  public int[] collidingAsteroids(int[] asteroids) {
    Stack<Integer> st = new Stack<>();
    for (int asteroid : asteroids) {
      boolean flag = true;
      while (!st.isEmpty() && (st.peek() > 0 && asteroid < 0)) {
        // If the top asteroid in the stack is smaller, then it will explode.
        // Hence, pop it from the stack, also continue with the next asteroid in the stack.
        if (Math.abs(st.peek()) < Math.abs(asteroid)) {
          st.pop();
          continue;
        }
        // If both asteroids have the same size, then both asteroids will explode.
        // Pop the asteroid from the stack; also, we won't push the current asteroid to the stack.
        else if (Math.abs(st.peek()) == Math.abs(asteroid)) {
          st.pop();
        }
        // If we reach here, the current asteroid will be destroyed
        // Hence; we should not add it to the stack
        flag = false;
        break;
      }
      if (flag) {
        st.push(asteroid);
      }
    }

    // Add the asteroids from the stack to the vector in the reverse order.
    int[] remainingAsteroids = new int[st.size()];
    for (int i = remainingAsteroids.length - 1; i >= 0; i--) {
      remainingAsteroids[i] = st.pop();
    }
    return remainingAsteroids;
  }

  public boolean isOneEditDistance(String s, String t) {
    int ns = s.length();
    int nt = t.length();
    // Ensure that s is shorter than t.
    if (ns > nt) {
      return isOneEditDistance(t, s);
    }
    // The strings are NOT one edit away distance
    // if the length diff is more than 1.
    if (nt - ns > 1) {
      return false;
    }
    for (int i = 0; i < ns; i++) {
      if (s.charAt(i) != t.charAt(i)) {
        if (ns == nt) {
          return s.substring(i + 1).equals(t.substring(i + 1));
        }
        // if strings have different lengths
        else {
          return s.substring(i).equals(t.substring(i + 1));
        }
      }
    }
    // If there are no diffs on ns distance,
    // the strings are one edit away only if
    // t has one more character.
    return (ns + 1 == nt);

        /*
        if (s.length() == 0) {
            return t.length() == 1;
        }
        if (t.length() == 0) {
            return s.length() == 1;
        }
        int len1 = s.length();
        int len2 = t.length();
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }
        else if (Math.abs(len1 - len2) == 0) {
            return replace(s, t);
        }
        else if (len1 < len2) {
            return insert(s, t);
        }
        // len1 > len2
        return insert(t, s);
         */
  }

  private boolean insert(String s, String t) {
    int count = 0;
    for (int i = 0, j = 0; i < s.length() && j < t.length(); ) {
      if (s.charAt(i) != t.charAt(j)) {
        count++;
      } else {
        i++;
      }
      j++;
    }
    return count <= 1;
  }

  /*
  O(MN) TC and O(MN) SC
   */
  public int minDistance(String word1, String word2) {
    int word1Length = word1.length();
    int word2Length = word2.length();
    int[][] dp = new int[word1Length + 1][word2Length + 1];
    for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
      dp[word1Index][0] = word1Index;
    }
    for (int word2Index = 1; word2Index <= word2Length; word2Index++) {
      dp[0][word2Index] = word2Index;
    }
    for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
      for (int word2Index = 1; word2Index <= word2Length; word2Index++) {
        if (word2.charAt(word2Index - 1) == word1.charAt(word1Index - 1)) {
          dp[word1Index][word2Index] = dp[word1Index - 1][word2Index - 1];
        } else {
          dp[word1Index][word2Index] = Math.min(dp[word1Index - 1][word2Index],
              Math.min(dp[word1Index][word2Index - 1],
                  dp[word1Index - 1][word2Index - 1])) + 1;
        }
      }
    }
    return dp[word1Length][word2Length];
  }

  /*
  return the number of reverse pairs in the array.
  A reverse pair is a pair (i, j) where:
  0 <= i < j < nums.length and nums[i] > 2 * nums[j]
  Time Complexity : O(NlogN), Each recursive call to perform two recursive calls on subslices of size N/2 and
  One linear scans of length <= N. Therefore, the time complexity of the divide & conquer approach can be
  represented by the following recurrence relation: T(N)=2T(N/2)+N
  Space Complexity : O(N), Recursion Stack Space O(logN) + Array(temp) space O(N)
   */
  public int reversePairs(int[] nums) {
    return mergeSort(nums, 0, nums.length - 1);
  }

  public int countInversionsInAnArray(int[] nums) {
    return inversionCount(nums, 0, nums.length - 1);
  }

  /*
  O(log(m + n))
   */
  public double findMedianOfTwoSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    if (m > n) {
      return findMedianOfTwoSortedArrays(nums2, nums1);
    }
    int total = m + n;
    int half = (total + 1) / 2;
    int left = 0;
    int right = m;
    double result = 0.0;
    while (left <= right) {
      int i = left + (right - left) / 2;
      int j = half - i;
      // get the four points around possible median
      int left1 = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
      int right1 = (i < m) ? nums1[i] : Integer.MAX_VALUE;
      int left2 = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
      int right2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;
      // partition is correct
      if (left1 <= right2 && left2 <= right1) {
        // even
        if (total % 2 == 0) {
          result =
              (Math.max(left1, left2) + Math.min(right1, right2)) /
                  2.0;
          // odd
        } else {
          result = Math.max(left1, left2);
        }
        break;
      }
      // partition is wrong (update left/right pointers)
      else if (left1 > right2) {
        right = i - 1;
      } else {
        left = i + 1;
      }
    }
    return result;
  }

  /**
   * array of points where points[i] = [xi, yi] represents a point on the X-Y plane return the k
   * closest points to the origin (0, 0)
   */
  public int[][] kClosestPointsToOrigin(int[][] points, int k) {
    if (k >= points.length) {
      return points;
    }
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
      int distA = a[0] * a[0] + a[1] * a[1];
      int distB = b[0] * b[0] + b[1] * b[1];
      return distB - distA;
    });
    for (int[] point : points) {
      queue.add(point);
      if (queue.size() > k) {
        queue.poll();
      }
    }
    return queue.toArray(new int[0][0]);
  }

  /*
  Course Schedule II
  There are totals n courses you have to take, labeled from 0 to n - 1
  You are given an array prerequisites (m) where prerequisites[i] = [ai, bi] indicates that you must
  take course bi first if you want to take course ai
  TC - O(m + n) && SC - O(m + n)
   */
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] inDegree = new int[numCourses];
    List<List<Integer>> outDegree = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; i++) {
      outDegree.add(new ArrayList<>());
    }
    Arrays.stream(prerequisites).forEach(prerequisite -> {
      outDegree.get(prerequisite[1]).add(prerequisite[0]);
      inDegree[prerequisite[0]]++;
    });
    Queue<Integer> queue = new LinkedList<>();
    // Push all the nodes with in-degree zero in the queue.
    IntStream.range(0, numCourses).filter(i -> inDegree[i] == 0).forEach(queue::offer);
    List<Integer> nodesVisited = new ArrayList<>();
    while (!queue.isEmpty()) {
      int node = queue.poll();
      nodesVisited.add(node);
      // Delete the edge "node -> neighbor".
      outDegree.get(node).forEach(neighbor -> {
        inDegree[neighbor]--;
        if (inDegree[neighbor] == 0) {
          queue.offer(neighbor);
        }
      });
    }
    return (nodesVisited.size() == numCourses) ?
        nodesVisited.stream().mapToInt(Integer::intValue).toArray() :
        new int[0];
  }

  /*
  O(wh) TC and O(wh) SC, w and h are the width and height of the matrix respectively
   */
  public int maximumSumOfAllSubmatrixOfSizeK(int[][] matrix, int k) {
    int[][] sums = Util.createPrefixSumMatrix(matrix);
    int max = Integer.MIN_VALUE;
    for (int row = k - 1; row < matrix.length; row++) {
      for (int col = k - 1; col < matrix[0].length; col++) {
        int total = sums[row][col];
        if (row - k >= 0) {
          total -= sums[row - k][col];
        }
        if (col - k >= 0) {
          total -= sums[row][col - k];
        }
        if (row - k >= 0 && col - k >= 0) {
          total += sums[row - k][col - k];
        }
        max = Math.max(max, total);
      }
    }
    String temp[] = {"a", "b", "c"};
    return max;
  }

  /*
  O(nlogk) TC and O(k) SC
   */
  public int[] sortKSortedArray(int[] array, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int[] ans = new int[array.length];
    int i = 0;
    for (; i < Math.min(k + 1, array.length); i++) {
      minHeap.add(array[i]);
    }
    int itr = 0;
    for (; i < array.length; i++) {
      ans[itr++] = minHeap.poll();
      minHeap.add(array[i]);
    }
    while (!minHeap.isEmpty()) {
      ans[itr++] = minHeap.poll();
    }
    return ans;
  }

  /*
  Approach I - O(n) tc and O(n) sc
  Approach II - O(n) tc and O(1) sc
   */
  public int firstMissingPositive(int[] nums) {
        /*
        int n = matrix.length;
        int[] arr = new int[n + 1];
        for (int t: matrix) {
            if (1 <= t || t <= n) {
                arr[t] = -1;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                return i;
            }
        }
        return n + 1;
         */

    int n = nums.length;
    boolean found = false;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == n) {
        found = true;
      }
      if (nums[i] < 0 || nums[i] >= n) {
        nums[i] = 0;
      }
    }
    for (int i = 0; i < n; i++) {
      int mod = nums[i] % n;
      nums[mod] += n;
    }
    for (int i = 1; i < n; i++) {
      if (nums[i] < n) {
        return i;
      }
    }
    return found ? n + 1 : n;
  }

  /*
  Given the following three values, the task is to find the total number of maximum chocolates you can eat.
  money : Money you have to buy chocolates
  price : Price of a chocolate
  wrap : Number of wrappers to be returned for getting one extra chocolate
  Examples:
  Input :   money = 16, price = 2, wrap = 2
  Output :   15
  Input :   money = 15, price = 1, wrap = 3
  Output :   22
  Input :  money = 20, price = 3, wrap = 5
  Output :   7
  */
  public int numberOfChocolates(int money, int price, int wrap) {
    int choc = money / price;
    return choc + countRec(choc, wrap);
  }

  private int countRec(int choc, int wrap) {
    if (choc < wrap) {
      return 0;
    }
    int newChoc = choc / wrap;
    return newChoc + countRec(newChoc + choc % wrap, wrap);
  }

  /*
  O(n) TC and O(n) SC with stack approach
  O(n) TC and O(1) SC
   */
  public int longestBalancedSubstring(String string) {
        /*
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
         */

    int left = 0, right = 0, maxLength = 0;

    // Forward pass
    for (int i = 0; i < string.length(); i++) {
      if (string.charAt(i) == '(') {
        left++;
      } else {
        right++;
      }

      if (left == right) {
        maxLength = Math.max(maxLength, 2 * right);
      } else if (right > left) {
        left = right = 0;
      }
    }

    // Reset counters for the backward pass
    left = right = 0;
    // Backward pass
    for (int i = string.length() - 1; i >= 0; i--) {
      if (string.charAt(i) == '(') {
        left++;
      } else {
        right++;
      }
      if (left == right) {
        maxLength = Math.max(maxLength, 2 * left);
      } else if (left > right) {
        left = right = 0;
      }
    }
    return maxLength;
  }

  public int candy(int[] ratings) {
    int n = ratings.length;
    int[] candies = new int[n];
    // Initialize each child with one candy.
    Arrays.fill(candies, 1);
    // Traverse from left to right and update candies based on ratings
    for (int i = 1; i < n; i++) {
      if (ratings[i] > ratings[i - 1]) {
        candies[i] = candies[i - 1] + 1;
      }
    }
    // Traverse from right to left and update candies again if needed
    for (int i = n - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1]) {
        candies[i] = Math.max(candies[i], candies[i + 1] + 1);
      }
    }
    // Calculate the total number of candies.
    return Arrays.stream(candies).sum();
  }

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(
        (a, b) -> (Math.abs(b - x) == Math.abs(a - x)) ? (a < b ? 1 : -1)
            : (Math.abs(b - x) - Math.abs(a - x))
    );
    for (int elem : arr) {
      pq.add(elem);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    List<Integer> list = new ArrayList<>(k);
    while (!pq.isEmpty()) {
      list.add(pq.poll());
    }
    Collections.sort(list);
    return list;
  }

  public String customSortString(String order, String s) {
    int[] frequency = new int[26]; // Array to store the frequency of each character
    // Calculate the frequency of each character in string s
    for (char ch : s.toCharArray()) {
      frequency[ch - 'a']++;
    }
    StringBuilder sortedString = new StringBuilder(); // StringBuilder for constructing the sorted string
    // Append characters in the order specified
    for (char ch : order.toCharArray()) {
      while (frequency[ch - 'a'] > 0) {
        sortedString.append(ch);
        frequency[ch - 'a']--;
      }
    }
    // Append remaining characters from s that were not in the order string
    for (char ch : s.toCharArray()) {
      while (frequency[ch - 'a'] > 0) {
        sortedString.append(ch);
        frequency[ch - 'a']--;
      }
    }
    return sortedString.toString();
  }

  public int timeRequiredToBuy(int[] tickets, int k) {
    int ans = 0;
    for (int i = 0; i < tickets.length; i++) {
      if (i <= k) {
        ans += Math.min(tickets[i], tickets[k]);
      } else {
        ans += Math.min(tickets[i], tickets[k] - 1);
      }
    }
    return ans;
  }

  /**
   * combination-sum Given an array of distinct integers candidates and a, return a list of all
   * unique combinations of candidates where the chosen numbers sum to target The same number may be
   * chosen from candidates an unlimited number of times
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    rec(candidates, new ArrayList<>(), target, 0, ans);
    return ans;
  }

  private void rec(int[] candidates, List<Integer> l, int target, int i, List<List<Integer>> ans) {
    if (i == candidates.length) {
      return;
    }
    if (target == 0) {
      ans.add(new ArrayList<>(l));
      return;
    }
    // pick
    boolean pick = candidates[i] <= target;
    if (pick) {
      List<Integer> temp = new ArrayList<>(l);
      temp.add(candidates[i]);
      rec(candidates, temp, target - candidates[i], i, ans);
    }
    // not pick
    rec(candidates, l, target, i + 1, ans);
  }

  public String addTwoBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int i = a.length() - 1, j = b.length() - 1, carry = 0;
    while (i >= 0 || j >= 0) {
      int sum = carry;
      if (j >= 0) {
        sum += b.charAt(j--) - '0';
      }
      if (i >= 0) {
        sum += a.charAt(i--) - '0';
      }
      sb.append(sum % 2);
      carry = sum / 2;
    }
    if (carry == 1) {
      sb.append(carry);
    }
    return sb.reverse().toString();
  }


  /*
    time complexity of O(n) and a space complexity of O(n)
   */
  public int uniquePairs(int[] numbs, int target) {
    Set<Integer> foundPairs = new HashSet<>();
    Set<Integer> seen = new HashSet<>();
    int count = 0;
    for (int num : numbs) {
      int complement = target - num;
      if (seen.contains(complement) && !foundPairs.contains(num)) {
        foundPairs.add(num);
        foundPairs.add(complement);
        count++;
      }
      seen.add(num);
    }
    return count;
  }

  /*
  Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
  and nums[i] + nums[j] + nums[k] == 0
  solution must not contain duplicate triplets
   */
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    Set<List<Integer>> set = new HashSet<>();
    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          set.add(List.of(nums[i], nums[j], nums[k]));
          j++;
          k--;
        } else if (sum < 0) {
          j++;
        } else {
          k--;
        }
      }
    }
    return new ArrayList<>(set);
  }

  public List<List<Integer>> threeSumWithoutSet(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();

    // Sort the array
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      // Skip duplicate elements for i
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int j = i + 1;
      int k = nums.length - 1;

      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];

        if (sum == 0) {
          // Found a triplet with zero sum
          ans.add(Arrays.asList(nums[i], nums[j], nums[k]));

          // Skip duplicate elements for j
          while (j < k && nums[j] == nums[j + 1]) {
            j++;
          }

          // Skip duplicate elements for k
          while (j < k && nums[k] == nums[k - 1]) {
            k--;
          }

          // Move the pointers
          j++;
          k--;
        } else if (sum < 0) {
          // Sum is less than zero, increment j to increase the sum
          j++;
        } else {
          // Sum is greater than zero, decrement k to decrease the sum
          k--;
        }
      }
    }
    return ans;
  }

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int currentDiff = Integer.MAX_VALUE;
    int ans = 0;
    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (Math.abs(sum - target) < currentDiff) {
          currentDiff = Math.abs(sum - target);
          ans = sum;
        }
        if (sum == target) {
          return sum;
        } else if (sum < target) {
          j++;
        } else {
          k--;
        }
      }
    }
    return ans;
  }

  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int count = 0;
    for (int i = 0; i < nums.length - 2; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum < target) {
          count += (k - j);
          j++;
        } else {
          k--;
        }
      }
    }
    return count;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    Set<List<Integer>> set = new HashSet<>();
    for (int i = 0; i < nums.length - 3; i++) {
      for (int j = i + 1; j < nums.length - 2; j++) {
        int k = j + 1;
        int l = nums.length - 1;
        while (k < l) {
          long a = nums[i];
          long b = nums[j];
          long c = nums[k];
          long d = nums[l];
          long sum = a + b + c + d;
          if (sum == target) {
            set.add(List.of(nums[i], nums[j], nums[k], nums[l]));
            k++;
            l--;
          } else if (sum < target) {
            k++;
          } else {
            l--;
          }
        }
      }
    }
    return new ArrayList<>(set);
  }

  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    int cnt = 0;
    Map<Integer, Integer> m = new HashMap<>();
    for (int a : nums1) {
      for (int b : nums2) {
        m.put(a + b, m.getOrDefault(a + b, 0) + 1);
      }
    }
    for (int c : nums3) {
      for (int d : nums4) {
        cnt += m.getOrDefault(-(c + d), 0);
      }
    }
    return cnt;
  }

  /*
    You are given an array points where points[i] = [xi, yi].
    The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|
    Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
    TC -
    SC - O(n^2), dominated by the space required for the priority queue.
   */
  public int minCostConnectPoints(int[][] points) {
    int n = points.length;
    PriorityQueue<Edge> minHeap = new PriorityQueue<>();
    minHeap.add(new Edge(0, 0));
    boolean[] visited = new boolean[n];
    int minCost = 0;
    while (!minHeap.isEmpty()) {
      Edge currentEdge = minHeap.poll();
      int currentPointIndex = currentEdge.pointIndex;

      if (visited[currentPointIndex]) {
        continue;
      }
      visited[currentPointIndex] = true;
      minCost += currentEdge.weight;

      for (int i = 0; i < n; i++) {
        if (!visited[i]) {
          int distance = calculateManhattanDistance(points[currentPointIndex], points[i]);
          minHeap.add(new Edge(i, distance));
        }
      }
    }
    return minCost;
  }

  // TC - O(n) and SC - O(n)
  public List<List<Integer>> groupThePeople(int[] groupSizes) {
    Map<Integer, List<Integer>> temp = new HashMap<>();
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < groupSizes.length; i++) {
      int size = groupSizes[i];
      List<Integer> group = temp.computeIfAbsent(size, k -> new ArrayList<>());
      group.add(i);
      if (group.size() == size) {
        result.add(group);
        temp.put(size, new ArrayList<>());
      }
    }
    return result;
  }

  /**
   * Max Consecutive Ones III
   */
  public int findMaxConsecutiveOnesIII(int[] nums, int k) {
    int left = 0;
    int right = 0;
    int maxCount = 0;
    int count0 = 0;
    while (right < nums.length) {
      if (nums[right] == 0) {
        count0++;
      }
      while (count0 > k) {
        if (nums[left] == 0) {
          count0--;
        }
        left++;
      }
      maxCount = Math.max(maxCount, right - left + 1);
      right++;
    }
    return maxCount;
  }

  public String frequencySort(String s) {
    Map<Character, Integer> hm = new HashMap<>();
    s.chars().forEach(c -> hm.merge((char) c, 1, Integer::sum));
    PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
        (a, b) -> b.getValue() - a.getValue());
    pq.addAll(hm.entrySet());
    StringBuilder result = new StringBuilder();
    while (!pq.isEmpty()) {
      Map.Entry<Character, Integer> entry = pq.poll();
      result.append(String.valueOf(entry.getKey()).repeat(entry.getValue()));
    }
    return result.toString();
  }


  /**
   * Nested List Weight Sum I
   */
  public int depthSum(List<NestedInteger> nestedList) {
    return dfs(nestedList, 1);
  }

  private int dfs(List<NestedInteger> nestedList, int depth) {
    int depthSum = 0;
    for (NestedInteger item : nestedList) {
      if (item.isInteger()) {
        depthSum += item.getInteger() * depth;
      } else {
        depthSum += dfs(item.getList(), depth + 1);
      }
    }
    return depthSum;
  }


  /**
   * Nested List Weight Sum II
   */
  public int depthSumInverse(List<NestedInteger> nestedList) {
    int depth = maxDepth(nestedList);
    return dfsInverse(nestedList, depth);
  }

  private int dfsInverse(List<NestedInteger> nestedList, int depth) {
    int depthSum = 0;
    for (NestedInteger item : nestedList) {
      if (item.isInteger()) {
        depthSum += item.getInteger() * depth;
      } else {
        depthSum += dfsInverse(item.getList(), depth - 1);
      }
    }
    return depthSum;
  }

  public int depthSumInverseOptimal(List<NestedInteger> nestedList) {
    int unweighted = 0, weighted = 0;
    Queue<NestedInteger> queue = new LinkedList<>(nestedList);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      int levelSum = 0;
      for (int i = 0; i < levelSize; i++) {
        NestedInteger ni = queue.poll();
        if (ni.isInteger()) {
          levelSum += ni.getInteger();
        } else {
          queue.addAll(ni.getList());
        }
      }
      unweighted += levelSum;
      weighted += unweighted;
    }
    return weighted;
  }

  private int maxDepth(List<NestedInteger> nestedList) {
    int depth = 1;
    for (NestedInteger item : nestedList) {
      if (!item.isInteger()) {
        depth = Math.max(depth, 1 + maxDepth(item.getList()));
      }
    }
    return depth;
  }

  /**
   * Generate Parentheses Given n pairs of parentheses, write a function to generate all
   * combinations of well-formed parentheses
   */
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    recurse(res, 0, 0, "", n);
    return res;
  }

  private void recurse(List<String> res, int left, int right, String s, int n) {
    if (s.length() == n * 2) {
      res.add(s);
      return;
    }
    if (left < n) {
      recurse(res, left + 1, right, s + "(", n);
    }
    if (left > right) {
      recurse(res, left, right + 1, s + ")", n);
    }
  }

  /**
   * Calculates the number of car fleets that will arrive at the destination.
   *
   * @param target   The destination point.
   * @param position An array representing the positions of the cars.
   * @param speed    An array representing the speeds of the cars.
   * @return The number of car fleets that will arrive at the destination.
   */
  public int carFleet(int target, int[] position, int[] speed) {
    int n = position.length;
    int[][] arr = new int[n][2];
    for (int i = 0; i < n; i++) {
      arr[i][0] = position[i];
      arr[i][1] = speed[i];
    }
    Arrays.sort(arr, comparingInt(a -> a[0]));
    Stack<int[]> st = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      int[] curr = arr[i];
      if (st.isEmpty()) {
        st.push(new int[]{curr[0], curr[1]});
      } else {
        int[] top = st.peek();
        double topTime = (target - top[0]) / (double) top[1];
        double currTime = (target - curr[0]) / (double) curr[1];
        if (currTime > topTime) {
          st.push(new int[]{curr[0], curr[1]});
        }
      }
    }
    return st.size();
  }


  /**
   * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false
   * otherwise.
   * <p>
   * In other words, return true if one of s1's permutations is the substring of s2.
   * <p>
   * Example 1: Input: s1 = "ab", s2 = "eidbaooo" Output: true Explanation: s2 contains one
   * permutation of s1 ("ba").
   * <p>
   * Example 2: Input: s1 = "ab", s2 = "eidboaoo" Output: false
   * <p>
   * Constraints: - 1 <= s1.length, s2.length <= 10^4 - s1 and s2 consist of lowercase English
   * letters.
   **/
  public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) {
      return false;
    }
    int[] s1map = new int[26];
    int[] s2map = new int[26];
    for (int i = 0; i < s1.length(); i++) {
      s1map[s1.charAt(i) - 'a']++;
      s2map[s2.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s2.length() - s1.length(); i++) {
      if (matches(s1map, s2map)) {
        return true;
      }
      s2map[s2.charAt(i + s1.length()) - 'a']++;
      s2map[s2.charAt(i) - 'a']--;
    }
    return matches(s1map, s2map);
  }

  private boolean matches(int[] s1map, int[] s2map) {
    for (int i = 0; i < 26; i++) {
      if (s1map[i] != s2map[i]) {
        return false;
      }
    }
    return true;
  }

  static class Elem {

    char task;
    int n;
    int next;

    public Elem(char task, int n, int next) {
      this.task = task;
      this.n = n;
      this.next = next;
    }
  }

  /**
   * Calculates the minimum number of intervals required to complete all tasks with the given
   * cooling time.
   *
   * @param tasks    Array of CPU tasks represented by letters A to Z
   * @param coolTime Cooling time between identical tasks
   * @return Minimum number of intervals required to complete all tasks
   */
  public int leastInterval(char[] tasks, int coolTime) {
    if (coolTime == 0) {
      return tasks.length;
    }
    int time = 0;
    PriorityQueue<Elem> pq = new PriorityQueue<>((p, q) -> q.n - p.n);
    List<Elem> pqq = new ArrayList<>();
    Map<Character, Integer> m = new HashMap<>();
    for (char c : tasks) {
      m.put(c, m.getOrDefault(c, 0) + 1);
    }
    for (var entry : m.entrySet()) {
      pq.add(new Elem(entry.getKey(), entry.getValue(), 0));
    }
    while (!pq.isEmpty()) {
      while (!pq.isEmpty() && pq.peek().next > time) {
        Elem e = pq.poll();
        pqq.add(e);
      }
      if (!pq.isEmpty()) {
        Elem e = pq.poll();
        if (e.n > 1) {
          pq.add(new Elem(e.task, e.n - 1, time + coolTime + 1));
        }
      }
      while (!pqq.isEmpty()) {
        pq.add(pqq.remove(0));
      }
      time++;
    }
    return time;
  }
}
