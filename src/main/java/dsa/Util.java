package dsa;

import dsa.models.Interval;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Util {

  public static final String EAST = "EAST";

  public static BigInteger factorial(BigInteger number) {
    BigInteger result = BigInteger.valueOf(1);

    for (long factor = 2; factor <= number.longValue(); factor++) {
      result = result.multiply(BigInteger.valueOf(factor));
    }
    return result;
  }

  public static void swap(List<Integer> array, int i, int j) {
    Integer a = array.get(i);
    array.set(i, array.get(j));
    array.set(j, a);
  }

  public static void swap(int[] numbs, int i, int j) {
    int temp = numbs[i];
    numbs[i] = numbs[j];
    numbs[j] = temp;
  }

  public static void reverse(int[] numbs, int start, int end) {
    while (start < end) {
      swap(numbs, start, end);
      start++;
      end--;
    }
  }

  public static boolean prefixIsValid(int[] arr, int i, Map<Integer, Boolean> memo) {
    if (memo.containsKey(i)) {
      return memo.get(i);
    }
    boolean ans = false;

    // Check three possibilities
    if (i >= 1 && arr[i] == arr[i - 1]) {
      ans |= prefixIsValid(arr, i - 2, memo);
    }
    if (i >= 2 && arr[i] == arr[i - 1] && arr[i - 1] == arr[i - 2]) {
      ans |= prefixIsValid(arr, i - 3, memo);
    }
    if (i >= 2 && arr[i] == arr[i - 1] + 1 && arr[i - 1] == arr[i - 2] + 1) {
      ans |= prefixIsValid(arr, i - 3, memo);
    }
    memo.put(i, ans);
    return ans;
  }

  public static int mergeSort(int[] arr, int start, int end) {
    if (start >= end) {
      return 0;
    }
    int mid = start + (end - start) / 2;
    int count = mergeSort(arr, start, mid) + mergeSort(arr, mid + 1, end);
    for (int i = start, j = mid + 1; i <= mid; i++) {
      while (j <= end && arr[i] / 2.0 > arr[j]) {
        j++;
      }
      count += j - (mid + 1);
    }
    merge(arr, start, end);
    return count;
  }

  public static int inversionCount(int[] arr, int start, int end) {
    if (start >= end) {
      return 0;
    }
    int mid = start + (end - start) / 2;
    int count = inversionCount(arr, start, mid) + inversionCount(arr, mid + 1, end);
    for (int i = start, j = mid + 1; i <= mid; i++) {
      while (j <= end && arr[i] > arr[j]) {
        j++;
      }
      count += j - (mid + 1);
    }
    merge(arr, start, end);
    return count;
  }

  private static void merge(int[] arr, int low, int high) {
    int mid = low + (high - low) / 2;
    int[] aux = new int[high - low + 1];
    int i = low, j = mid + 1, k = 0;
    while (k < arr.length) {
      int num1 = (i > mid) ? Integer.MAX_VALUE : arr[i];
      int num2 = (j > high) ? Integer.MAX_VALUE : arr[j];
      aux[k++] = (num1 <= num2) ? arr[i++] : arr[j++];
    }
    System.arraycopy(aux, 0, arr, low, arr.length);
  }

  // Returns true if the intervals a and b have a common element.
  public static boolean doesIntervalsOverlap(Interval a, Interval b) {
    return Math.min(a.end, b.end) - Math.max(a.start, b.start) >= 0;
  }

  public static Interval mergeIntervals(Interval a, Interval b) {
    int start = Math.min(a.start, b.start);
    int end = Math.max(a.end, b.end);
    return new Interval(start, end);
  }

  public static int max(Integer... values) {
    return Collections.max(Arrays.asList(values));
  }

  public static int[][] createPrefixSumMatrix(int[][] matrix) {
    int[][] sums = new int[matrix.length][matrix[0].length];
    sums[0][0] = matrix[0][0];
    IntStream.range(1, matrix.length)
        .forEach(row -> sums[row][0] = sums[row - 1][0] + matrix[row][0]);
    IntStream.range(1, matrix[0].length)
        .forEach(col -> sums[0][col] = sums[0][col - 1] + matrix[0][col]);
    for (int row = 1; row < matrix.length; row++) {
      for (int col = 1; col < matrix[0].length; col++) {
        sums[row][col] =
            matrix[row][col] + sums[row - 1][col] + sums[row][col - 1] - sums[row - 1][col - 1];
      }
    }
    return sums;
  }

  static boolean isPalindrome(String s, int start, int end) {
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  public static String buildSubsequence(int[][] dp, String text) {
    StringBuilder sb = new StringBuilder();
    int row = dp.length - 1;
    int col = dp[0].length - 1;
    while (row >= 1 && col >= 1) {
      if (dp[row][col] == dp[row - 1][col]) {
        row--;
      } else if (dp[row][col] == dp[row][col - 1]) {
        col--;
      } else {
        sb.append(text.charAt(row - 1));
        row--;
        col--;
      }
    }
    sb.reverse();
    return sb.toString();
  }

  public static int calculateManhattanDistance(int[] pointA, int[] pointB) {
    return Math.abs(pointA[0] - pointB[0]) + Math.abs(pointA[1] - pointB[1]);
  }

  public static long powerMod(long base, long exponent, long modulus) {
    long result = 1;
    base %= modulus;
    while (exponent > 0) {
      if (exponent % 2 == 1) {
        result = (result * base) % modulus;
      }
      exponent >>= 1;
      base = (base * base) % modulus;
    }
    return result;
  }
}
