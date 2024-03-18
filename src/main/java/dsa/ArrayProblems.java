package dsa;

import dsa.models.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ArrayProblems {

  private static void transpose(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = i + 1; j < matrix[0].length; j++) {
        matrix[i][j] = matrix[j][i] + matrix[i][j] - (matrix[j][i] = matrix[i][j]);
      }
    }
  }

  /*
  n: total number of array elements
  k: number of arrays
  TC: O(nlogk + k) SC: O(n + k)
   */
  public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
    List<Integer> ans = new ArrayList<>();
    PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i.element)
    );
    // O(k)
    for (int i = 0; i < arrays.size(); i++) {
      pq.add(new Item(arrays.get(i).get(0), i, 1));
    }
    // O(nlogk)
    while (!pq.isEmpty()) {
      Item top = pq.poll();
      ans.add(top.element);
      if (arrays.get(top.from).size() > top.idx) {
        pq.add(new Item(arrays.get(top.from).get(top.idx), top.from, top.idx + 1
        ));
      }
    }
    return ans;
  }

  public static int maxSubArraySum(int[] numbers) {
    int max = Integer.MIN_VALUE;
    int local = 0;
    for (int a : numbers) {
      local += a;
      max = Math.max(max, local);
      local = Math.max(0, local);
    }
    return max;
  }

  public static int[] maxSubArraySumReturnArray(int[] numbers) {
    int max = Integer.MIN_VALUE, runningSum = 0, start = 0, end = 0, probableStart = 0;
    for (int i = 0; i < numbers.length; i++) {
      runningSum += numbers[i];
      if (max < runningSum) {
        max = runningSum;
        start = probableStart;
        end = i;
      }
      if (runningSum < 0) {
        runningSum = 0;
        probableStart = i + 1;
      }
    }
    return Arrays.copyOfRange(numbers, start, end + 1);
  }

  /*
  Sort an array of 0s, 1s and 2s | Sort Three Colors | Dutch National Flag problem
   */
  public static int[] threeNumberSort(int[] nums, int[] order) {
    int lastZero = 0;
    int firstTwo = nums.length - 1;
    for (int i = 0; i <= firstTwo; ) {
      if (nums[i] == order[0]) {
        nums[i] = nums[lastZero] + nums[i] - (nums[lastZero] = nums[i]);
        i++;
        lastZero++;
      } else if (nums[i] == order[1]) {
        i++;
      } else {
        nums[i] = nums[firstTwo] + nums[i] - (nums[firstTwo] = nums[i]);
        firstTwo--;
      }
    }
    return nums;
  }

  public List<Integer> spiralOrderOfAMatrix(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    int rows = matrix.length;
    int columns = matrix[0].length;
    int up = 0;
    int left = 0;
    int right = columns - 1;
    int down = rows - 1;
    while (result.size() < rows * columns) {
      // Traverse from left to right
      for (int col = left; col <= right; col++) {
        result.add(matrix[up][col]);
      }
      // Traverse downwards
      for (int row = up + 1; row <= down; row++) {
        result.add(matrix[row][right]);
      }
      // Make sure we are now on a different row
      if (up != down) {
        // Traverse from right to left
        for (int col = right - 1; col >= left; col--) {
          result.add(matrix[down][col]);
        }
      }
      // Make sure we are now on a different column
      if (left != right) {
        // Traverse upwards
        for (int row = down - 1; row > up; row--) {
          result.add(matrix[row][left]);
        }
      }
      left++;
      right--;
      up++;
      down--;
    }
    return result;
  }

  public void rotateBy90DegreesClockwise(int[][] matrix) {
    transpose(matrix);
    int col1 = 0;
    int col2 = matrix[0].length - 1;
    while (col1 < col2) {
      for (int row = 0; row < matrix.length; row++) {
        matrix[row][col1] =
            matrix[row][col2] + matrix[row][col1] - (matrix[row][col2] = matrix[row][col1]);
      }
      col1++;
      col2--;
    }
  }

  /**
   * A sub array Sum Equals k, O(n) TC and O(n) SC
   */
  public int numberOfSubArrayHavingSumK(int[] nums, int k) {
    int count = 0, sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int num : nums) {
      sum += num;
      if (map.containsKey(sum - k)) {
        count += map.get(sum - k);
      }
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }

  /**
   * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a
   * sum goal. A subarray is a contiguous part of the array.
   * <p>
   * Example 1: Input: nums = [1,0,1,0,1], goal = 2 Output: 4 Explanation: The 4 subarrays are
   * bolded and underlined below: [1,0,1,0,1] [1,0,1,0,1] [1,0,1,0,1] [1,0,1,0,1]
   * <p>
   * Example 2: Input: nums = [0,0,0,0,0], goal = 0 Output: 15
   * <p>
   * Constraints: - 1 <= nums.length <= 3 * 104 - nums[i] is either 0 or 1. - 0 <= goal <=
   * nums.length
   */
  public int numberOfSubArrayHavingSumKInABinaryArray(int[] nums, int k) {
    return calc(nums, k) - calc(nums, k - 1);
  }

  public int calc(int[] nums, int goal) {
    if (goal < 0) {
      return 0;
    }
    int l = 0;
    int r = 0;
    int count = 0;
    int currSum = 0;
    while (r < nums.length) {
      currSum += nums[r];
      while (currSum > goal) {
        currSum -= nums[l];
        l++;
      }
      if (currSum == goal) {
        count += r - l + 1;
      }
      r++;
    }
    return count;
  }

  public int maxSubArrayProduct(int[] numbers) {
    int prefix = 1;
    int suffix = 1;
    int ans = Integer.MIN_VALUE;
    int n = numbers.length;
    for (int i = 0; i < numbers.length; i++) {
      prefix *= numbers[i];
      suffix *= numbers[n - i - 1];
      ans = Util.max(prefix, suffix, ans);
      if (prefix == 0) {
        prefix = 1;
      }
      if (suffix == 0) {
        suffix = 1;
      }
    }
    return ans;
  }

  /*
  nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
  Squaring each element and sorting the new array is very trivial
  could you find an O(n) solution using a different approach?
   */
  public int[] sortedSquares(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int i = 0;
    int j = n - 1;
    for (int k = n - 1; k >= 0; k--) {
      if (Math.abs(nums[i]) > Math.abs(nums[j])) {
        result[k] = nums[i] * nums[i];
        i++;
      } else {
        result[k] = nums[j] * nums[j];
        j--;
      }
    }
    return result;
  }


  /*
      Let n = 7 and k = 3
      Original List                   : 1 2 3 4 5 6 7
      After reversing all numbers     : 7 6 5 4 3 2 1
      After reversing first k numbers : 5 6 7 4 3 2 1
      After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
      O(n) TC and O(1) SC
   */
  public void rotate(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }


  /*
  The majority element is the element that appears more than ⌊n / 2⌋ times.
  You may assume that the majority element always exists in the array.
  Boyer-Moore Voting Algorithm
   */
  public int majorityElementI(int[] nums) {
    // Initialize the major element and its count
    int major = nums[0];
    int count = 1;

    // Traverse the array
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == major) {
        // Increment count for the current candidate
        count++;
      } else {
        // Decrement count for a different element
        count--;
        // If the count reaches zero, update the major candidate
        if (count == 0) {
          major = nums[i];
          count = 1;
        }
      }
    }
    return major;
  }

  /*
  find all elements that appear more than ⌊ n/3 ⌋ times.
   */
  public List<Integer> majorityElement(int[] nums) {
    int candidate1 = Integer.MAX_VALUE;
    int count1 = 0;
    int candidate2 = Integer.MAX_VALUE;
    int count2 = 0;
    for (int num : nums) {
      if (num == candidate1) {
        count1++;
      } else if (num == candidate2) {
        count2++;
      } else if (count1 == 0) {
        candidate1 = num;
        count1 = 1;
      } else if (count2 == 0) {
        candidate2 = num;
        count2 = 1;
      } else {
        count1--;
        count2--;
      }
    }
    // Reset counters to find the actual counts of candidates.
    count1 = 0;
    count2 = 0;
    for (int num : nums) {
      if (num == candidate1) {
        count1++;
      } else if (num == candidate2) {
        count2++;
      }
    }
    List<Integer> result = new ArrayList<>();
    if (count1 > nums.length / 3) {
      result.add(candidate1);
    }
    if (count2 > nums.length / 3) {
      result.add(candidate2);
    }
    return result;
  }
}
