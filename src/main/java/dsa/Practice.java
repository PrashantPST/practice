package dsa;

import dsa.models.BinaryTree;
import dsa.models.Interval;
import dsa.models.ListNode;
import dsa.models.NodeColor;

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

import static dsa.Util.EAST;
import static dsa.Util.doesIntervalsOverlap;
import static dsa.Util.helper;
import static dsa.Util.inversionCount;
import static dsa.Util.mergeSort;
import static dsa.Util.prefixIsValid;
import static dsa.Util.swap;
import static java.util.Arrays.fill;
import static java.util.Comparator.comparingInt;

public class Practice {

    private final int[][] coordinates = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static int minNumberOfCoinsForChange(int amount, int[] coins) {
        int[] count = new int[amount + 1];
        fill(count, Integer.MAX_VALUE);
        count[0] = 0;
        for (int coin : coins) {
            for (int amt = 1; amt <= amount; amt++) {
                if (coin <= amt) {
                    if (count[amt - coin] != Integer.MAX_VALUE) {
                        count[amt] = Math.min(count[amt], count[amt - coin] + 1);
                    }
                }
            }
        }
        return count[amount] == Integer.MAX_VALUE ? -1 : count[amount];
    }

    public static int numberOfWaysToMakeChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int amt = 1; amt <= amount; amt++) {
                if (amt >= coin) {
                    dp[amt] += dp[amt - coin];
                }
            }
        }
        return dp[amount];
    }

    public static List<List<Integer>> getPermutations(List<Integer> array) {
        List<List<Integer>> ans = new ArrayList<>();
        permute(array, 0, ans);
        return ans;
    }

    public static void permute(List<Integer> array, int i, List<List<Integer>> ans) {
        if (i == array.size() - 1) {
            ans.add(new ArrayList<>(array));
        } else {
            for (int j = i; j < array.size(); j++) {
                swap(array, i, j);
                permute(array, i + 1, ans);
                swap(array, i, j);
            }
        }
    }

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
    O(n^2) TC and O(1) SC
     */
    public static String longestPalindromicSubstring(String str) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            String temp = String.valueOf(str.charAt(i));
            while (left >= 0 && right < str.length()) {
                if (str.charAt(left) == str.charAt(right)) {
                    temp = str.substring(left, right + 1);
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if (temp.length() > ans.length()) {
                ans = temp;
            }
            left = i - 1;
            right = i;
            while (left >= 0 && right < str.length()) {
                if (str.charAt(left) == str.charAt(right)) {
                    temp = str.substring(left, right + 1);
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if (temp.length() > ans.length()) {
                ans = temp;
            }
        }
        return ans;
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
    Jump Game I
    You are initially positioned at the array's first index, and each element in the array represents
    your maximum jump length at that position
    Return true if you can reach the last index, or false otherwise.
     */
    public static boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > reachable) {
                return false;
            }
            reachable = Math.max(reachable, i + nums[i]);
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
        // destination is last index
        int destination = size - 1;
        int curCoverage = 0, lastJumpIdx = 0;
        // counter of jump
        int timesOfJump = 0;
        // Quick response if start index == destination index == 0
        if (size == 1) {
            return 0;
        }
        // Greedy strategy: extend coverage as long as possible with lazy jump
        for (int i = 0; i < size; i++) {
            if (i == destination) {
                return timesOfJump;
            }
            // extend coverage
            curCoverage = Math.max(curCoverage, i + nums[i]);
            // forced to jump (by lazy jump) to extend coverage
            if (i == lastJumpIdx) {
                lastJumpIdx = curCoverage;
                timesOfJump++;
            }
        }
        return timesOfJump;
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
        return Util.buildSubsequnce(dp, text1);
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

    public static ArrayList<Integer> findOrder(ArrayList<Integer> height, ArrayList<Integer> infront) {
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

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String a = "a";
        List<String> dummy_input = new ArrayList<>();
        dummy_input.add(a);
        for (String s : dummy_input) {
            // System.out.println("rere");
            sb.append(s);
            sb.append("<->");
        }
        System.out.println(sb);
        String temp = sb.toString();
        System.out.println(temp);

        List<String> ans = new ArrayList<>();
        for (String t : temp.split(a)) {
            ans.add(t);
        }
        System.out.println(ans);
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

    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        boolean possible = false;
        int j = n - 1;
        while (j >= 0) {
            if (nums[j] >= nums[j + 1]) {
                j--;
            } else {
                possible = true;
                int i = n;
                for (; i > j; i--) {
                    if (nums[i] > nums[j]) {
                        break;
                    }
                }
                nums[i] = nums[j] + nums[i] - (nums[j] = nums[i]);
                int start = j + 1;
                int end = n;
                while (start < end) {
                    nums[start] = nums[end] + nums[start] - (nums[end] = nums[start]);
                    start++;
                    end--;
                }
                break;
            }
        }
        if (!possible) {
            Arrays.sort(nums);
        }
    }

    public boolean cycleInGraph(int[][] edges) {
        int nVertices = edges.length;
        NodeColor[] arr = new NodeColor[nVertices];
        fill(arr, NodeColor.WHITE);
        for (int i = 0; i < nVertices; i++) {
            if (dfs(edges, i, arr)) {
                return true;
            }
        }
        return false;
    }

    public boolean dfs(int[][] edges, int i, NodeColor[] arr) {
        arr[i] = NodeColor.GREY;
        for (int v : edges[i]) {
            if (arr[v] == NodeColor.GREY) {
                return true;
            }
            if (dfs(edges, v, arr)) {
                return true;
            }
        }
        arr[i] = NodeColor.BLACK;
        return false;
    }

    public Interval[] mergeOverlappingIntervals(Interval[] intervals) {
        Arrays.sort(intervals, comparingInt(Interval::getStart));
        List<Interval> answer = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            Interval currentInterval = Interval.builder().start(intervals[i].getStart()).
                    end(intervals[i].getEnd()).build();
            // Merge until the list gets exhausted or no overlap is found.
            while (i < intervals.length && Util.doesIntervalsOverlap(currentInterval, intervals[i])) {
                currentInterval = Util.mergeIntervals(currentInterval, intervals[i]);
                i++;
            }
            // Decrement to ensure we don't skip the interval due to outer for-loop incrementing.
            i--;
            answer.add(currentInterval);
        }

        return answer.toArray(new Interval[0]);
    }

    public Interval[] insert(Interval[] intervals, Interval newInterval) {
        intervals = insertAnInterval(intervals, newInterval);
        return mergeOverlappingIntervals(intervals);
    }

    Interval[] insertAnInterval(Interval[] intervals, Interval newInterval) {
        boolean intervalInserted = false;
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (newInterval.getStart() < intervals[i].getStart()) {
                // Found the position, insert the interval and break from the loop.
                list.add(i, newInterval);
                intervalInserted = true;
                break;
            }
        }

        // If there is no interval ith a greater value of start value,
        // then the interval must be inserted at the end of the list.
        if (!intervalInserted) {
            list.add(newInterval);
        }
        return list.toArray(new Interval[0]);
    }

    public int eraseOverlapIntervals(Interval[] intervals) {
        int ans = 0;
        Arrays.sort(intervals, comparingInt(Interval::getEnd));
        Interval currentInterval = intervals[0];
        for (Interval interval : intervals) {
            if (!doesIntervalsOverlap(currentInterval, interval)) {
                currentInterval = interval;
                ans++;
            }
        }
        return intervals.length - ans - 1;
    }

    /*
    words[i] consists of lowercase English letters.
     */
    public List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> ans = new HashMap<>();
        Arrays.stream(words).forEach(str -> {
            int[] hash = new int[26];
            for (char c : str.toCharArray()) {
                hash[c - 'a']++;
            }
            String key = Arrays.toString(hash);
            ans.putIfAbsent(key, new ArrayList<>());
            ans.get(key).add(str);
        });
        return new ArrayList<>(ans.values());
    }

    /*
    Unique Paths I
    A robot is initially located at grid[0][0]
    The robot tries to move to the grid[m - 1][n - 1]
    The robot can only move either down or right at any point in time
    return the number of possible unique paths
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

    public void spiralOrder(BinaryTree root) {
        if (root == null) {
            return;
        }
        Stack<BinaryTree> s1 = new Stack<>();
        Stack<BinaryTree> s2 = new Stack<>();
        s1.push(root);
        while (!s1.empty() || !s2.empty()) {
            while (!s1.empty()) {
                BinaryTree temp = s1.pop();
                System.out.print(temp.value + " ");
                if (temp.right != null)
                    s2.push(temp.right);
                if (temp.left != null)
                    s2.push(temp.left);
            }
            while (!s2.empty()) {
                BinaryTree temp = s2.pop();
                System.out.print(temp.value + " ");
                if (temp.left != null)
                    s1.push(temp.left);
                if (temp.right != null)
                    s1.push(temp.right);
            }
        }
    }

    public int numberOfIslands(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        for (int[] coordinate : coordinates) {
            dfs(grid, i + coordinate[0], j + coordinate[1]);
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxArea = Math.max(maxArea, maxAreaOfIsland(grid, i, j));
            }
        }
        return maxArea;
    }

    public int maxAreaOfIsland(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 ||
                r == grid.length ||
                c == grid[0].length ||
                grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        return (1 + maxAreaOfIsland(grid, r + 1, c) +
                maxAreaOfIsland(grid, r - 1, c) +
                maxAreaOfIsland(grid, r, c + 1) +
                maxAreaOfIsland(grid, r, c - 1));
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

    /*
    reverse the nodes of the list k at a time, 1 <= k<= length of the linked list
    If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        int k2 = k;
        ListNode current = head;
        while (current != null && k-- > 0) {
            ListNode nodeNext = current.getNext();
            current.setNext(prev);
            prev = current;
            current = nodeNext;
        }
        if (k == 0) {
            head.setNext(reverseKGroup(current, k2));
        } else if (k > 0) {
            ListNode previousNode = null;
            ListNode currentNode = prev;
            while (currentNode != null) {
                ListNode nextNode = currentNode.getNext();
                currentNode.setNext(previousNode);
                previousNode = currentNode;
                currentNode = nextNode;
            }
            return previousNode;
        }
        return prev;
    }

    /*
    Pointer to head node is not given - O(1) SC and O(1) TC
     */
    public void deleteNode(ListNode node) {
        ListNode nextNode = node.getNext();
        node.setValue(nextNode.getValue());
        node.setNext(nextNode.getNext());
        nextNode.setNext(null);
    }

    /*
    You are given the head of a singly linked-list. The list can be represented as
    L0 → L1 → … → Ln - 1 → Ln
    Reorder the list to be on the following form:
    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     */
    public void reorderList(ListNode head) {
        // find the middle of linked list
        ListNode slow = head, fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        // reverse the second part of the list in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while (second.getNext() != null) {
            tmp = first.getNext();
            first.setNext(second);
            first = tmp;

            tmp = second.getNext();
            second.setNext(first);
            second = tmp;
        }
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

    /*
    TC - O(NlogK) SC - O(K)
     */
    public int[] topKFrequentNumbers(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));
        PriorityQueue<Integer> pq = new PriorityQueue<>(comparingInt(map::get));
        int[] ans = new int[k];
        map.keySet().forEach(num -> {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        });
        int i = 0;
        while (!pq.isEmpty()) {
            ans[i++] = pq.poll();
        }
        return ans;
    }

    public List<String> topKFrequentWords(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> h = new PriorityQueue<>(
                (w1, w2) -> cnt.get(w1).equals(cnt.get(w2)) ? w2.compareTo(w1) : cnt.get(w1) - cnt.get(w2));

        for (String word : cnt.keySet()) {
            h.offer(word);
            if (h.size() > k) {
                h.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (!h.isEmpty())
            res.add(h.poll());
        Collections.reverse(res);
        return res;
    }

    /*
    return the kth largest element in the array
    Time complexity: O(nlogk)
    Space complexity: O(k)
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

    public int staircaseTraversal(int height, int maxSteps) {
        int[] ans = new int[height + 1];
        ans[0] = 1;
        for (int h = 1; h <= height; h++) {
            for (int j = h - 1; j >= h - maxSteps && j >= 0; j--) {
                ans[h] += ans[j];
            }
        }
        return ans[height];
    }

    /*
    Time Complexity: O(n)
    Space Complexity: O(1)
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

    /*
    Given an array of integer heights representing the histogram's bar height where the width of each bar is 1,
    return the area of the largest rectangle in the histogram
    O(n) TC and O(n) SC
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 1) {
            return heights[0];
        }
        int ans = 0;
        int[] sml = new int[n];
        int[] smr = new int[n];
        Arrays.fill(sml, -1);
        Arrays.fill(smr, n);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        IntStream.range(0, n).forEach(i -> {
            while (st.peek() != -1 && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            if (st.peek() == -1) {
                sml[i] = -1;
            } else {
                sml[i] = st.peek();
            }
            st.push(i);
        });
        st.clear();
        st.push(n);
        for (int j = n - 1; j >= 0; j--) {
            while (st.peek() != n && heights[st.peek()] >= heights[j]) {
                st.pop();
            }
            if (st.peek() == n) {
                smr[j] = n;
            } else {
                smr[j] = st.peek();
            }
            st.push(j);
        }
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (smr[i] - sml[i] - 1));
        }
        return ans;
    }

    /*
    Find two lines that together with the x-axis form a container
    such that the container contains the most water.
    Return the maximum amount of water a container can store.
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            int containerLength = right - left;
            int area = containerLength * Math.min(height[left], height[right]);
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    /*
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
            ans += Math.min(left_max[i], right_max[i]) - height[i];
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

    /*
    Given a string s, find the length of the longest substring without repeating characters
    O(n) TC O(n) SC
     */
    public String longestSubstringWithoutRepeatingCharacters(String str) {
        int longest = 0;
        String ans = "";
        int current;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i)) && map.get(str.charAt(i)) >= start) {
                start = map.get(str.charAt(i)) + 1;
            }
            current = i - start + 1;
            map.put(str.charAt(i), i);
            if (current > longest) {
                ans = str.substring(start, i + 1);
                longest = current;
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
    Given an m x n grid of character board and a string word, return true if a word exists in the grid,
    The word can be constructed from letters of sequentially adjacent cells,
    where adjacent cells are horizontally or vertically neighboring
     */
    public boolean exist(char[][] board, String word) {
        return false;
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

    public int[][] updateMatrix(int[][] mat) {
        return null;
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
                j++;
            } else {
                i++;
                j++;
            }
        }
        return count <= 1;
    }

    private boolean replace(String s, String t) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                count++;
            }
        }
        return count == 1;
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

    void printkdistanceNodeDown(BinaryTree node, int k) {
        // Base Case
        if (node == null || k < 0)
            return;

        // If we reach a k distant node, print it
        if (k == 0) {
            System.out.print(node.value);
            return;
        }
        // Recur for left and right subtrees
        printkdistanceNodeDown(node.left, k - 1);
        printkdistanceNodeDown(node.right, k - 1);
    }

    /*
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
    return the k closest points to the origin (0, 0).
    The distance between two points on the X-Y plane is the Euclidean distance
     */
    public int[][] kClosestPointsToOrigin(int[][] points, int k) {
        if (k == points.length) {
            return points;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) ->
                (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.toArray(new int[0][0]);
    }

    /*
    Course Schedule II
    There are totals of numCourses (n) courses you have to take, labeled from 0 to numCourses - 1
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
    Meeting Rooms II
    Given an array of meeting time intervals where intervals[i] = [start, end],
    return the minimum number of conference rooms required
    we can keep all the rooms in a min heap where the key for the min heap would be the end time of meeting
    TC - O(NlogN)
    SC - O(N)
     */
    public int minimumMeetingRooms(Interval[] intervals) {
        // Sort the given meetings by their start time.
        Arrays.sort(intervals, comparingInt(Interval::getStart));
        // Min heap
        PriorityQueue<Integer> allocatedRooms = new PriorityQueue<>();
        for (Interval interval : intervals) {
            if (!allocatedRooms.isEmpty()) {
                if (allocatedRooms.peek() <= interval.getStart()) {
                    allocatedRooms.poll();
                }
            }
            allocatedRooms.add(interval.getEnd());
        }
        return allocatedRooms.size();
    }

    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        // Write your code here.
        times.sort(comparingInt(a -> a.get(0)));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ArrayList<Integer> time : times) {
            if (!pq.isEmpty()) {
                if (pq.peek() <= time.get(0)) {
                    pq.poll();
                }
            }
            pq.add(time.get(1));
        }
        return pq.size();
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
    Best Time to Buy and Sell Stock I
    maximize your profit by choosing a single day to buy one stock and choosing a
    different day in the future to sell that stock
     */
    public int maxProfitI(int[] prices) {
        int profit = 0, minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            profit = Math.max(profit, price - minPrice);
        }
        return profit;
    }

    /*
    Best Time to Buy and Sell Stock II
    as many transactions
     */
    public int maxProfitII(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }

    /*
    Find the maximum profit you can achieve. You may complete at most two transactions
     */
    public int maxProfitIII(int[] prices) {
        int profit = 0;
        int n = prices.length;
        int[] l = new int[n];
        int[] r = new int[n + 1];
        int min = prices[0];
        int max = prices[n - 1];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[n - i - 1]);
            l[i] = Math.max(l[i - 1], prices[i] - min);
            r[n - i - 1] = Math.max(r[i + 1], max - prices[n - i - 1]);
        }
        for (int i = 0; i < n; i++) {
            profit = Math.max(profit, l[i] + r[i + 1]);
        }
        return profit;
    }

    /*
    Find the maximum profit you can achieve
    you may buy at most k times and sell at most k times
     */
    public int maxProfitIV(int[] prices, int k) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[k + 1][n];
        for (int r = 1; r <= k; r++) {
            int local = dp[r - 1][0] - prices[0];
            for (int c = 1; c < n; c++) {
                local = Math.max(local, dp[r - 1][c - 1] - prices[c - 1]);
                dp[r][c] = Math.max(dp[r][c - 1], prices[c] + local);
            }
        }
        return dp[k][n - 1];
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


    /*
    O(n) TC and O(1) SC
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
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

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> (Math.abs(b - x) == Math.abs(a - x)) ? (a < b ? 1 : -1) : (Math.abs(b - x) - Math.abs(a - x))
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

    public int uniquePairs(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Set<Integer> foundPairs = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        int count = 0;
        for (int num : nums) {
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
    BF approach - O(nk) O(1)
    max heap - O(nlogk) O(k)
    deque - O(n) O(k)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
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

    public int threeSumClosest(int[] nums, int target) {
        return 0;
    }
}
