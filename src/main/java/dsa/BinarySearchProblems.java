package dsa;

import dsa.models.BinaryMatrix;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BinarySearchProblems {

    /*
    may contain duplicates
     */
    public boolean searchInARotatedSortedArrayII(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        while (l <= h) {
            while (l < h && nums[l] == nums[l + 1]) {
                l += 1;
            }
            while (l < h && nums[h] == nums[h - 1]) {
                h -= 1;
            }
            int mid = (l + h) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[h]) {
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
        }
        return false;
    }

    public int findMinimumInASortedRotatedArrayI(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) {
                ans = Math.min(ans, nums[left]);
                left = mid + 1;
            } else {
                ans = Math.min(ans, nums[mid]);
                right = mid - 1;
            }
        }
        return ans;
    }

    public int findMinimumInASortedRotatedArrayII(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[0]) {
            return nums[0];
        }
        while (left <= right) {
            while (left < right && nums[left] == nums[left + 1]) {
                left += 1;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right -= 1;
            }
            int mid = left + (right - left) / 2;
            if (mid == nums.length - 1) {
                return nums[mid];
            }
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (mid == 0) {
                return nums[mid];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = findBound(nums, target, true);
        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }
        int lastOccurrence = findBound(nums, target, false);
        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                if (isFirst) {
                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }
                    // Search on the left side for the bound.
                    end = mid - 1;
                } else {
                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }
                    // Search on the right side for the bound.
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }


    /*
    sorted array consisting of only integers where every element appears exactly twice
    except for one element which appears exactly once
    O(logn) TC and O(1) SC
     */
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums[0] != nums[1]) {
            return nums[0];
        }
        if (nums[nums.length - 1] != nums[nums.length - 2]) {
            return nums[nums.length - 1];
        }
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if ((mid % 2 == 0 && nums[mid + 1] == nums[mid]) || (mid % 2 == 1 && nums[mid - 1] == nums[mid])) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int l = 1;
        int r = nums.length - 2;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] < nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long) pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }
        return right;
    }

    public double myPow(double x, int n) {
        if (n < 0) {
            return 1.0 / rec(x, n);
        }
        return rec(x, n);
    }

    public double rec(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double t = rec(x, n / 2);
        if (n % 2 == 0) {
            return t * t;
        }
        return t * t * x;
    }

    /*
    Let n be the length of the input array piles and m
    be the maximum number of bananas in a single pile from piles
    search space will be 1 to m
    TC O(nlogm) SC O(1)
     */
    public int minEatingSpeed(int[] piles, int h) {
        // Initialize the left and right boundaries
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        int ans = Integer.MAX_VALUE;
        while (left <= right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middle = (left + right) / 2;
            int hourSpent = 0;
            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middle);
            }
            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                ans = Math.min(ans, middle);
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return ans;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(bloomDay, mid, k, m)) {
                ans = Math.min(ans, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private boolean isPossible(int[] bloomDay, int mid, int k, int booket) {
        int bouquets = 0, flowersCollected = 0;
        for (int value : bloomDay) {
            if (value <= mid) {
//                If the current flower can be taken with in days then increase the flower flowersCollected.
                flowersCollected++;
            } else {
//                If there is a flower in between that takes more number of days then the given day,
//                then reset the counter
                flowersCollected = 0;
            }
//            If the flowersCollected is same as the required flower per bookie, then increase the bouquets count;
            if (flowersCollected == k) {
                bouquets++;
                flowersCollected = 0;
            }
        }
        return bouquets >= booket;
    }

    /*
    O(NlogM)
    N is the number of elements and M is the maximum element of the nums array
     */
    public int smallestDivisor(int[] nums, int threshold) {
        if (nums.length == 1) {
            return (int) Math.ceil((double) nums[0] / threshold);
        }
        int l = 1;
        int r = Arrays.stream(nums).max().getAsInt();
        int divisor = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (can(nums, mid, threshold)) {
                divisor = Math.min(divisor, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return divisor;
    }

    public boolean can(int[] arr, int mid, int t) {
        int local = 0;
        for (int a : arr) {
            local += Math.ceil((double) a / mid);
        }
        return local <= t;
    }

    public int shipWithinDays(int[] weights, int days) {
        if (weights.length == 1) {
            return weights[0];
        }
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();
        int ans = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (feasible(weights, mid, days)) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private boolean feasible(int[] weights, int c, int days) {
        int daysNeeded = 1, currentLoad = 0;
        for (int weight : weights) {
            currentLoad += weight;
            if (currentLoad > c) {
                daysNeeded++;
                currentLoad = weight;
            }
        }
        return daysNeeded <= days;
    }

    /*
    Approach I: TC - O(NK) and O(N) SC TLE
    Approach II: TC - O(N+KlogN) and O(N) SC TLE
     */
    public double minmaxGasDist(int[] stations, int k) {
        /*
        int n = stations.length;
        int[] count = new int[n - 1];
        for (int i = 1; i <= k; i++) {
            int l = 0;
            double min = Double.MIN_VALUE;
            for (int j = 0; j < n - 1; j++) {
                double t = (double) (stations[j + 1] - stations[j]) / (count[j] + 1);
                if (t > min) {
                    l = j;
                    min = (double) (stations[j + 1] - stations[j]) / (count[j] + 1);
                }
            }
            count[l]++;
        }
        double ans = 0;
        for (int i = 0; i < count.length; i++) {
            ans = Math.max(ans, (double) (stations[i + 1] - stations[i]) / (count[i] + 1));
        }
        return ans;
         */

        int N = stations.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                (double) b[0] / b[1] < (double) a[0] / a[1] ? -1 : 1);
        for (int i = 0; i < N - 1; ++i)
            pq.add(new int[]{stations[i + 1] - stations[i], 1});
        for (int i = 0; i < k; i++) {
            int[] node = pq.poll();
            node[1]++;
            pq.add(node);
        }
        int[] node = pq.poll();
        return (double) node[0] / node[1];
    }

    /*
    A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted
    in non-decreasing order
    O(N+M) TC and O(1) SC
    */
    public int leftMostColumnWithAtleastOneOnes(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        // Set pointers to the top-right corner.
        int currentRow = 0;
        int currentCol = cols - 1;
        // Repeat the search until it goes off the grid.
        while (currentRow < rows && currentCol >= 0) {
            if (binaryMatrix.get(currentRow, currentCol) == 0) {
                currentRow++;
            } else {
                currentCol--;
            }
        }
        // If we never left the last column, this is because it was all 0's.
        return (currentCol == cols - 1) ? -1 : currentCol + 1;
    }

    /*
    O(m + n)
     */
    public static int[] searchInSortedMatrixI(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return new int[]{row, col};
            } else if (matrix[row][col] <= target) {
                row++;
            } else {
                col--;
            }
        }
        return new int[]{-1, -1};

        /*
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }
        return false;
         */
    }

    /*
    You must write a solution in O(log(mn)) TC
     */
    public static int[] searchInSortedMatrixII(int[][] matrix, int target) {
        int l = 0;
        int r = (matrix.length * matrix[0].length) - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int row = mid / matrix[0].length;
            int col = mid % matrix[0].length;
            if (matrix[row][col] == target) {
                return new int[]{row, col};
            } else if (matrix[row][col] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    public int indexEqualsValue(int[] array) {
        int ans = array.length;
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == mid) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else if (array[mid] > mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans == array.length ? -1 : ans;
    }
}
