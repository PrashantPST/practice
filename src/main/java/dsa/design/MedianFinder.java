package dsa.design;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {


    /**
     * •	For median, we use two heaps:
     * 	•	A max heap for the lower half of numbers.
     * 	•	A min heap for the upper half of numbers.
     */
    public final PriorityQueue<Integer> max;
    public final PriorityQueue<Integer> min;

    public MedianFinder() {
        max = new PriorityQueue<>(Collections.reverseOrder()); // Max heap
        min = new PriorityQueue<>(); // Min heap
    }

    /*
    O(logn) TC and O(n) SC
     */
    public void addNum(int num) {
        if (max.isEmpty() || num <= max.peek()) {
            max.add(num);
        } else {
            min.add(num);
        }
        // Re-balance heaps if needed
        if (max.size() > 1 + min.size()) {
            min.add(max.poll());
        } else if (min.size() > 1 + max.size()) {
            max.add(min.poll());
        }
    }

    /**
     * 	•	Time Complexity:  O(1)
     * 	•	Space Complexity:  O(1)
     */
    public double findMedian() {
        if (max.size() == min.size()) {
            return (double) (max.peek() + min.peek()) / 2;
        } else if (max.size() > min.size()) {
            return max.peek();
        }
        return min.peek();
    }
}
